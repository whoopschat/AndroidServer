package com.safframework.server.core.handler.websocket

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpServerCodec
import io.netty.handler.stream.ChunkedWriteHandler


/**
 *
 * @FileName:
 *          com.safframework.server.core.handler.websocket.NettyWebSocketServerInitializer
 * @author: Tony Shen
 * @date: 2020-11-19 15:23
 * @version: V1.0 <描述当前版本功能>
 */
class NettyWebSocketServerInitializer : ChannelInitializer<SocketChannel>() {

    override fun initChannel(ch: SocketChannel) {

        val pipeline = ch.pipeline()

        pipeline.addLast("http-codec", HttpServerCodec())
        pipeline.addLast("http-chunked", ChunkedWriteHandler()) //用于大数据的分区传输
        pipeline.addLast("aggregator", HttpObjectAggregator(65536))


    }

}