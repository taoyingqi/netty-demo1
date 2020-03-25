package com.qed.demo7;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServer {
    public void run(int port) throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    // 将请求和应答的消息编码或解码为HTTP消息
                                    .addLast("http-codec", new HttpServerCodec())
                                    // 将HTTP消息的多个部分组合成一条完整的HTTP消息
                                    .addLast("aggregator", new HttpObjectAggregator(65536))
                                    //
                                    .addLast("http-chunked", new ChunkedWriteHandler())
                                    .addLast("handler", new WebSokectServerHandler());
                        }
                    });
            // 绑定端口，同步等待成功
            Channel ch = b.bind(port).sync().channel();
            System.out.println("Web socket server start at port: " + port);
            System.out.println("View in browser: http://localhost:" + port);

            // 等待服务端监听端口关闭
            ch.closeFuture().sync();
        } finally {
            // 优雅退出，释放线程池资源
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }

        new WebSocketServer().run(port);
    }
}
