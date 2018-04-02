package com.xgq.data.save;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xingguoqing
 * @date 2018/4/1 下午12:41
 */
@Component
public class DataFilterThread
        extends Thread
{
    ArrayList catcheListA = new ArrayList();
    ArrayList catcheListB = new ArrayList();
    private int counter = 0;
    private String data;
//    DataSaveThread dataSaveThread;
    ExecutorService pool = Executors.newCachedThreadPool();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void run()
    {
//        if (RegExp.isEffective(this.data)) {
//            return;
//        }
        save(this.data);
    }

    private void save(String data)
    {
        this.counter += 1;
        if (this.counter <= 5)
        {
            this.catcheListA.add(data);
            if (this.catcheListA.size() == 5) {
                sendArrayList(this.catcheListA);
            }
        }
        else
        {
            this.catcheListB.add(data);
            if (this.catcheListB.size() == 5) {
                sendArrayList(this.catcheListB);
            }
        }
        if (this.counter == 10) {
            this.counter = 0;
        }
    }

    private void sendArrayList(ArrayList arrayList)
    {
//        this.dataSaveThread = ((DataSaveThread)SpringApplicationContextHolder.getSpringBean("dataSaveThread"));
//        this.dataSaveThread.setCatcheList(arrayList);
//        this.pool.execute(this.dataSaveThread);
        System.out.println(arrayList);
    }

    public String getData()
    {
        return this.data;
    }

    public void setData(String data)
    {
        this.data = data;
    }
}
