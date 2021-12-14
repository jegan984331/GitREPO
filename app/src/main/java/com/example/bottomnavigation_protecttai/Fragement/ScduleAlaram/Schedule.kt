package com.example.bottomnavigation_protecttai.Fragement.ScduleAlaram

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.bottomnavigation_protecttai.R
import com.example.bottomnavigation_protecttai.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import java.nio.channels.Channel
import java.util.*

//
class Schedule : Fragment() {

    lateinit var timePicker: MaterialTimePicker
    lateinit var calender: Calendar
    lateinit var alaramManager: AlarmManager
    lateinit var pendingIntent: PendingIntent
    lateinit var SHOWTIME: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lateinit var  binding:ActivityMainBinding


        lateinit var SeclectBTN: Button
        lateinit var SetBTN: Button
        lateinit var CanceltBTN: Button
        SeclectBTN =view.findViewById(R.id.buttonSelectAlarm)
        SetBTN =view.findViewById(R.id.buttonSetAlarm)
        CanceltBTN  =view.findViewById(R.id.buttonCancelAlarm)
        SHOWTIME=view.findViewById(R.id.TIME)

        CreateNOtification()

        SeclectBTN.setOnClickListener(View.OnClickListener {
            SelectTime()
        })
        SetBTN.setOnClickListener(View.OnClickListener {
            SetAlaram()

        })
        CanceltBTN.setOnClickListener(View.OnClickListener {
             CancelAlaram()
        })


    }

    private fun CancelAlaram() {
        alaramManager=activity?.getSystemService(ALARM_SERVICE)as AlarmManager
        val intent= Intent(activity,AlaramRicever::class.java)
        pendingIntent= PendingIntent.getBroadcast(activity,0,intent,0)
        alaramManager.cancel(pendingIntent)
        Toast.makeText(activity,"ALARAM CANCEL!!",Toast.LENGTH_SHORT).show()
    }

    private fun SetAlaram() {
        alaramManager=activity?.getSystemService(ALARM_SERVICE)as AlarmManager
        val intent= Intent(activity,AlaramRicever::class.java)
        pendingIntent= PendingIntent.getBroadcast(activity,0,intent,0)
        alaramManager.setRepeating(
            AlarmManager.RTC_WAKEUP,calender.timeInMillis,
            AlarmManager.INTERVAL_DAY,pendingIntent
        )
        Toast.makeText(activity,"ALARAM SUCCESFULLY SET!!",Toast.LENGTH_SHORT).show()
    }


    private fun SelectTime() {
         timePicker =MaterialTimePicker.Builder()
             .setHour(12)
             .setMinute(0)
             .setTitleText("SELECT ALARAM")
             .build()
        timePicker.show(activity?.supportFragmentManager!!,"FoxAndroid")
        timePicker.addOnPositiveButtonClickListener {
            if (timePicker.hour>=12){
                SHOWTIME.text= String.format("%2d",timePicker.hour - 12)+":"+ String.format("%2d",timePicker.minute)+ "PM"
            }else{
                SHOWTIME.text= String.format("%2d",timePicker.hour)+":"+ String.format("%2d",timePicker.minute)+ "AM"

            }
            calender= Calendar.getInstance()
            calender[Calendar.HOUR_OF_DAY]=timePicker.hour
            calender[Calendar.MINUTE]=timePicker.minute
            calender[Calendar.SECOND]=0
            calender[Calendar.MILLISECOND]=0
        }
    }

    private fun CreateNOtification() {
         if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
             val name:CharSequence ="Android Reminder"
             val description ="Alaram Manager"
             val impartance= NotificationManager.IMPORTANCE_HIGH
             val Channel=NotificationChannel("FoxAndroid",name,impartance)
             Channel.description=description
             val notificationManager =activity?.getSystemService(NotificationManager::class.java)
             notificationManager?.createNotificationChannel(Channel)
         }
    }



}

