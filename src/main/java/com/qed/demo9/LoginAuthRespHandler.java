package com.qed.demo9;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginAuthRespHandler extends ChannelHandlerAdapter {
    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<String, Boolean>();
    private String[] whiteList = {"127.0.0.1", "10.1.2.95"};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.LOGIN_REQ.value()) {
            String nodeIndex = ctx.channel().remoteAddress().toString();
            NettyMessage loginResp = null;
            //重复登录，拒绝
            if (nodeCheck.containsKey(nodeIndex)) {
                loginResp = buildResponse((byte) -1);
            } else {
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                boolean isOK = false;
                for (String WIP : whiteList) {
                    if (WIP.equals(ip)) {
                        isOK = true;
                    }
                }
                loginResp = isOK ? buildResponse((byte) 0) : buildResponse((byte) -1);
                if (isOK) nodeCheck.put(ip, true);
                System.out.println("The login response is :" + loginResp + " body[" + loginResp.getBody() + "]");
                ctx.writeAndFlush(loginResp);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildResponse(byte b) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(b);
        return message;
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        nodeCheck.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }

}
