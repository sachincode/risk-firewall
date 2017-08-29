package com.sachin.risk.firewall.listener;

import com.sachin.risk.common.data.loader.DictCacheLoader;
import com.sachin.risk.common.data.loader.EventTypeCacheLoader;
import com.sachin.risk.common.data.loader.EventTypePropertyCacheLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author shicheng.zhang
 * @since 17-8-17 下午9:03
 */
@Component
public class CacheLoaderListener implements ApplicationListener<ContextRefreshedEvent> {

    private final static Logger logger = LoggerFactory.getLogger(CacheLoaderListener.class);


    @Resource
    private EventTypeCacheLoader eventTypeCacheLoader;
    @Resource
    private EventTypePropertyCacheLoader eventTypePropertyCacheLoader;
    @Resource
    private DictCacheLoader dictCacheLoader;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (null != event.getApplicationContext().getParent()) {
            return;
        }

        normalCacheLoad();

    }

    /**
     * 初次加载缓存，出错即启动失败
     */
    private void normalCacheLoad() {
        long start = System.currentTimeMillis();
        eventTypeCacheLoader.load(true);
        logger.info("eventTypeCacheLoader load use time: {}", System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        eventTypePropertyCacheLoader.load(true);
        logger.info("eventTypePropertyCacheLoader load use time: {}", System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        dictCacheLoader.load(true);
        logger.info("dictCacheLoader load use time: {}", System.currentTimeMillis() - start);
    }
}
