package com.futu.openapi;

public class FTAPI_Conn_Qot_Thread extends FTAPI_Conn_Qot{
    PacketExecutor packetExecutor = new PacketExecutor(this::handleReply, this::handlePush, this::handleInitConnect);

    void handleReply(ReqReplyType replyType, ProtoHeader protoHeader, byte[] data) {
        super.onReply(replyType, protoHeader, data);
    }

    void handlePush(ProtoHeader protoHeader, byte[] data) {
        super.onPush(protoHeader, data);
    }

    void handleInitConnect(long errCode, String desc) {
        super.onInitConnect(errCode, desc);
    }

    @Override
    public void close() {
        packetExecutor.close();
        super.close();
    }

    @Override
    protected void onReply(ReqReplyType replyType, ProtoHeader protoHeader, byte[] data) {
        packetExecutor.addReply(replyType, protoHeader, data);
    }

    @Override
    protected synchronized void onPush(ProtoHeader protoHeader, byte[] data) {
        packetExecutor.addPush(protoHeader, data);
    }

    @Override
    protected void onInitConnect(long errCode, String desc) {
        packetExecutor.addInitConnect(errCode, desc);
    }
}
