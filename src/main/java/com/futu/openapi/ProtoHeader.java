package com.futu.openapi;

import com.google.common.base.MoreObjects;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ProtoHeader {
  public byte[] szHeaderFlag = {'F', 'T'};
  public int nProtoID;
  public byte nProtoFmtType = 0;
  public byte nProtoVer = 0;
  public int nSerialNo;
  public int nBodyLen;
  public byte[] arrBodySHA1;
  public byte[] arrReserved = {0, 0, 0, 0, 0, 0, 0, 0};

  public static final int HEADER_SIZE = 2 + 4 + 1 + 1 + 4 + 4 + 20 + 8;

  public static ProtoHeader parse(byte[] data, int offset) {
    if (offset + HEADER_SIZE > data.length) return null;
    ByteBuffer buffer = ByteBuffer.wrap(data, offset, data.length - offset);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    ProtoHeader header = new ProtoHeader();
    buffer.get(header.szHeaderFlag);
    header.nProtoID = buffer.getInt();
    header.nProtoFmtType = buffer.get();
    header.nProtoVer = buffer.get();
    header.nSerialNo = buffer.getInt();
    header.nBodyLen = buffer.getInt();
    header.arrBodySHA1 = new byte[20];
    buffer.get(header.arrBodySHA1);
    buffer.get(header.arrReserved);
    return header;
  }

  public static ProtoHeader parse(ByteBuf buf) {
    ProtoHeader header = new ProtoHeader();
    buf.readBytes(header.szHeaderFlag);
    header.nProtoID = buf.readIntLE();
    header.nProtoFmtType = buf.readByte();
    header.nProtoVer = buf.readByte();
    header.nSerialNo = buf.readIntLE();
    header.nBodyLen = buf.readIntLE();
    header.arrBodySHA1 = new byte[20];
    buf.readBytes(header.arrBodySHA1);
    buf.readBytes(header.arrReserved);
    return header;
  }

  public void write(byte[] dst) {
    if (dst.length < HEADER_SIZE) throw new IllegalArgumentException("dst capacity not enough");

    ByteBuffer buffer = ByteBuffer.wrap(dst);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    buffer.put(szHeaderFlag);
    buffer.putInt(nProtoID);
    buffer.put(nProtoFmtType);
    buffer.put(nProtoVer);
    buffer.putInt(nSerialNo);
    buffer.putInt(nBodyLen);
    buffer.put(arrBodySHA1);
    buffer.put(arrReserved);
  }

  public void write(ByteBuf dst) {
    dst.writeBytes(szHeaderFlag).writeIntLE(nProtoID).writeByte(nProtoFmtType).writeByte(nProtoVer)
            .writeIntLE(nSerialNo).writeIntLE(nBodyLen).writeBytes(arrBodySHA1).writeBytes(arrReserved);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
            .add("szHeaderFlag", szHeaderFlag)
            .add("nProtoID", nProtoID)
            .add("nProtoFmtType", nProtoFmtType)
            .add("nProtoVer", nProtoVer)
            .add("nSerialNo", nSerialNo)
            .add("nBodyLen", nBodyLen)
            .add("arrBodySHA1", arrBodySHA1)
            .add("arrReserved", arrReserved)
            .toString();
  }
}