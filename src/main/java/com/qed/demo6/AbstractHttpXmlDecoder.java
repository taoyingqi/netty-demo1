package com.qed.demo6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class AbstractHttpXmlDecoder<T> extends MessageToMessageDecoder<T> {
//    private IBindingFactory factory;


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, T t, List<Object> list) throws Exception {

    }
}
