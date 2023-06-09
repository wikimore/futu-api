package com.futu.openapi;

import com.futu.openapi.pb.*;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.concurrent.Future;

public class FTAPI_Conn_Trd extends FTAPI_Conn {
  private FTSPI_Trd trdSpi;
  private int trdSerialNo = 100;
  final private Object trdSpiLock = new Object();

  /***
   * 设置交易请求的回调
   * @param callback
   */
  synchronized public void setTrdSpi(FTSPI_Trd callback) {
    synchronized (trdSpiLock) {
      this.trdSpi = callback;
    }
  }

  synchronized public Common.PacketID nextPacketID() {
    ++trdSerialNo;
    Common.PacketID packetID = Common.PacketID.newBuilder().setConnID(getConnectID()).setSerialNo(trdSerialNo).build();
    return packetID;
  }

  /***
   * 获取交易账户列表，具体字段请参考Trd_GetAccList.proto协议
   * @param req
   * @return 请求的序列号
   */
  public TrdGetAccList.Response getAccList(TrdGetAccList.Request req) {
    Future<TrdGetAccList.Response> responseFuture = sendProto(ProtoID.TRD_GETACCLIST, req);
    TrdGetAccList.Response response = Futures.getUnchecked(responseFuture);
    return response;
  }

  /***
   * 解锁，具体字段请参考Trd_UnlockTrade.proto协议
   * @param req
   * @return 请求的序列号
   */
  public TrdUnlockTrade.Response unlockTrade(TrdUnlockTrade.Request req) {
    Future<TrdUnlockTrade.Response> responseFuture = sendProto(ProtoID.TRD_UNLOCKTRADE, req);
    TrdUnlockTrade.Response response = Futures.getUnchecked(responseFuture);
    return response;
  }

  /***
   * 订阅接收推送数据的交易账户，具体字段请参考Trd_SubAccPush.proto协议
   * @param req
   * @return 请求的序列号
   */
  public TrdSubAccPush.Response subAccPush(TrdSubAccPush.Request req) {
    Future<TrdSubAccPush.Response> responseFuture = sendProto(ProtoID.TRD_SUBACCPUSH, req);
    TrdSubAccPush.Response response = Futures.getUnchecked(responseFuture);
    return response;
  }

  /***
   * 获取账户资金，具体字段请参考Trd_GetFunds.proto协议
   * @param req
   * @return 请求的序列号
   */
  public TrdGetFunds.Response getFunds(TrdGetFunds.Request req) {
    Future<TrdGetFunds.Response> responseFuture = sendProto(ProtoID.TRD_GETFUNDS, req);
    TrdGetFunds.Response response = Futures.getUnchecked(responseFuture);
    return response;
  }

  /***
   * 获取账户持仓，具体字段请参考Trd_GetPositionList.proto协议
   * @param req
   * @return 请求的序列号
   */
  public TrdGetPositionList.Response getPositionList(TrdGetPositionList.Request req) {
    Future<TrdGetPositionList.Response> responseFuture = sendProto(ProtoID.TRD_GETPOSITIONLIST, req);
    TrdGetPositionList.Response response = Futures.getUnchecked(responseFuture);
    return response;
  }

  /***
   * 获取最大交易数量，具体字段请参考Trd_GetMaxTrdQtys.proto协议
   * @param req
   * @return 请求的序列号
   */
  public TrdGetMaxTrdQtys.Response getMaxTrdQtys(TrdGetMaxTrdQtys.Request req) {
    Future<TrdGetMaxTrdQtys.Response> responseFuture = sendProto(ProtoID.TRD_GETMAXTRDQTYS, req);
    TrdGetMaxTrdQtys.Response response = Futures.getUnchecked(responseFuture);
    return response;
  }

  /***
   * 获取当日订单列表，具体字段请参考Trd_GetOrderList.proto协议
   * @param req
   * @return 请求的序列号
   */
  public TrdGetOrderList.Response getOrderList(TrdGetOrderList.Request req) {
    Future<TrdGetOrderList.Response> responseFuture = sendProto(ProtoID.TRD_GETORDERLIST, req);
    TrdGetOrderList.Response response = Futures.getUnchecked(responseFuture);
    return response;
  }

  /***
   * 下单，具体字段请参考Trd_PlaceOrder.proto协议
   * @param req
   * @return 请求的序列号
   */
  public TrdPlaceOrder.Response placeOrder(TrdPlaceOrder.Request req) {
    Future<TrdPlaceOrder.Response> responseFuture = sendProto(ProtoID.TRD_PLACEORDER, req);
    TrdPlaceOrder.Response response = Futures.getUnchecked(responseFuture);
    return response;
  }

