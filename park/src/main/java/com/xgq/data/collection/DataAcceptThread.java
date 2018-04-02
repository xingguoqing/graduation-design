package com.xgq.data.collection;

import com.xgq.data.save.DataFilterThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xingguoqing
 * @date 2018/4/1 下午12:39
 */
@Component
public class DataAcceptThread
        extends Thread {

    @Value("${connection.sign}")
    private String sign;
    @Value("${connection.ip}")
    private String ip;
    @Value("${connection.port}")
    private int port;
    @Autowired
    HandShake handShake;


    private final Logger logger = LoggerFactory.getLogger(getClass());
    private DatagramSocket socket;
    private StringRedisTemplate template;
    private ValueOperations<String, String> ops;
    private SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private boolean isAccept = true;

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }
    public void setTemplate(StringRedisTemplate template) {
        this.template = template;
        this.ops = template.opsForValue();
    }


    private void close() {
        this.isAccept = false;
        if ((this.socket == null) || (this.socket.isClosed())) {
            return;
        }
        this.socket.close();
        this.socket = null;
    }

    public String listen() {
        try {
//            System.out.println("s");
//            logger.info("开始获取数据");
            byte[] arrayOfByte1 = new byte[65507];

            DatagramPacket localDatagramPacket = new DatagramPacket(arrayOfByte1, arrayOfByte1.length);

            this.socket.setSoTimeout(0);

            this.socket.receive(localDatagramPacket);
            if (localDatagramPacket.getLength() != 0) {
                byte[] arrayOfByte2 = new byte[localDatagramPacket.getLength()];
                System.arraycopy(localDatagramPacket.getData(), 0, arrayOfByte2, 0, arrayOfByte2.length);
                return new String(arrayOfByte2);
            }
        } catch (Exception e) {
//            System.out.println("出现异常");
            this.logger.error(e.getMessage(), e);
        }
        return null;
    }



    public void run() {
        try {
            while (this.isAccept) {
                try {
                    DatagramSocket socket = new DatagramSocket(0, null);
                    setSocket(socket);
                    handShake.send(this.sign.getBytes(), this.ip, this.port, this.socket);
                    Thread.sleep(1000L);
                    String a = listen();
                    if (!StringUtils.isEmpty(a)){
                        String[] datas = a.split(",");
                        String data = datas[1]+","+ datas[2]+"," + datas[3]+"," + datas[4];
                        System.out.println(data);
                        ops.set("data", data);
                    }

                    socket.close();

                } catch (InterruptedException e) {
                    this.logger.error(e.getMessage(), e);
                }
            }
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
