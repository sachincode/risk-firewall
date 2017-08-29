package com.sachin.risk.firewall.service.core;

import com.sachin.risk.firewall.model.EventContext;
import com.sachin.risk.firewall.model.EventDTO;

import java.util.List;

/**
 * @author shicheng.zhang
 * @since 17-8-28 上午11:29
 */
public interface EventContextBuildService {

    /**
     * 根据EventDTO构建防火墙流转的事件上下文，事件预处理
     * 可能涉及事件拆分、属性抽取等
     * @param eventDTO
     * @return
     */
    List<EventContext> buildEventContext(EventDTO eventDTO);
}
