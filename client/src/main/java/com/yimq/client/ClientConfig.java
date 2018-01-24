package com.yimq.client;

import com.yimq.common.Constant;

import java.util.Arrays;
import java.util.List;

public class ClientConfig {
    private String namesrvAddr = System.getProperty(Constant.NAMESRV_ADDR_PROPERTY
        , System.getenv(Constant.NAMESRV_ADDR_ENV));

    public List<String> getNamesrvAddrList() {
        if (this.namesrvAddr != null) {
            return Arrays.asList(this.namesrvAddr.split(";"));
        }
        return null;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }
}
