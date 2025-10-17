package com.voltageg;

import com.digitalpetri.modbus.client.ModbusRtuClient;
import com.digitalpetri.modbus.client.ModbusTcpClient;
import com.digitalpetri.modbus.exceptions.ModbusExecutionException;
import com.digitalpetri.modbus.serial.client.SerialPortClientTransport;
import com.digitalpetri.modbus.tcp.client.NettyRtuClientTransport;
import com.digitalpetri.modbus.tcp.client.NettyTcpClientTransport;
import com.fazecast.jSerialComm.SerialPort;

import static com.voltageg.Main.client;

public class Client {
    public static void runTCPClient() {
        var transport = NettyTcpClientTransport.create(cfg -> {
            cfg.setHostname("127.0.0.1");
            cfg.setPort(502);
        });

        client = ModbusTcpClient.create(transport);
        reconnect();
    }
    public static void runRTUClient() {
        var transport = SerialPortClientTransport.create(cfg -> {
            cfg.setSerialPort("/dev/ttyUSB0");
            cfg.setBaudRate(115200);
            cfg.setDataBits(8);
            cfg.setParity(SerialPort.NO_PARITY);
            cfg.setStopBits(SerialPort.TWO_STOP_BITS);
        });
        client = ModbusRtuClient.create(transport);
        reconnect();
    }

    public static void runRTUOverTCPClient() {
        var transport = NettyRtuClientTransport.create(cfg -> {
            cfg.setHostname("127.0.0.1");
            cfg.setPort(502);
        });

        client = ModbusRtuClient.create(transport);
        reconnect();
    }

    public static void reconnect() {
        try {
            client.disconnect();
            client.connect();
        } catch (ModbusExecutionException ignored) {

        }
    }
}
