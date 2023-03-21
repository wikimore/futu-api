package com.futu.openapi;

import com.google.common.util.concurrent.SettableFuture;

import java.util.concurrent.LinkedBlockingQueue;

enum PacketType {
  Quit(0),
  Reply(1),
  Push(2),
  InitConnect(3);

  PacketType(int type) {
  }
}

class PacketData {
  PacketType packetType;
  ReqReplyType replyType;
  ProtoHeader protoHeader;
  SettableFuture future;
  byte[] data;
  long initConnectErrCode;
  String initConnectErrMsg;
}

@FunctionalInterface
interface ReplyHandler {
  void handleReply(ReqReplyType replyType, SettableFuture future, ProtoHeader protoHeader, byte[] data);
}

@FunctionalInterface
interface PushHandler {
  void handlePush(ProtoHeader protoHeader, byte[] data);
}

@FunctionalInterface
interface InitConnectHandler {
  void handleInitConnect(long errCode, String desc);
}

public class PacketExecutor {
  Thread execThread;
  ReplyHandler replyHandler;
  PushHandler pushHandler;
  InitConnectHandler initConnectHandler;
  LinkedBlockingQueue<PacketData> packetQueue = new LinkedBlockingQueue<>();
  volatile boolean isActive = true;

  public PacketExecutor(ReplyHandler replyHandler, PushHandler pushHandler, InitConnectHandler initConnectHandler) {
    this.replyHandler = replyHandler;
    this.pushHandler = pushHandler;
    this.initConnectHandler = initConnectHandler;
    this.execThread = new Thread(this::loop, "PacketExecutor");
    this.execThread.setDaemon(true);
    this.execThread.start();
  }

  public void addReply(ReqReplyType replyType, SettableFuture future, ProtoHeader protoHeader, byte[] data) {
    PacketData packetData = new PacketData();
    packetData.replyType = replyType;
    packetData.protoHeader = protoHeader;
    packetData.data = data;
    packetData.future = future;
    packetData.packetType = PacketType.Reply;

    this.packetQueue.add(packetData);
  }

  public void addPush(ProtoHeader protoHeader, byte[] data) {
    PacketData packetData = new PacketData();
    packetData.replyType = ReqReplyType.SvrReply;
    packetData.protoHeader = protoHeader;
    packetData.data = data;
    packetData.packetType = PacketType.Push;
    this.packetQueue.add(packetData);
  }

  public void addInitConnect(long errCode, String desc) {
    PacketData packetData = new PacketData();
    packetData.packetType = PacketType.InitConnect;
    packetData.initConnectErrCode = errCode;
    packetData.initConnectErrMsg = desc;
    this.packetQueue.add(packetData);
  }

  public void close() {
    isActive = false;
    PacketData packetData = new PacketData();
    packetData.packetType = PacketType.Quit;
    this.packetQueue.add(packetData);
  }

  void loop() {
    while (true) {
      try {
        PacketData packetData = this.packetQueue.take();
        if (!isActive)
          break;
        switch (packetData.packetType) {
          case Reply:
            replyHandler.handleReply(packetData.replyType, null,packetData.protoHeader, packetData.data);
            break;
          case Push:
            pushHandler.handlePush(packetData.protoHeader, packetData.data);
            break;
          case InitConnect:
            initConnectHandler.handleInitConnect(packetData.initConnectErrCode, packetData.initConnectErrMsg);
            break;
        }
      } catch (InterruptedException ex) {
        break;
      }
    }
  }
}
