package com.futu.openapi;

import com.futu.openapi.pb.GetGlobalState;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Ted Wang
 */
public class MainTest {
  public static void main(String[] args) throws InterruptedException {
    FTAPI_Conn_Qot qot = new FTAPI_Conn_Qot();
    qot.setClientInfo("javaclient", 1);  //设置客户端信息
    CountDownLatch countDownLatch = new CountDownLatch(1);
    qot.setConnSpi(new FTSPI_Conn() {
      @Override
      public void onInitConnect(FTAPI_Conn client, long errCode, String desc) {
        System.out.println("onInitConnect begin");
        System.out.println(client);
        System.out.println(errCode);
        System.out.println(desc);
        System.out.println("onInitConnect end");
        countDownLatch.countDown();
      }

      @Override
      public void onDisconnect(FTAPI_Conn client, long errCode) {
        System.out.println("onDisconnect begin");
        System.out.println(client);
        System.out.println(errCode);
        System.out.println("onDisconnect end");
      }
    });  //设置连接回调
    qot.setQotSpi(new FTSPI_Qot() {
      @Override
      public void onReply_GetGlobalState(FTAPI_Conn client, int nSerialNo, GetGlobalState.Response rsp) {
        System.out.println("onReply_GetGlobalState begin");
        System.out.println(client);
        System.out.println(nSerialNo);
        System.out.println(rsp);
        System.out.println("onReply_GetGlobalState end");
      }
    });   //设置行情回调
    qot.initConnect("127.0.0.1", (short) 11111, false);
    countDownLatch.await();
    GetGlobalState.C2S c2s = GetGlobalState.C2S.newBuilder()
            .setUserID(0)
            .build();
    GetGlobalState.Request req = GetGlobalState.Request.newBuilder().setC2S(c2s).build();
    GetGlobalState.Response response = qot.getGlobalState(req);
    System.out.println("Sync response is " + response);
  }
}