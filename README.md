# futu-api
基于富途的官方版本，但是官方版本的FTAPI_Conn_Qot and FTAPI_Conn_Trd的接口方法是返回请求序列号，本项目调整后的接口会直接同步返回Response，其他均没有变化。
```java
  
  FTAPI_Conn_Trd trd = new FTAPI_Conn_Trd();
  ...                               // init connection
  TrdGetAccList.Request req = ..... // build request
  int serialNo = trd.getAccList(req)
```

```java
  
  FTAPI_Conn_Trd trd = new FTAPI_Conn_Trd();
  ...                               // init connection
  TrdGetAccList.Request req = ..... // build request
  TrdGetAccList.Response response = trd.getAccList(req)
```