package com.sachin.risk.firewall.service.core.impl;

import com.sachin.risk.common.util.JsonHelper;
import com.sachin.risk.firewall.constants.EventConstants;
import com.sachin.risk.firewall.model.EventContext;
import com.sachin.risk.firewall.model.EventDTO;
import com.sachin.risk.firewall.model.RiskResult;
import com.sachin.risk.firewall.service.RiskResultHandler;
import com.sachin.risk.firewall.service.core.EventContextBuildService;
import com.sachin.risk.firewall.service.core.EventProcessService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author shicheng.zhang
 * @since 17-8-16 下午9:48
 */
@Service
public class EventProcessServiceImpl implements EventProcessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventProcessServiceImpl.class);

    @Resource
    private EventContextBuildService eventContextBuildService;

    @Override
    public List<RiskResult> processSync(EventDTO eventDTO) {
        long beginTime = System.currentTimeMillis();

        try {
            // 1、构建事件，含事件拆分、属性抽取等
            List<EventContext> contexts = eventContextBuildService.buildEventContext(eventDTO);
            if (CollectionUtils.isEmpty(contexts)) {
                // 构建context失败，返回默认结果
                return RiskResultHandler.getBatchDefaultRiskResult(1);
            }
            // 2、限定时间内获取风控结果

            // 3、处理返回结果

            // 4、异步存储rawMap
            long processTime = System.currentTimeMillis() - beginTime;
            for (EventContext context : contexts) {
                context.putRawMap(EventConstants.TIMEOUT_SUPPLY_RULES, context.getTimeoutSupplyRules());
                context.putRawMap(EventConstants.FIREWALL_PROCESS_TIME, processTime);
            }


        } catch (Exception e) {
            LOGGER.error("process sync event error. sessionId: {}", eventDTO.getSessionId(), e);
            return RiskResultHandler.getBatchDefaultRiskResult(1);
        }
        return null;
    }

    @Override
    public List<RiskResult> processAsync(EventDTO eventDTO) {
        return null;
    }
}
