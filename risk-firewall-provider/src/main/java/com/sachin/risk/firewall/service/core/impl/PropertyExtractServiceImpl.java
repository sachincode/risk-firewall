package com.sachin.risk.firewall.service.core.impl;

import com.google.common.collect.Maps;
import com.sachin.risk.common.core.convert.EventPropertyConverter;
import com.sachin.risk.common.data.cache.EventTypeCache;
import com.sachin.risk.common.data.cache.EventTypePropertyCache;
import com.sachin.risk.common.data.model.EventTypeProperty;
import com.sachin.risk.firewall.model.EventContext;
import com.sachin.risk.firewall.model.EventDTO;
import com.sachin.risk.firewall.service.core.PropertyExtractService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author shicheng.zhang
 * @since 17-8-28 上午11:42
 */
@Service
public class PropertyExtractServiceImpl implements PropertyExtractService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyExtractServiceImpl.class);

    /**
     * 根据事件属性配置抽取事件属性值，并将属性类型转换为正确配置的类型
     * 
     * @param eventContext
     * @return
     */
    @Override
    public Map<String, Object> extractByProperties(EventContext eventContext) {
        Map<String, Object> resultMap = Maps.newHashMap();
        EventDTO sourceEvent = eventContext.getSourceEvent();
        Map<String, EventTypeProperty> properties = EventTypePropertyCache.getInstance()
                .getEventTypeProperty(sourceEvent.getEventType());
        if (MapUtils.isEmpty(properties)) {
            LOGGER.error("can not find event properties in cache. event type: {}, sessionId: {}",
                    sourceEvent.getEventType(), sourceEvent.getSessionId());
            return resultMap;
        }

        for (Map.Entry<String, EventTypeProperty> entry : properties.entrySet()) {
            String propKey = entry.getKey();
            Object value = null;
            try {
                // 从rawMap的第一层抽取，如果没有，则从extInfo中抽取
                value = eventContext.getRawMap().get(propKey);
                if (value == null && sourceEvent.getExtInfo() != null) {
                    value = sourceEvent.getExtInfo().get(propKey);
                }
                if (value == null) {
                    continue;
                }

                EventTypeProperty property = entry.getValue();
                Object convertedProperty = EventPropertyConverter.convert(value, property.getDataType(),
                        property.getBusiType());
                if (convertedProperty != null) {
                    resultMap.put(propKey, convertedProperty);
                }
            } catch (Exception e) {
                LOGGER.error("extract event property error. sessionId: {}, propKey: {}, value: {}",
                        sourceEvent.getSessionId(), propKey, value);
            }
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> extractByRules(EventContext eventContext) {
        return null;
    }
}
