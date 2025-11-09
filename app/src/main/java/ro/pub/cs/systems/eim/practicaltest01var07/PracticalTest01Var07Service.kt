package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Service
import android.content.Intent
import android.os.IBinder

class PracticalTest01Var07Service : Service() {
    var processingThread = MyThread(this)
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        processingThread.start()
        return Service.START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        processingThread.stopThread()
        super.onDestroy()
    }
}