  /***
   * 修改订单，具体字段请参考Trd_ModifyOrder.proto协议
   * @param req
   * @return 请求的序列号
   */
  public TrdModifyOrder.Response modifyOrder(TrdModifyOrder.Request req) {
    Future<TrdModifyOrder.Response> responseFuture = sendProto(ProtoID.TRD_MODIFYORDER, req);
    TrdModifyOrder.Response response = Futures.getUnchecked(responseFuture);
    return response;
  }

  /***
   * 获取当日成交列表，具体字段请参考Trd_GetOrderFillList.proto协议
   * @param req
   * @return 请求的序列号
   */
  public TrdGetOrderFillList.Response getOrderFillList(TrdGetOrderFillList.Request req) {
    Future<TrdGetOrderFillList.Response> responseFuture = sendProto(ProtoID.TRD_GETORDERFILLLIST, req);
    TrdGetOrderFillList.Response response = Futures.getUnchecked(responseFuture);
    return response;
  }

  /***
   * 获取历史订单列表，具体字段请参考Trd_GetHistoryOrderList.proto协议
   * @param req
   * @return 请求的序列号
   */
  public TrdGetHistoryOrderList.Response getHistoryOrderList(TrdGetHistoryOrderList.Request req) {
    Future<TrdGetHistoryOrderList.Response> responseFuture = sendProto(ProtoID.TRD_GETHISTORYORDERLIST, req);
    TrdGetHistoryOrderList.Response response = Futures.getUnchecked(responseFuture);
    return response;
  }

  /***
   * 获取历史成交列表，具体字段请参考Trd_GetHistoryOrderFillList.proto协议
   * @param req
   * @return 请求的序列号
   */
  public TrdGetHistoryOrderFillList.Response getHistoryOrderFillList(TrdGetHistoryOrderFillList.Request req) {
    Future<TrdGetHistoryOrderFillList.Response> responseFuture = sendProto(ProtoID.TRD_GETHISTORYORDERFILLLIST, req);
    TrdGetHistoryOrderFillList.Response response = Futures.getUnchecked(responseFuture);
    return response;
  }

  /***
   * 获取获取融资融券数据，具体字段请参考Trd_GetMarginRatio.proto协议
   * @param req
   * @return 请求的序列号
   */
  public TrdGetMarginRatio.Response getMarginRatio(TrdGetMarginRatio.Request req) {
    Future<TrdGetMarginRatio.Response> responseFuture = sendProto(ProtoID.TRD_GETMARGINRATIO, req);
    TrdGetMarginRatio.Response response = Futures.getUnchecked(responseFuture);
    return response;
  }

