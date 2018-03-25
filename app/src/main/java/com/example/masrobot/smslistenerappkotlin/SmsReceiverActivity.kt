package com.example.masrobot.smslistenerappkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_sms_receiver.*

class SmsReceiverActivity : AppCompatActivity(), View.OnClickListener {

    val EXTRA_SMS_NO: String = "extra_sms_no"
    val EXTRA_SMS_MESSAGE = "extra_sms_message"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_receiver)

        btn_close.setOnClickListener(this)

        var senderNo = intent.getStringExtra(EXTRA_SMS_NO)
        var senderMessage = intent.getStringExtra(EXTRA_SMS_MESSAGE)

        tv_no.text = "From : ${senderNo}"
        tv_message.text = senderMessage
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_close -> finish()
        }
    }
}
