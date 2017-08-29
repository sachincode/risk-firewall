package com.sachin.risk.firewall.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shicheng.zhang
 * @since 17-8-18 上午10:41
 */
public class EventContext {

    private EventDTO sourceEvent;

    private ConcurrentHashMap<String, Object> rawMap = new ConcurrentHashMap<>();

    public EventDTO getSourceEvent() {
        return sourceEvent;
    }

    public void setSourceEvent(EventDTO sourceEvent) {
        this.sourceEvent = sourceEvent;
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
}
