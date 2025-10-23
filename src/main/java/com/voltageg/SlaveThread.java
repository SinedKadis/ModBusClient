package com.voltageg;

import com.voltageg.sensors.Sensors;

import static com.voltageg.Main.LOGGER;

public class SlaveThread extends AbstractThread{
    @Override
    public void init() {
        Server.startRTUOverTCPSlave();
        Sensors.TEMPERATURE_HUMIDITY.writeData(20,40);
    }

    @Override
    public void tickLoop() {
        int temperature = (int) (Math.sin(tick.get())*10+10);
        int humidity = (int) (Math.cos(tick.get())*10+10);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= 19; i++) {
            if (i == temperature) builder.append("T");
            else builder.append(".");
        }
        LOGGER.info(builder.toString());
        builder = new StringBuilder();
        for (int i = 0; i <= 19; i++) {
            if (i == humidity) builder.append("H");
            else builder.append(".");
        }
        LOGGER.info(builder.toString());

        Sensors.TEMPERATURE_HUMIDITY.writeData(temperature,humidity);
    }

    @Override
    public void mainLoop() {

    }
}
