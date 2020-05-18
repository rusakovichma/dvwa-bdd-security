package com.github.rusakovichma.dvwa.bdd.nc;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.DatagramChannel;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import static com.github.rusakovichma.dvwa.bdd.nc.NetCat.BUFFER_LIMIT;


class DatagramReceiver implements Callable<Long> {

    private WritableByteChannel output;
    private DatagramChannel input;
    private BlockingQueue<SocketAddress> queue;

    public DatagramReceiver(DatagramChannel input, OutputStream output, BlockingQueue<SocketAddress> queue) {
        this.input = input;
        this.output = Channels.newChannel(output);
        this.queue = queue;
    }

    @Override
    public Long call() {
        long total = 0;
        ByteBuffer buf = ByteBuffer.allocateDirect(BUFFER_LIMIT);
        try {
            if (queue != null) {
                InetSocketAddress remoteAddress = (InetSocketAddress) input.receive(buf);
                total = write(buf);
                System.err.println(String.format("Accepted from [%s:%d]", remoteAddress.getAddress(), remoteAddress.getPort()));
                input.connect(remoteAddress);
                queue.put(remoteAddress);
            }
            while ((input.read(buf)) != -1) {
                total += write(buf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    private int write(ByteBuffer buf) throws IOException {
        buf.flip();
        int bytes = output.write(buf);
        if (buf.hasRemaining()) {
            buf.compact();
        } else {
            buf.clear();
        }
        return bytes;
    }
}
