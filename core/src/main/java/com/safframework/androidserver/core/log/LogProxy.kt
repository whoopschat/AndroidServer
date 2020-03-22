package com.safframework.androidserver.core.log

/**
 *
 * @FileName:
 *          com.safframework.androidserver.core.log.LogProxy
 * @author: Tony Shen
 * @date: 2020-03-21 20:23
 * @version: V1.0 <描述当前版本功能>
 */
interface LogProxy {

    fun e(tag:String , msg:String)

    fun w(tag:String , msg:String)

    fun i(tag:String , msg:String)

    fun d(tag:String , msg:String)
}