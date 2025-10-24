package com.voltageg.threads;

import java.util.concurrent.*;

public abstract class AbstractThread extends Thread{
    public boolean isMaster;
    public boolean stop = false;

    @Override
    public void run() {
        init();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::tickLoop, 0, 50, TimeUnit.MILLISECONDS);
        while (!stop)
            mainLoop();
    }

    public abstract void init();
    public abstract void tickLoop();
    public abstract void mainLoop();
}
