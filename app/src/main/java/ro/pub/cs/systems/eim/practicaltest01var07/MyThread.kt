package ro.pub.cs.systems.eim.practicaltest01var07

import android.content.Context
import android.content.Intent
import android.util.Log
import kotlin.random.Random

class MyThread(var context: Context) : Thread() {
    companion object {
        val SLEEP_TIME = 10000L
    }
    var random: Random = Random(42)
    var isRunning = true

    override fun run() {
        Log.d("DEBUG", "THREAD START")
        while(isRunning) {
            sendMessage()
            try {
                Thread.sleep(SLEEP_TIME)
            } catch (e: InterruptedException) {
                Log.d("IDK", "THREAD STOP")
                e.printStackTrace()
            }
        }
    }

    fun sendMessage() {
        var intent = Intent()
        intent.setAction("SEND_MESSAGE")

        intent.putExtra("R1", random.nextInt())
        intent.putExtra("R2", random.nextInt())
        intent.putExtra("R3", random.nextInt())
        intent.putExtra("R4", random.nextInt())

        context.sendBroadcast(intent)
    }

    fun stopThread() {
        isRunning = false
    }
}