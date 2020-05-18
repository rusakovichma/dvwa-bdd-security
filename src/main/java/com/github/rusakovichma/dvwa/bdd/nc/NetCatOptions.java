package com.github.rusakovichma.dvwa.bdd.nc;

import java.io.InputStream;
import java.io.OutputStream;

public class NetCatOptions {
    private boolean listen = false;

    private boolean udp = false;

    private int port = 9999;

    private String host = "127.0.0.1";

    private InputStream input = System.in;
    private OutputStream output = System.out;

    public NetCatOptions() {
    }

    public NetCatOptions(boolean listen, boolean udp, String host, int port, InputStream input, OutputStream output) {
        this.listen = listen;
        this.udp = udp;
        this.port = port;
        this.host = host;
        this.input = input;
        this.output = output;
    }

    public NetCatOptions(String host, int port) {
        this.port = port;
        this.host = host;
    }

    public boolean isListen() {
        return listen;
    }

    public void setListen(boolean listen) {
        this.listen = listen;
    }

    public boolean isUdp() {
        return udp;
    }

    public void setUdp(boolean udp) {
        this.udp = udp;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public InputStream getInput() {
        return input;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }

    public OutputStream getOutput() {
        return output;
    }

    public void setOutput(OutputStream output) {
        this.output = output;
    }
}
