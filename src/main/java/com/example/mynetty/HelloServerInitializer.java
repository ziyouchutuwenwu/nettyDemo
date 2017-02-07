package com.example.mynetty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("framer", new LengthFieldBasedFrameDecoder(1024*1024, 0, 2, 0, 2));

        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new MyEncoder());

        pipeline.addLast("handler", new HelloServerHandler());
    }
}