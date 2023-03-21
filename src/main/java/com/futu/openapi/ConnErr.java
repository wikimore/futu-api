package com.futu.openapi;

/**
 * 连接错误类型
 */
public enum ConnErr {
    /**
     * 没有错误
     */
    OK,
    /**
     * 连接失败
     */
    CONNECT_FAIL,
    /**
     * 连接超时
     */
    CONNECT_TIMEOUT,
    /**
     * 发送失败
     */
    SEND_FAIL,
    /**
     * 接收失败
     */
    READ_FAIL,
    /**
     * 主动关闭
     */
    CLOSE,
    /**
     * 远端已关闭
     */
    REMOTE_CLOSE
}

