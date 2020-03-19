package com.qed.demo1;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

public class EchoClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger.getLogger(EchoClientHandler.class.getName());

    private int counter;
    static final  String ECHO_REQ = "HI, Lilinfeng, Welcome to Netty.S_";

    public EchoClientHandler() {
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("Now is : " + body + " ; the counter is : " + ++counter);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 释放资源
        logger.warning("Unexcepted exception from downstream " + cause.getMessage());
        ctx.close();
    }
}
