package com.qed.demo8;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ThreadLocalRandom;


public class ChineseProverbServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    // 谚语列表
    private static final String[] DICT = {
            "只要功夫深，铁杵磨成针。",
            "老骥伏枥，志在千里。",
            "问君能有几多愁，恰似一江春水向东流。"
    };

    private String nextQuoto() {
        int quotoId = ThreadLocalRandom.current().nextInt(DICT.length);
        return DICT[quotoId];
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        String req = packet.content().toString(CharsetUtil.UTF_8);
        System.out.println(req);
        if ("谚语查询?".equalsIgnoreCase(req)) {
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(
                    "谚语查询结果：" + nextQuoto(), CharsetUtil.UTF_8
            ), packet.sender()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
