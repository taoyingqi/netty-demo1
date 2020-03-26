package com.qed.demo9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.util.CharsetUtil;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import java.util.HashMap;
import java.util.Map;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {
    MyMarshallingDecoder marshallingDecoder;

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        DefaultUnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
        this.marshallingDecoder = new MyMarshallingDecoder(provider);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        // LengthFieldBasedFrameDecoder 解码后返回的就是整包消息或者为空
        // 如果为空说明是个半包消息，直接返回继续I/O线程读取后续的码流
        if (frame == null) {
            return null;
        }
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(in.readInt());
        header.setLength(in.readInt());
        header.setSessionId(in.readLong());
        header.setType(in.readByte());
        header.setPriority(in.readByte());

        int size = in.readInt();
        if (size > 0) {
            Map<String, Object> attch = new HashMap<>();
            int keySize = 0;
            byte[] keyArray = null;
            String key = null;
            for (int i = 0; i < size; i++) {
                 keySize = in.readInt();
                 keyArray = new byte[keySize];
                 in.readBytes(keyArray);
                 key = new String(keyArray, CharsetUtil.UTF_8);
                 attch.put(key, marshallingDecoder.decode(ctx, in));
            }
            keyArray = null;
            key = null;
            header.setAttachment(attch);
        }
        if (in.readableBytes() > 4) {
            message.setBody(marshallingDecoder.decode(ctx, in));
        }
        message.setHeader(header);
        return message;
    }

}
