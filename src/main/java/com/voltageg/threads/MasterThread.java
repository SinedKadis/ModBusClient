package com.voltageg.threads;

import com.voltageg.Master;
import com.voltageg.sensors.Sensors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MasterThread extends AbstractThread {
    public static final Logger LOGGER = LoggerFactory.getLogger(MasterThread.class);

    @Override
    public void init() {
        isMaster = true;
        setName("MasterThread-"+threadId());
        Master.runRTUOverTCPClient();
    }

    @Override
    public void tickLoop() {

        int[] lastResponse = Sensors.TEMPERATURE_HUMIDITY.readData(isMaster);

        if (lastResponse.length>0) {
            int temperature = lastResponse[0];
            int humidity = lastResponse[1];
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
        }
    }

    @Override
    public void mainLoop() {

    }
}
