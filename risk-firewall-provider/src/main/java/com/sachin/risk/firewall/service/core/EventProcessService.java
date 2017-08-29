package com.sachin.risk.firewall.service.core;

import com.sachin.risk.firewall.model.EventDTO;
import com.sachin.risk.firewall.model.RiskResult;

import java.util.List;

/**
 * @author shicheng.zhang
 * @since 17-8-16 下午9:48
 */
public interface EventProcessService {

    /**
     * 同步事件处理
     * @param eventDTO
     * @return
     */
    List<RiskResult> processSync(EventDTO eventDTO);

    /**
     * 异步事件处理
     * @param eventDTO
     * @return
     */
    List<RiskResult> processAsync(EventDTO eventDTO);
}
