package com.voltageg;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Thread master = new MasterThread();
        Thread slave = new SlaveThread();

        master.start();
        slave.start();

    }


}