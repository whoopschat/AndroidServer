package com.safframework.server.core

import com.safframework.server.core.handler.socket.SocketListener
import com.safframework.server.core.http.HttpMethod

/**
 *
 * @FileName:
 *          com.safframework.server.core.HttpServer
 * @author: Tony Shen
 * @date: 2020-03-21 15:42
 * @version: V1.0 <描述当前版本功能>
 */
interface HttpServer {

    @Throws(Exception::class)
    fun start()

    @Throws(Exception::class)
    fun close()

    fun get(route: String, handler: RequestHandler): HttpServer = request(HttpMethod.GET, route, handler)

    fun post(route: String, handler: RequestHandler): HttpServer = request(HttpMethod.POST, route, handler)

    fun put(route: String, handler: RequestHandler): HttpServer = request(HttpMethod.PUT, route, handler)

    fun delete(route: String, handler: RequestHandler): HttpServer = request(HttpMethod.DELETE, route, handler)

    fun head(route: String, handler: RequestHandler): HttpServer = request(HttpMethod.HEAD, route, handler)

    fun patch(route: String, handler: RequestHandler): HttpServer = request(HttpMethod.PATCH, route, handler)

    fun trace(route: String, handler: RequestHandler): HttpServer = request(HttpMethod.TRACE, route, handler)

    fun connect(route: String, handler: RequestHandler): HttpServer = request(HttpMethod.CONNECT, route, handler)

    fun options(route: String, handler: RequestHandler): HttpServer = request(HttpMethod.OPTIONS, route, handler)

    fun request(method: HttpMethod, route: String, handler: RequestHandler): HttpServer

    fun socket(webSocketPath:String?,listener: SocketListener<String>): HttpServer
}