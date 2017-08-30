package com.sachin.risk.firewall.model;

import com.sachin.risk.common.core.enums.EventScope;

import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shicheng.zhang
 * @since 17-8-18 上午10:41
 */
public class EventContext {

    private Long eventTypeId;

    private EventScope eventScope;

    private EventDTO sourceEvent;

    private String riskId;

    private ConcurrentHashMap<String, Object> rawMap = new ConcurrentHashMap<>();

    // finalMap 抽取后的map对象 key value
    private ConcurrentHashMap<String, Object> finalMap = new ConcurrentHashMap<>();

    private Vector<String> timeoutSupplyRules = new Vector<String>();

    public Long getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Long eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public EventScope getEventScope() {
        return eventScope;
    }

    public void setEventScope(EventScope eventScope) {
        this.eventScope = eventScope;
    }

    public EventDTO getSourceEvent() {
        return sourceEvent;
    }

    public void setSourceEvent(EventDTO sourceEvent) {
        this.sourceEvent = sourceEvent;
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }

    public ConcurrentHashMap<String, Object> getRawMap() {
        return rawMap;
    }

    public void coverRawMap(Map<String, Object> rawMap) {
        if (rawMap != null) {
            for (Map.Entry<String, Object> entry : rawMap.entrySet()) {
                putRawMap(entry.getKey(), entry.getValue());
            }
        }
    }

    public void putRawMap(String key, Object value) {
        if (key != null && value != null) {
            this.rawMap.put(key, value);
        }
    }

    public ConcurrentHashMap<String, Object> getFinalMap() {
        return finalMap;
    }

    public void coverFinalMap(Map<String, Object> finalMap) {
        if (finalMap != null) {
            for (Map.Entry<String, Object> entry : finalMap.entrySet()) {
                putFinalMap(entry.getKey(), entry.getValue());
            }
        }
    }

    public void putFinalMap(String key, Object value) {
        if (key != null && value != null) {
            this.finalMap.put(key, value);
        }
    }

    public Vector<String> getTimeoutSupplyRules() {
        return timeoutSupplyRules;
    }

    public void addTimeoutSupplyRule(String rule) {
        this.timeoutSupplyRules.add(rule);
    }
}
