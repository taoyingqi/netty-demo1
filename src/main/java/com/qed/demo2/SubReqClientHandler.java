package com.qed.demo2;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

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

    private SubscribeReq subReq(int i) {
        SubscribeReq req = new SubscribeReq();
        req.setAddress("南京市江宁区方山国家地质公园");
        req.setPhoneNumber("138xxxxxxxx");
        req.setProductName("Netty权威指南");
        req.setSubReqID(i);
        req.setUserName("Lilinfeng");
        return req;
    }


    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
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
