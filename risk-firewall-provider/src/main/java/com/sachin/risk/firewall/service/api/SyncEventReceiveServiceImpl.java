package com.sachin.risk.firewall.service.api;

import com.sachin.risk.firewall.api.SyncEventReceiveService;
import com.sachin.risk.firewall.model.EventDTO;
import com.sachin.risk.firewall.model.RiskResult;
import com.sachin.risk.firewall.service.core.EventProcessService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author shicheng.zhang
 * @since 17-8-16 下午9:20
 */
public class SyncEventReceiveServiceImpl implements SyncEventReceiveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncEventReceiveServiceImpl.class);

    @Resource
    private EventProcessService eventProcessService;

    @Override
    public List<RiskResult> receiveEventDTO(EventDTO event) {
        if (event == null) {
            return null;
        }
        try {
            long start = System.currentTimeMillis();
            if (event.getOccurTime() == null) {
                event.setOccurTime(new Date());
            }
            if (StringUtils.isBlank(event.getEventType())) {
                throw new IllegalArgumentException("empty event type is error.");
            }

            LOGGER.info("sync event receive dto: {}", event);
            List<RiskResult> riskResults = eventProcessService.processSync(event);
            LOGGER.info("sync event receive finished. sessionId: {}, result: {}, use time: {}", event.getSessionId(),
                    riskResults, System.currentTimeMillis() - start);

        } catch (Exception e) {
            LOGGER.error("sync event receive error. sessionId: {}", event.getSessionId(), e);
        }
        return null;
    }
}
