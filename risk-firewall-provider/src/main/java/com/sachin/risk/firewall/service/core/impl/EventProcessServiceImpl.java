package com.sachin.risk.firewall.service.core.impl;

import com.sachin.risk.common.util.JsonHelper;
import com.sachin.risk.firewall.model.EventDTO;
import com.sachin.risk.firewall.model.RiskResult;
import com.sachin.risk.firewall.service.core.EventProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author shicheng.zhang
 * @since 17-8-16 下午9:48
 */
@Service
public class EventProcessServiceImpl implements EventProcessService {


    private static final Logger LOGGER = LoggerFactory.getLogger(EventProcessServiceImpl.class);


    @Override
    public List<RiskResult> processSync(EventDTO eventDTO) {


        try {
            // 事件平铺
            Map<String, Object> dtoMap = JsonHelper.parse(eventDTO, null);
        } catch (Exception e) {
            LOGGER.error("process sync event error. sessionId: {}", eventDTO.getSessionId(), e);
        }
        return null;
    }

    @Override
    public List<RiskResult> processAsync(EventDTO eventDTO) {
        return null;
    }
}
