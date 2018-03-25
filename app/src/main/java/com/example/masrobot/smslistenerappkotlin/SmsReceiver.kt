package com.example.masrobot.smslistenerappkotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import android.R.attr.phoneNumber
import android.support.v4.content.ContextCompat.startActivity






class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras

        try {
            if (bundle != null) {
                val pdusObj = bundle.get("pdus") as Array<Any>

                for (i in 0 until pdusObj.size) {
                    val currentMessage = getIncomingMessage(pdusObj[i], bundle)
                    val phoneNumber = currentMessage.displayOriginatingAddress
                    val senderNum = phoneNumber
                    val message = currentMessage.displayMessageBody
                    Log.i("SmsReceiver", "senderNum: $senderNum; message: $message")
                    val showSmsIntent = Intent(context, SmsReceiverActivity::class.java)
                    showSmsIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    showSmsIntent.putExtra(SmsReceiverActivity().EXTRA_SMS_NO, phoneNumber)
                    showSmsIntent.putExtra(SmsReceiverActivity().EXTRA_SMS_MESSAGE, message)
                    context.startActivity(showSmsIntent)
                }
            }
        } catch (e: Exception) {
            Log.e("SmsReceiver", "Exception smsReceiver ${e}")
        }
    }

    private fun getIncomingMessage(aObject: Any, bundle: Bundle): SmsMessage {
        val currentSMS: SmsMessage
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val format = bundle.getString("format")
            currentSMS = SmsMessage.createFromPdu(aObject as ByteArray, format)
        } else {
            currentSMS = SmsMessage.createFromPdu(aObject as ByteArray)
        }
        return currentSMS
    }
}
