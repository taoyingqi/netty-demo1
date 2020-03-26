package com.qed.demo9;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteOutput;

import java.io.IOException;

public class MyChannelBufferByteOutput implements ByteOutput {
    private final ByteBuf buffer;

    public MyChannelBufferByteOutput(ByteBuf buffer) {
        this.buffer = buffer;
    }

    public void close() throws IOException {
    }

    public void flush() throws IOException {
    }

    public void write(int b) throws IOException {
        this.buffer.writeByte(b);
    }

    public void write(byte[] bytes) throws IOException {
        this.buffer.writeBytes(bytes);
    }

    public void write(byte[] bytes, int srcIndex, int length) throws IOException {
        this.buffer.writeBytes(bytes, srcIndex, length);
    }

    ByteBuf getBuffer() {
        return this.buffer;
    }
}
