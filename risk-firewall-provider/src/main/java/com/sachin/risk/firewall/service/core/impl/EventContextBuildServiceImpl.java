package com.sachin.risk.firewall.service.core.impl;

import com.google.common.collect.Lists;
import com.sachin.risk.common.core.model.EventType;
import com.sachin.risk.common.data.cache.EventTypeCache;
import com.sachin.risk.common.util.JsonHelper;
import com.sachin.risk.firewall.constants.EventConstants;
import com.sachin.risk.firewall.model.EventContext;
import com.sachin.risk.firewall.model.EventDTO;
import com.sachin.risk.firewall.service.core.EventContextBuildService;
import com.sachin.risk.firewall.service.core.PropertyExtractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author shicheng.zhang
 * @since 17-8-28 上午11:29
 */
@Service
public class EventContextBuildServiceImpl implements EventContextBuildService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventContextBuildServiceImpl.class);

    @Resource(name = "propertyExtractServiceImpl")
    private PropertyExtractService propertyExtractService;

    /**
     *
     * @param eventDTO
     * @return
     */
    @Override
    public List<EventContext> buildEventContext(EventDTO eventDTO) {
        // 在我们的系统中会对事件进行拆分逻辑，一般支付类型请求会按订单维度m和支付方式维度n进行拆分
        // 拆分后的事件数量为m*n, 并以卡维度作为主维度数量返回结果
        Date accessTime = new Date();
        List<EventContext> contexts = Lists.newArrayList();

        try {
            // 事件平铺
            Map<String, Object> dtoRawMap = JsonHelper.parse(eventDTO, null);
            EventContext context = new EventContext();
            context.coverRawMap(dtoRawMap);
            // 根据事件配置属性抽取一遍finalMap
            Map<String, Object> properties = propertyExtractService.extractByProperties(context);
            context.coverFinalMap(properties);
            // 根据抽取规则抽取一遍finalMap
            Map<String, Object> objectMap = propertyExtractService.extractByRules(context);
            context.coverFinalMap(objectMap);
            setCommonProperty(context, accessTime);
            contexts.add(context);
        } catch (Exception e) {
            LOGGER.error("event context build error. sessionId: {}", eventDTO.getSessionId(), e);
        }
        return contexts;
    }

    private void setCommonProperty(EventContext context, Date accessTime) {
        EventType eventType = EventTypeCache.getInstance().getEventType(context.getSourceEvent().getEventType());
        context.setEventTypeId(eventType.getId());
        context.setEventScope(eventType.getType());
        context.putRawMap(EventConstants.EVENT_TYPE_ID, eventType.getId());
        context.putRawMap(EventConstants.EVENT_TYPE_CODE, eventType.getCode());
        context.putFinalMap(EventConstants.EVENT_TYPE_ID, eventType.getId());
        context.putFinalMap(EventConstants.EVENT_TYPE_CODE, eventType.getCode());
        context.putRawMap(EventConstants.FIREWALL_ACCESS_TIME, accessTime);

        String riskId = UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
        context.putRawMap(EventConstants.RISK_ID, riskId);
        context.putFinalMap(EventConstants.RISK_ID, riskId);
    }
}
