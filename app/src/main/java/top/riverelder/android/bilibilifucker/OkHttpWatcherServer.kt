package top.riverelder.android.bilibilifucker

import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import top.riverelder.android.bilibilifucker.Utils.log
import top.riverelder.android.bilibilifucker.data.HttpConnectionState
import java.net.Inet4Address
import java.net.InetSocketAddress
import java.net.NetworkInterface

class OkHttpWatcherServer(port: Int) : WebSocketServer(InetSocketAddress(port)) {
    override fun onOpen(conn: WebSocket, handshake: ClientHandshake?) {
        log("Connected: ${conn.remoteSocketAddress}")
    }

    override fun onClose(conn: WebSocket, code: Int, reason: String?, remote: Boolean) {
        log("Disconnected: ${conn.remoteSocketAddress}")
        log("- remote: $remote")
        if (reason != null) log("- reason: $reason")
    }

    override fun onMessage(conn: WebSocket, message: String?) {
        log("message: $message")
    }

    override fun onError(conn: WebSocket?, ex: Exception?) {
        log("Error: $ex")
    }

    override fun onStart() {
        val networkInterfaces = NetworkInterface.getNetworkInterfaces()

        var ipString = ""
        for (networkInterface in networkInterfaces) {
            val addresses = networkInterface.inetAddresses
            for (address in addresses) {
                if (address !is Inet4Address) continue

                val ip = address.hostAddress
                if (ip != null && ip != "" && ip != "127.0.0.1" && ip != "0.0.0.0" && ip != "::::") {
                    ipString = ip
                    break
                }
            }
            if (ipString != "") break
        }
        log("${javaClass.simpleName} started at ws://$ipString:$port/")
    }

    fun broadcastState(state: HttpConnectionState) {
        val data = """
            {
                "uid": ${state.uid},
                "request": { 
                    "url": "${state.requestUrl.escape()}"
                },
                "response": {
                    "body": {
                        "charset": "${state.responseCharset.name().escape()}",
                        "content": "${state.responseContent.toHexString()}"
                    }
                }
            }
        """.trimIndent()
//        if (state.status == HttpConnectionState.STATUS_RESPONSE_READ) {
//            log(state.responseContent.toString())
//        }
        broadcast(data)
    }
}

val REG_ESCAPE = Regex("[\t\r\n\"\'\$]")
fun String.escape(): String = this.replace(REG_ESCAPE) {
    when (val ch = it.value) {
        "\t" -> "\\t"
        "\r" -> "\\r"
        "\n" -> "\\n"
        "\"" -> "\\\""
        "\'" -> "\\'"
        "\$" -> "\\$"
        else -> ch
    }
}

fun ByteArray.toHexString(): String = this.joinToString("") { it.toUByte().toString(16).padStart(2, '0') }