package com.xgq.data;

import com.xgq.data.collection.DataAcceptThread;
import com.xgq.data.collection.HandShake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.DatagramSocket;

/**
 * @author xingguoqing
 * @date 2018/4/1 下午12:45
 */
@Configuration
public class StartDateCollection
        implements ServletContextListener
{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private DatagramSocket socket;
    @Autowired
    DataAcceptThread dataAcceptThread;
    @Autowired
    HandShake handShake;
    @Autowired
    private StringRedisTemplate template;
//    @Autowired
//    BackDataService dataService;
    @Value("${connection.sign}")
    private String sign;
    @Value("${connection.ip}")
    private String ip;
    @Value("${connection.port}")
    private int port;

    public StartDateCollection()
    {
        try
        {
            this.socket = new DatagramSocket(0, null);
        }
        catch (Exception e)
        {
            this.logger.error("DatagramSocket实例化异常", e);
        }
    }

    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
//        this.dataService.createTable();
        if (this.handShake.send(this.sign.getBytes(), this.ip, this.port, this.socket)) {
            dataAcceptThread.setSocket(this.socket);
            dataAcceptThread.setTemplate(this.template);
        }
        this.dataAcceptThread.start();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
