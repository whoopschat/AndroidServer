package com.safframework.androidserver.core.http

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.Channel
import io.netty.handler.codec.http.*
import io.netty.handler.codec.http.DefaultFullHttpResponse
import io.netty.util.AsciiString
import io.netty.util.CharsetUtil
import java.util.HashMap

/**
 *
 * @FileName:
 *          com.safframework.androidserver.core.http.HttpResponse
 * @author: Tony Shen
 * @date: 2020-03-24 11:37
 * @version: V1.0 <描述当前版本功能>
 */
class HttpResponse(private val channel:Channel) : Response {

    private var status: HttpResponseStatus? = null
    private var body: ByteBuf? = null
    private var headers: MutableMap<AsciiString, AsciiString>? = null

    override fun setStatus(status: HttpResponseStatus): Response {
        TODO("Not yet implemented")
    }

    override fun setBodyJson(serializeToJson: Any): Response {
        TODO("Not yet implemented")
    }

    override fun setBodyHtml(html: String): Response {
        TODO("Not yet implemented")
    }

    override fun setBodyData(contentType: String, data: ByteArray): Response {
        TODO("Not yet implemented")
    }

    override fun setBodyText(text: String): Response {
        val bytes = text.toByteArray(CharsetUtil.UTF_8)
        body = Unpooled.copiedBuffer(bytes)
        addHeader(HttpHeaderNames.CONTENT_TYPE, TEXT_PLAIN)
        return this
    }

    override fun addHeader(key: CharSequence, value: CharSequence): Response = addHeader(AsciiString.of(key), AsciiString.of(value))

    override fun addHeader(key: AsciiString, value: AsciiString): Response {
        if (headers == null) {
            headers = HashMap()
        }
        headers!![key] = value
        return this
    }

    private fun buildBodyData(): ByteBuf {
        return body ?: Unpooled.EMPTY_BUFFER
    }

    fun buildFullH1Response(): FullHttpResponse {
        var status = this.status
        if (status == null) {
            status = HttpResponseStatus.OK
        }

        val response = DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status!!, buildBodyData())
        response.headers().set(HttpHeaderNames.SERVER, SERVER_VALUE)
        headers?.forEach { (key, value) -> response.headers().set(key, value) }

        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, buildBodyData().readableBytes())
        return response
    }

    companion object {
        private val SERVER_VALUE = AsciiString.of("androidserver")
        private val JSON = AsciiString.cached("application/json")
        private val TEXT_HTML = AsciiString.cached("text/html")
        private val TEXT_PLAIN = AsciiString.cached("text/plain")
    }
}