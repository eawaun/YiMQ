package com.yimq.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * SnowFlake实现
 * 生成64位的ID，ID格式为
 * 41位的时间戳 + 10位截断的mac地址 + 12位递增序列
 * 即
 * 0 | timestamp | machineId | sequence
 * 0 |    41     |     10    |  12
 * 0表示非负数
 */
public class IdGenerator {

    private final int sequenceBits = 12;
    private final int machineIdBits = 10;
    private final int maxMachineId = ~(-1 << machineIdBits);

    private final int machineIdShift = sequenceBits;
    private final int timestampLeftShift = sequenceBits + machineIdBits;

    private final long baseTimestamp = 1519643637000L;//基准时间
    private final int machineId;
    private int maxSequence = 1 << 12;

    private volatile long lastTimestamp = -1L;
    private volatile int sequence = 0;

    private volatile static IdGenerator singleton;

    private IdGenerator() throws Exception {
        machineId = getMachineId();
        if (machineId > maxMachineId) {
            throw new Exception("machineId > MaxMachineId, machinedId: " + machineId);
        } else if (machineId < 0) {
            throw new Exception("machineId < 0, machinedId: " + machineId);
        }
    }

    public static IdGenerator getInstance() throws Exception {
        if (singleton == null) {
            synchronized (IdGenerator.class) {
                if (singleton == null) {
                    singleton = new IdGenerator();
                }
            }
        }
        return singleton;
    }

    public synchronized long generate() throws Exception {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new Exception(
                "Clock moved backwards.  Refusing to generate id for " + (
                    lastTimestamp - timestamp) + " milliseconds.");
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) % maxSequence;
            if (sequence == 0) {
                timestamp = tillNextMills(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;

        return ((timestamp - baseTimestamp) << timestampLeftShift) | (machineId << machineIdShift) | sequence;
    }

    private long tillNextMills(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    private int getMachineId() throws Exception {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ip);
            int machineId;
            if (networkInterface == null) {
                machineId = 1;
            } else {
                byte[] mac = networkInterface.getHardwareAddress();
                machineId =
                    (int) (((0x000000FF & (long) mac[mac.length - 1]) |
                        (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6);
            }
            return machineId;
        } catch (UnknownHostException | SocketException e) {
            throw new Exception(e);
        }
    }
}
