package com.sachin.risk.firewall.service.core;

import com.sachin.risk.firewall.model.EventContext;

import java.util.Map;

/**
 * @author shicheng.zhang
 * @since 17-8-28 上午11:42
 */
public interface PropertyExtractService {

    /**
     * 根据事件类型属性配置抽取属性值
     * @param eventContext
     * @return
     */
    Map<String, Object> extractByProperties(EventContext eventContext);

    /**
     * 根据抽取规则抽取事件属性值
     * @param eventContext
     * @return
     */
    Map<String, Object> extractByRules(EventContext eventContext);
}
