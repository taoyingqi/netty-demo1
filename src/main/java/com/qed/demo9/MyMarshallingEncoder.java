package com.qed.demo9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import org.jboss.marshalling.Marshaller;

public class MyMarshallingEncoder {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    private final MarshallerProvider provider;

    public MyMarshallingEncoder(MarshallerProvider provider) {
        this.provider = provider;
    }

    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        Marshaller marshaller = null;
        try {
            marshaller = this.provider.getMarshaller(ctx);
            int lengthPos = out.writerIndex();
            out.writeBytes(LENGTH_PLACEHOLDER);
            MyChannelBufferByteOutput output = new MyChannelBufferByteOutput(out);
            marshaller.start(output);
            marshaller.writeObject(msg);
            marshaller.finish();
            marshaller.close();
            out.setInt(lengthPos, out.writerIndex() - lengthPos - 4);
        } finally {
            if (marshaller != null) {
                marshaller.close();
            }
        }
    }

}
