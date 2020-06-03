package com.mistletoe15.playandroid_mvvm.data.net

/**
 * 接口数据返回基类
 * Created by Mistletoe on 2020/5/29
 **/
data class BaseResp<T>(
    var errorCode: Int = 0,
    var errorMsg: String = "",
    var `data`: T
)
/**
 * 数据处理
 * errorCode = 0 代表执行成功，不建议依赖任何非0的 errorCode.
 * errorCode = -1001 代表登录失效，需要重新登录。
 */
fun <T> BaseResp<T>.handled(): T {
    if (errorCode ==  0 ) {
        return data
    } else {
        throw Exception(errorMsg)
    }
}