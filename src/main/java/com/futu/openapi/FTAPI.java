package com.futu.openapi;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class FTAPI {
    static NetEventListener netEventListener = new NetEventListener() {
    };

    public static void init() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void unInit() {

    }

    public static void setNetEventListener(NetEventListener listener) {
        if (listener != null) {
            netEventListener = listener;
        } else {
            netEventListener = new NetEventListener() {
            };
        }
    }

}
