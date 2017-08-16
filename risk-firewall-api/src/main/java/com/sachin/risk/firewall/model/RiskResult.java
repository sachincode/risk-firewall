package com.sachin.risk.firewall.model;

import java.io.Serializable;

/**
 * @author shicheng.zhang
 * @since 17-8-15 下午8:41
 */
public class RiskResult implements Serializable {

    /**
     * 风控返回码
     */
    private Long resultCode;

    /**
     * 风控结果预留字段，可以传输json格式的字符串
     */
    private String resultInfo;

    public Long getResultCode() {
        return resultCode;
    }

    public void setResultCode(Long resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    @Override
    public String toString() {
        return "RiskResult{" + "resultCode=" + resultCode + ", resultInfo='" + resultInfo + '\'' + '}';
    }
}
