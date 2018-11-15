package com.youmu.spider.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @Author: YOUMU
 */
public final class HttClientUtils {

    private HttClientUtils() {
    }

    /**
     * ping <b>address</b> and wait <b>time</b> milliSec
     * @param address address need be ping
     * @param time timeout(millisec
     * @return return true if ping ok or false
     */
    public static boolean ping(SocketAddress address, int time) {
        if (null == address) {
            return false;
        }
        Socket socket = new Socket();
        try {
            socket.connect(address, time);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
