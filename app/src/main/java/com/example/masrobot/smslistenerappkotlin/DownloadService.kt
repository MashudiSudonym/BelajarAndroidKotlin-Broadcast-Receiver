package com.example.masrobot.smslistenerappkotlin

import android.app.IntentService
import android.content.Intent
import android.util.Log


/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 *
 * TODO: Customize class - update intent actions and extra parameters.
 */
class DownloadService : IntentService("DownloadService") {

    val TAG = "DownloadService"

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "Download Service dijalankan")

        if (intent != null) {
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val notifyFinishIntent = Intent(MainActivity().ACTION_DOWNLOAD_STATUS)
            sendBroadcast(notifyFinishIntent)
        }
    }

}
