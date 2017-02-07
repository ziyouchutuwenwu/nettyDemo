package com.example.mynetty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyEncoder extends MessageToByteEncoder<CharSequence> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CharSequence msg, ByteBuf out) throws Exception {

        if( msg.length() != 0 ) {

            //包头内数据长度不包括包头长度
            byte[] headerSizeBytes = new byte[PackageHeader.size()];
            byte[] sendBuffer = new byte[1024*1024];

            int dataSize = msg.toString().getBytes().length;
            int totalSendSize = headerSizeBytes.length + dataSize;

            byte[] dataSizeBytes = PackageHeader.setDataSizeBytes(dataSize);

            System.arraycopy(dataSizeBytes, 0, headerSizeBytes, 0, dataSizeBytes.length);

            System.arraycopy(headerSizeBytes, 0, sendBuffer, 0, headerSizeBytes.length);
            System.arraycopy(msg.toString().getBytes(), 0, sendBuffer, headerSizeBytes.length, dataSize);

            out.writeBytes(sendBuffer, 0, totalSendSize);
        }
    }
}