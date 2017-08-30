package com.sachin.risk.firewall.constants;

/**
 * @author shicheng.zhang
 * @since 17-8-30 上午10:43
 */
public class EventConstants {

    private EventConstants() {
    }

    public static final String EVENT_TYPE_ID = "eventTypeId";
    public static final String HASHCODE = "hashCode";
    public static final String EVENT_TYPE_CODE = "eventTypeCode";
    public static final String RISK_ID = "riskId";
    public static final String OCCUR_TIME = "occurTime";
    // 数据接入防火墙时间
    public static final String FIREWALL_ACCESS_TIME = "firewall_accessTime";
    // 防火墙事件处理时间
    public static final String FIREWALL_PROCESS_TIME = "firewall_processTime";
    // rawMap中存储timeOut的补充规则ListKey
    public static final String TIMEOUT_SUPPLY_RULES = "timeoutSupplyRules";
    // 记录命中的补充规则
    public static final String HIT_SUPPLY_RULES = "hitSupplyRules";
}
