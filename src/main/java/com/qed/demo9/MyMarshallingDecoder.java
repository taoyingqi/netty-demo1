package com.qed.demo9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;
import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.Unmarshaller;

public class MyMarshallingDecoder {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    private final UnmarshallerProvider provider;

    public MyMarshallingDecoder(UnmarshallerProvider provider) {
        this.provider = provider;
    }

    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = this.provider.getUnmarshaller(ctx);
            int objectSize = in.readInt();
            ByteBuf buf = in.slice(in.readerIndex(), objectSize);
            ByteInput input = new MyChannelBufferByteInput(buf);
            unmarshaller.start(input);
            Object obj = unmarshaller.readObject();
            unmarshaller.finish();
            in.readerIndex(in.readerIndex() + objectSize);
            return obj;
        } finally {
            if (unmarshaller != null) {
                unmarshaller.close();
            }
        }
    }

}