  protected void onReply(ReqReplyType replyType, SettableFuture future, ProtoHeader protoHeader, byte[] data) {
    int protoID = protoHeader.nProtoID;
    int serialNo = protoHeader.nSerialNo;
    FTSPI_Trd trdSpi = null;
    synchronized (trdSpiLock) {
      if (this.trdSpi == null) {
        return;
      }
      trdSpi = this.trdSpi;
    }

    switch (protoID) {
      case ProtoID.TRD_GETACCLIST: {
        TrdGetAccList.Response rsp;
        if (replyType == ReqReplyType.SvrReply) {
          try {
            rsp = TrdGetAccList.Response.parseFrom(data);
          } catch (InvalidProtocolBufferException e) {
            rsp = TrdGetAccList.Response.newBuilder().setRetType(Common.RetType.RetType_Invalid_VALUE).build();
          }
        } else {
          rsp = TrdGetAccList.Response.newBuilder().setRetType(replyType.getCode()).build();
        }
        future.set(rsp);
        trdSpi.onReply_GetAccList(this, serialNo, rsp);
      }
      break;

      case ProtoID.TRD_GETFUNDS: {
        TrdGetFunds.Response rsp;
        if (replyType == ReqReplyType.SvrReply) {
          try {
            rsp = TrdGetFunds.Response.parseFrom(data);
          } catch (InvalidProtocolBufferException e) {
            rsp = TrdGetFunds.Response.newBuilder().setRetType(Common.RetType.RetType_Invalid_VALUE).build();
          }
        } else {
          rsp = TrdGetFunds.Response.newBuilder().setRetType(replyType.getCode()).build();
        }
        future.set(rsp);
        trdSpi.onReply_GetFunds(this, serialNo, rsp);
      }
      break;

      case ProtoID.TRD_GETHISTORYORDERFILLLIST: {
        TrdGetHistoryOrderFillList.Response rsp;
        if (replyType == ReqReplyType.SvrReply) {
          try {
            rsp = TrdGetHistoryOrderFillList.Response.parseFrom(data);
          } catch (InvalidProtocolBufferException e) {
            rsp = TrdGetHistoryOrderFillList.Response.newBuilder().setRetType(Common.RetType.RetType_Invalid_VALUE)
                    .build();
          }
        } else {
          rsp = TrdGetHistoryOrderFillList.Response.newBuilder().setRetType(replyType.getCode()).build();
        }
        future.set(rsp);
        trdSpi.onReply_GetHistoryOrderFillList(this, serialNo, rsp);
      }
      break;

      case ProtoID.TRD_GETHISTORYORDERLIST: {
        TrdGetHistoryOrderList.Response rsp;
        if (replyType == ReqReplyType.SvrReply) {
          try {
            rsp = TrdGetHistoryOrderList.Response.parseFrom(data);
          } catch (InvalidProtocolBufferException e) {
            rsp = TrdGetHistoryOrderList.Response.newBuilder().setRetType(Common.RetType.RetType_Invalid_VALUE).build();
          }
        } else {
          rsp = TrdGetHistoryOrderList.Response.newBuilder().setRetType(replyType.getCode()).build();
        }
        future.set(rsp);
        trdSpi.onReply_GetHistoryOrderList(this, serialNo, rsp);
      }
      break;

      case ProtoID.TRD_GETMAXTRDQTYS: {
        TrdGetMaxTrdQtys.Response rsp;
        if (replyType == ReqReplyType.SvrReply) {
          try {
            rsp = TrdGetMaxTrdQtys.Response.parseFrom(data);
          } catch (InvalidProtocolBufferException e) {
            rsp = TrdGetMaxTrdQtys.Response.newBuilder().setRetType(Common.RetType.RetType_Invalid_VALUE).build();
          }
        } else {
          rsp = TrdGetMaxTrdQtys.Response.newBuilder().setRetType(replyType.getCode()).build();
        }
        future.set(rsp);
        trdSpi.onReply_GetMaxTrdQtys(this, serialNo, rsp);
      }
      break;

      case ProtoID.TRD_GETORDERFILLLIST: {
        TrdGetOrderFillList.Response rsp;
        if (replyType == ReqReplyType.SvrReply) {
          try {
            rsp = TrdGetOrderFillList.Response.parseFrom(data);
          } catch (InvalidProtocolBufferException e) {
            rsp = TrdGetOrderFillList.Response.newBuilder().setRetType(Common.RetType.RetType_Invalid_VALUE).build();
          }
        } else {
          rsp = TrdGetOrderFillList.Response.newBuilder().setRetType(replyType.getCode()).build();
        }
        future.set(rsp);
        trdSpi.onReply_GetOrderFillList(this, serialNo, rsp);
      }
      break;

      case ProtoID.TRD_GETORDERLIST: {
        TrdGetOrderList.Response rsp;
        if (replyType == ReqReplyType.SvrReply) {
          try {
            rsp = TrdGetOrderList.Response.parseFrom(data);
          } catch (InvalidProtocolBufferException e) {
            rsp = TrdGetOrderList.Response.newBuilder().setRetType(Common.RetType.RetType_Invalid_VALUE).build();
          }
        } else {
          rsp = TrdGetOrderList.Response.newBuilder().setRetType(replyType.getCode()).build();
        }
        future.set(rsp);
        trdSpi.onReply_GetOrderList(this, serialNo, rsp);
      }
      break;

      case ProtoID.TRD_GETPOSITIONLIST: {
        TrdGetPositionList.Response rsp;
        if (replyType == ReqReplyType.SvrReply) {
          try {
            rsp = TrdGetPositionList.Response.parseFrom(data);
          } catch (InvalidProtocolBufferException e) {
            rsp = TrdGetPositionList.Response.newBuilder().setRetType(Common.RetType.RetType_Invalid_VALUE).build();
          }
        } else {
          rsp = TrdGetPositionList.Response.newBuilder().setRetType(replyType.getCode()).build();
        }
        future.set(rsp);
        trdSpi.onReply_GetPositionList(this, serialNo, rsp);
      }
      break;

      case ProtoID.TRD_MODIFYORDER: {
        TrdModifyOrder.Response rsp;
        if (replyType == ReqReplyType.SvrReply) {
          try {
            rsp = TrdModifyOrder.Response.parseFrom(data);
          } catch (InvalidProtocolBufferException e) {
            rsp = TrdModifyOrder.Response.newBuilder().setRetType(Common.RetType.RetType_Invalid_VALUE).build();
          }
        } else {
          rsp = TrdModifyOrder.Response.newBuilder().setRetType(replyType.getCode()).build();
        }
        future.set(rsp);
        trdSpi.onReply_ModifyOrder(this, serialNo, rsp);
      }
      break;


      case ProtoID.TRD_PLACEORDER: {
        TrdPlaceOrder.Response rsp;
        if (replyType == ReqReplyType.SvrReply) {
          try {
            rsp = TrdPlaceOrder.Response.parseFrom(data);
          } catch (InvalidProtocolBufferException e) {
            rsp = TrdPlaceOrder.Response.newBuilder().setRetType(Common.RetType.RetType_Invalid_VALUE).build();
          }
        } else {
          rsp = TrdPlaceOrder.Response.newBuilder().setRetType(replyType.getCode()).build();
        }
        future.set(rsp);
        trdSpi.onReply_PlaceOrder(this, serialNo, rsp);
      }
      break;


      case ProtoID.TRD_SUBACCPUSH: {
        TrdSubAccPush.Response rsp;
        if (replyType == ReqReplyType.SvrReply) {
          try {
            rsp = TrdSubAccPush.Response.parseFrom(data);
          } catch (InvalidProtocolBufferException e) {
            rsp = TrdSubAccPush.Response.newBuilder().setRetType(Common.RetType.RetType_Invalid_VALUE).build();
          }
        } else {
          rsp = TrdSubAccPush.Response.newBuilder().setRetType(replyType.getCode()).build();
        }
        future.set(rsp);
        trdSpi.onReply_SubAccPush(this, serialNo, rsp);
      }
      break;

      case ProtoID.TRD_UNLOCKTRADE: {
        TrdUnlockTrade.Response rsp;
        if (replyType == ReqReplyType.SvrReply) {
          try {
            rsp = TrdUnlockTrade.Response.parseFrom(data);
          } catch (InvalidProtocolBufferException e) {
            rsp = TrdUnlockTrade.Response.newBuilder().setRetType(Common.RetType.RetType_Invalid_VALUE).build();
          }
        } else {
          rsp = TrdUnlockTrade.Response.newBuilder().setRetType(replyType.getCode()).build();
        }
        future.set(rsp);
        trdSpi.onReply_UnlockTrade(this, serialNo, rsp);
      }
      break;

      case ProtoID.TRD_GETMARGINRATIO: {
        TrdGetMarginRatio.Response rsp;
        if (replyType == ReqReplyType.SvrReply) {
          try {
            rsp = TrdGetMarginRatio.Response.parseFrom(data);
          } catch (InvalidProtocolBufferException e) {
            rsp = TrdGetMarginRatio.Response.newBuilder().setRetType(Common.RetType.RetType_Invalid_VALUE).build();
          }
        } else {
          rsp = TrdGetMarginRatio.Response.newBuilder().setRetType(replyType.getCode()).build();
        }
        future.set(rsp);
        trdSpi.onReply_GetMarginRatio(this, serialNo, rsp);
      }
      break;
    }
  }

