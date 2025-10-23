package com.voltageg;

import com.voltageg.sensors.Sensors;

import static com.voltageg.Main.LOGGER;

public class MasterThread extends AbstractThread{
    @Override
    public void init() {
        Client.runRTUOverTCPClient();
    }

    @Override
    public void tickLoop() {
        int[] response = Sensors.TEMPERATURE_HUMIDITY.readData();

        int temperature = response[0];
        int humidity = response[1];
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
    }

    @Override
    public void mainLoop() {

    }
}
