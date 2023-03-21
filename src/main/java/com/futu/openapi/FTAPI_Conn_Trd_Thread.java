package com.futu.openapi;

import com.google.common.util.concurrent.SettableFuture;

public class FTAPI_Conn_Trd_Thread extends FTAPI_Conn_Trd {
  PacketExecutor packetExecutor = new PacketExecutor(this::handleReply, this::handlePush, this::handleInitConnect);

  void handleReply(ReqReplyType replyType, SettableFuture future, ProtoHeader protoHeader, byte[] data) {
    super.onReply(replyType, future, protoHeader, data);
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
  protected void onReply(ReqReplyType replyType, SettableFuture future, ProtoHeader protoHeader, byte[] data) {
    packetExecutor.addReply(replyType, future, protoHeader, data);
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
