package com.sachin.risk.firewall.service;

import com.sachin.risk.firewall.model.RiskResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shicheng.zhang
 * @since 17-8-30 上午11:32
 */
public class RiskResultHandler {

    // 默认返回值，可修改在配置文件中获取等
    public static final Integer DEFAULT_CODE = 21000;
    public static final String DEFAULT_INFO = "{\"mobile\":\"true\"}";

    public static RiskResult getDefaultRiskResult() {
        RiskResult result = new RiskResult();
        result.setResultCode(DEFAULT_CODE);
        result.setResultInfo(DEFAULT_INFO);
        return result;
    }

    public static List<RiskResult> getBatchDefaultRiskResult(int nums) {
        List<RiskResult> results = new ArrayList<>();
        for (int i = 0; i < nums; i++) {
            results.add(getDefaultRiskResult());
        }
        return results;
    }
}
