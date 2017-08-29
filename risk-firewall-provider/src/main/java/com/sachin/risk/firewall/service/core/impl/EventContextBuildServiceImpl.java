package com.sachin.risk.firewall.service.core.impl;

import com.sachin.risk.firewall.model.EventContext;
import com.sachin.risk.firewall.model.EventDTO;
import com.sachin.risk.firewall.service.core.EventContextBuildService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shicheng.zhang
 * @since 17-8-28 上午11:29
 */
@Service
public class EventContextBuildServiceImpl implements EventContextBuildService {

    /**
     *
     * @param eventDTO
     * @return
     */
    @Override
    public List<EventContext> buildEventContext(EventDTO eventDTO) {
        // 在我们的系统中会对事件进行拆分逻辑，一般支付类型请求会按订单维度m和支付方式维度n进行拆分
        // 拆分后的事件数量为m*n, 并以卡维度作为主维度数量返回结果




        return null;
    }
}
