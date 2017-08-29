package com.sachin.risk.firewall.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author shicheng.zhang
 * @since 17-8-15 下午8:09
 */
public class EventDTO implements Serializable {

    /**
     * 事件类型
     */
    private String eventType;
    /**
     * 事件发生时间
     */
    private Date occurTime;
    /**
     * 服务器ID
     */
    private String serverId;
    /**
     * 请求类型
     */
    private String requestType;
    /**
     * 接口调用方组织ID
     */
    private String orgId;
    /**
     * 会话ID
     */
    private String sessionId = new Date().getTime() + "-" + UUID.randomUUID().toString();
    /**
     * 扩展信息
     */
    private Map<String, Object> extInfo;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, Object> extInfo) {
        this.extInfo = extInfo;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "eventType='" + eventType + '\'' +
                ", occurTime=" + occurTime +
                ", serverId='" + serverId + '\'' +
                ", requestType='" + requestType + '\'' +
                ", orgId='" + orgId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", extInfo=" + extInfo +
                '}';
    }
}
