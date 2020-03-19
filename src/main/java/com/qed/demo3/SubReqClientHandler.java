package com.qed.demo3;

import com.qed.demo3.protobuf.SubscribeReqProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.logging.Logger;

public class SubReqClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger.getLogger(SubReqClientHandler.class.getName());

    public SubReqClientHandler() {
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private SubscribeReqProto.SubscribeReq subReq(int i) {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(i)
                .setUserName("Lilinfeng")
                .setProductName("Netty权威指南")
                .setPhoneNumber("138xxxxxxxx")
                .addAllAddress(new ArrayList<String>() {{
                    add("NanJing YuHuaTai");
                    add("BeiJing LiuLiChang");
                    add("ShenZhen HongShuLin");
                }});
        return builder.build();
    }


    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 调用toString的时候，中文在控制台打印的是字节，而调用对应属性的getter方法的时候，是可以做中文的判断的
        System.out.println("Receive server response : [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
