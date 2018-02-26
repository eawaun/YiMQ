package com.yimq.broker;

import com.yimq.common.Constant;
import com.yimq.remoting.common.RemotingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class BrokerConfig {
    private final static Logger logger = LoggerFactory.getLogger(BrokerConfig.class);

    private String namesrvAddr = System.getProperty(Constant.NAMESRV_ADDR_PROPERTY
        , System.getenv(Constant.NAMESRV_ADDR_ENV));

    private String brokerName = localHostName();
    private String brokerClusterName = "DefaultCluster";
    private int brokerId = Constant.MASTER_ID;
    private String brokerIP = RemotingUtil.getLocalAddress();

    private long registerBrokerTimeoutMills = 6000;

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public static String localHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.error("Failed to obtain the host name", e);
        }

        return "DEFAULT_BROKER";
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getBrokerClusterName() {
        return brokerClusterName;
    }

    public void setBrokerClusterName(String brokerClusterName) {
        this.brokerClusterName = brokerClusterName;
    }

    public int getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(int brokerId) {
        this.brokerId = brokerId;
    }

    public String getBrokerIP() {
        return brokerIP;
    }

    public void setBrokerIP(String brokerIP) {
        this.brokerIP = brokerIP;
    }

    public long getRegisterBrokerTimeoutMills() {
        return registerBrokerTimeoutMills;
    }

    public void setRegisterBrokerTimeoutMills(long registerBrokerTimeoutMills) {
        this.registerBrokerTimeoutMills = registerBrokerTimeoutMills;
    }
}