  @Override
  protected synchronized void onPush(ProtoHeader protoHeader, byte[] data) {
    int protoID = protoHeader.nProtoID;

    if (trdSpi == null) {
      return;
    }

    switch (protoID) {
      case ProtoID.TRD_UPDATEORDER: {
        try {
          TrdUpdateOrder.Response rsp = TrdUpdateOrder.Response.parseFrom(data);
          trdSpi.onPush_UpdateOrder(this, rsp);
        } catch (InvalidProtocolBufferException e) {
          TrdUpdateOrder.Response rsp = TrdUpdateOrder.Response.newBuilder()
                  .setRetType(Common.RetType.RetType_Invalid_VALUE).build();
          trdSpi.onPush_UpdateOrder(this, rsp);
        }
      }
      break;
      case ProtoID.TRD_UPDATEORDERFILL: {
        try {
          TrdUpdateOrderFill.Response rsp = TrdUpdateOrderFill.Response.parseFrom(data);
          trdSpi.onPush_UpdateOrderFill(this, rsp);
        } catch (InvalidProtocolBufferException e) {
          TrdUpdateOrderFill.Response rsp = TrdUpdateOrderFill.Response.newBuilder()
                  .setRetType(Common.RetType.RetType_Invalid_VALUE).build();
          trdSpi.onPush_UpdateOrderFill(this, rsp);
        }
      }
      break;
    }
  }
}

