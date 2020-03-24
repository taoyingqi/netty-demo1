package com.qed.demo6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HttpXmlClientHandler extends SimpleChannelInboundHandler<HttpXmlResponse> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 给客户端发送请求消息，HttpXmlRequest包含FullHttpRequest和Order这个了类
        HttpXmlRequest request = new HttpXmlRequest(null, OrderFactory.create(123));
        ctx.writeAndFlush(request);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, HttpXmlResponse msg) throws Exception {
        System.out.println("The client receive response of http header is : " + msg.getResponse().headers().names());
        System.out.println("The client receive response of http body is : " + msg.getResult());
    }

}
