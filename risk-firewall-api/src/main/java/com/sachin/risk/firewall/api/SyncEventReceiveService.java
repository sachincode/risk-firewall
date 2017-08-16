package com.sachin.risk.firewall.api;

import com.sachin.risk.firewall.model.EventDTO;
import com.sachin.risk.firewall.model.RiskResult;

import java.util.List;

/**
 * @author shicheng.zhang
 * @since 17-8-15 下午8:39
 */
public interface SyncEventReceiveService {

    /**
     * 接收同步事件方法
     * @param event 同步事件
     * @return 风控结果
     */
    public List<RiskResult> receiveEventDTO(EventDTO event);
}
