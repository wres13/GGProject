package com.tools.jj.tools.dto;

/**
 * Author : zhouyx
 * Date   : 2016/8/16
 * EventBus通信类
 */
public class MessageEventDto {

    private int eventCode = -1;
    private Object data;

    public MessageEventDto(int eventCode) {
        this(eventCode, null);
    }

    public MessageEventDto(int eventCode, Object data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    public int getEventCode() {
        return eventCode;
    }

    public Object getData() {
        return data;
    }

}
