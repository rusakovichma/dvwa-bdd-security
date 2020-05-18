package com.github.rusakovichma.dvwa.bdd.nc;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

public class NetCat {

    // Ready to handle full-size UDP datagram or TCP segment in one step
    public final static int BUFFER_LIMIT = 2 << 16 - 1;

    private ExecutorCompletionService<Long> executor;

    private NetCatOptions opt;

    public NetCat(NetCatOptions opt) {
        this.opt = opt;
        executor = new ExecutorCompletionService<Long>(Executors.newFixedThreadPool(2));
    }

    public Future<Long> start() throws Exception {
        return opt.isListen() ? listen() : connect();
    }

    private Future<Long> listen() throws Exception {
        System.err.println(String.format("Listening at %s:%d", opt.isUdp() ? "UDP" : "TCP", opt.getPort()));
        return opt.isUdp() ? listenUdp() : listenTcp();
    }

    private Future<Long> connect() throws Exception {
        System.err.println(String.format("Connecting to [%s:%d]", opt.getHost(), opt.getPort()));
        return opt.isUdp() ? connectUdp() : connectTcp();
    }

    /**
     * @return Receiver future
     */
    private Future<Long> listenUdp() throws Exception {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(opt.getPort()));
        channel.configureBlocking(true);

        BlockingQueue<SocketAddress> queue = new ArrayBlockingQueue<SocketAddress>(1);
        Future<Long> future = executor.submit(new DatagramReceiver(channel, opt.getOutput(), queue));

        // Start sender after remote address will be determined
        SocketAddress remoteAddress = queue.take();
        executor.submit(new DatagramSender(opt.getInput(), channel, remoteAddress));

        return future;
    }

    /**
     * @return Receiver future
     */
    private Future<Long> connectUdp() throws Exception {
        DatagramChannel channel = DatagramChannel.open();
        SocketAddress remoteAddress = new InetSocketAddress(opt.getHost(), opt.getPort());
        channel.connect(remoteAddress);

        executor.submit(new DatagramSender(opt.getInput(), channel, remoteAddress));
        return executor.submit(new DatagramReceiver(channel, opt.getOutput(), null));
    }

    /**
     * @return Receiver future
     */
    private Future<Long> listenTcp() throws Exception {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(opt.getPort()));
        serverChannel.configureBlocking(true);
        SocketChannel channel = serverChannel.accept();
        InetSocketAddress remoteAddress = (InetSocketAddress) channel.getRemoteAddress();
        System.err.println(String.format("Accepted from [%s:%d]", remoteAddress.getAddress().getHostAddress(), remoteAddress.getPort()));
        return transferStreams(channel);
    }

    /**
     * @return Receiver future
     */
    private Future<Long> connectTcp() throws Exception {
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress(opt.getHost(), opt.getPort()));
        return transferStreams(channel);
    }

    /**
     * @return Receiver future
     */
    private Future<Long> transferStreams(final SocketChannel socketChannel) throws IOException, ExecutionException, InterruptedException {
        // Shutdown socket when this program is terminated
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    socketChannel.shutdownOutput();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        executor.submit(new StreamTransferer(Channels.newChannel(opt.getInput()), socketChannel));
        executor.submit(new StreamTransferer(socketChannel, Channels.newChannel(opt.getOutput())));
        executor.take().get();  // Wait for sender
        return executor.take(); // And return receiver future
    }
}
