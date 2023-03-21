package com.futu.openapi;

public interface NetEventListener {
    default void onBeginSend(FTAPI_Conn conn, int protoID) {}
    default void onEndSend(FTAPI_Conn conn, int protoID, int serialNO){}
    default void onBeginRecv(FTAPI_Conn conn, int protoID, int serialNO){}
    default void onEndRecv(FTAPI_Conn conn, int protoID, int serialNO){}
    default void onConnect(long localConnID, ConnErr err, String msg){}
}
