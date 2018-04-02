package com.xgq.data.collection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Component
public class HandShake
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean send(byte[] paramArrayOfByte, String ip, int port, DatagramSocket socket)
    {
        DatagramPacket datagramPacket = null;
        try
        {
            datagramPacket = new DatagramPacket(paramArrayOfByte, paramArrayOfByte.length, InetAddress.getByName(ip), port);

            socket.send(datagramPacket);

            return true;
        }
        catch (Exception localIOException)
        {
            this.logger.error("数据发送异常");
        }
        return false;
    }
}

