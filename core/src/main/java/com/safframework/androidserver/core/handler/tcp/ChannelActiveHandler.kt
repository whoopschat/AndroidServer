package com.safframework.androidserver.core.handler.tcp

import com.safframework.androidserver.core.log.LogManager
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import java.net.InetSocketAddress

/**
 *
 * @FileName:
 *          com.safframework.androidserver.core.handler.tcp.ChannelActiveHandler
 * @author: Tony Shen
 * @date: 2020-03-25 13:45
 * @version: V1.0 <描述当前版本功能>
 */
@ChannelHandler.Sharable
class ChannelActiveHandler : ChannelInboundHandlerAdapter() {

    @Throws(Exception::class)
    override fun channelActive(ctx: ChannelHandlerContext) {

        val insocket = ctx.channel().remoteAddress() as InetSocketAddress
        val clientIP = insocket.address.hostAddress
        val clientPort = insocket.port

        LogManager.i("ChannelActiveHandler","新的连接：$clientIP : $clientPort")
//
//        mListener.onChannelConnect(ctx.channel())
    }

}