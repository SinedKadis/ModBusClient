package com.voltageg;


import com.digitalpetri.modbus.client.ModbusClient;
import com.voltageg.sensors.Sensors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class Main {
    public static boolean stop = false;
    public static ModbusClient client;
    public static final Supplier<Long> tick = System::currentTimeMillis;
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static final int tickRate = 50;


    static boolean ticked = false;
    public static void main(String[] args) {
        init();
        while (!stop) {
            if (tick.get() % tickRate == 0) {
                if (!ticked) {
                    tickLoop();
                    ticked = true;
                }
            } else ticked = false;
            mainLoop();
        }

    }

    private static void mainLoop() {

    }


    private static void init() {
        //Client.runTCPClient();
        //Client.runRTUClient();
        Client.runRTUOverTCPClient();
    }



    private static void tickLoop() {
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
//        LOGGER.info("Data:");
//        LOGGER.info("   Temperature: {}", response[0]);
//        LOGGER.info("   Humidity: {}", response[1]);

    }


}