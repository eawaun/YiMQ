package com.yimq.client;

import com.yimq.common.Constant;

public class ClientConfig {
    private String namesrvAddr = System.getProperty(Constant.NAMESRV_ADDR_PROPERTY
        , System.getenv(Constant.NAMESRV_ADDR_ENV));

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }
}
