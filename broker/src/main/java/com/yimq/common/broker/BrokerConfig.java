package com.yimq.common.broker;

import com.yimq.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class BrokerConfig {
    private final static Logger logger = LoggerFactory.getLogger(BrokerConfig.class);

    private String namesrvAddr = System.getProperty(Constant.NAMESRV_ADDR_PROPERTY
        , System.getenv(Constant.NAMESRV_ADDR_ENV));

    private String brokerName = localHostName();

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
}
