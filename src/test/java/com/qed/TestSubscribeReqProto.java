package com.qed;

import com.google.protobuf.InvalidProtocolBufferException;
import com.qed.demo3.protobuf.SubscribeReqProto;

import java.util.ArrayList;

public class TestSubscribeReqProto {

    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body)
            throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1)
                .setUserName("Lilinfeng")
                .setProductName("Netty Book")
                .setPhoneNumber("138xxxxxxxx")
                .addAllAddress(new ArrayList<String>() {{
                    add("NanJing YuHuaTai");
                    add("BeiJing LiuLiChang");
                    add("ShenZhen HongShuLin");
                }});
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("Before encode : " + req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
        System.out.println("After decode : " + req2.toString());
        System.out.println("Assets equal : " + req2.equals(req));
    }

}
