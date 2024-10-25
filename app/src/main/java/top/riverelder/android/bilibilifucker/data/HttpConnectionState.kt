package top.riverelder.android.bilibilifucker.data

import java.nio.charset.Charset

data class HttpConnectionState(val uid: Int) {

    companion object {
        val STATUS_NOT_START = 0
        val STATUS_REQUESTING = 1
        val STATUS_RESPONSING = 2
        val STATUS_RESPONSE_READ = 3
    }

    var request: Any? = null
    var requestUrl: String = ""
    var response: Any? = null
    var responseBody: Any? = null
    var responseContent: ByteArray = ByteArray(0)
    var responseCharset: Charset = Charsets.UTF_8
    var status: Int = STATUS_NOT_START
}