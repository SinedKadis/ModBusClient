package com.voltageg.threads;

import com.voltageg.Slave;
import com.voltageg.sensors.Sensors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlaveThread extends AbstractThread {
    public static final Logger LOGGER = LoggerFactory.getLogger(SlaveThread.class);
    long tick = 0;
    @Override
    public void init() {
        isMaster = false;
        setName("SlaveThread-"+threadId());
        Slave.startRTUOverTCPSlave();
        Sensors.TEMPERATURE_HUMIDITY.writeData(isMaster,20,40);
    }

    @Override
    public void tickLoop() {
        tick++;
        int i1 = 4;
        int temperature = (int) (Math.sin((double) tick / i1)*10+10);
        int humidity = (int) (Math.cos((double) tick / i1)*10+10);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= 19; i++) {
            if (i == temperature) builder.append("T");
            else builder.append(".");
        }
        builder.append("   ");
        for (int i = 0; i <= 19; i++) {
            if (i == humidity) builder.append("H");
            else builder.append(".");
        }
        LOGGER.info(builder.toString());

        Sensors.TEMPERATURE_HUMIDITY.writeData(isMaster,temperature,humidity);
        //LOGGER.info("Sent");
    }

    @Override
    public void mainLoop() {

    }
}
