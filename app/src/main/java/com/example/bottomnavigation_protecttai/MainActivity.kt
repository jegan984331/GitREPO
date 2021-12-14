package com.example.bottomnavigation_protecttai

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

import androidx.fragment.app.Fragment
import com.example.bottomnavigation_protecttai.Fragement.Sharedpereference.Sharedpreference
import com.example.bottomnavigation_protecttai.Fragement.ScduleAlaram.Schedule
import com.example.bottomnavigation_protecttai.Fragement.Broadcast.Setting
import com.example.bottomnavigation_protecttai.Fragement.Service.ForeService

import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



              val BottomNAvigastion:BottomNavigationView

          val firstFragement= Sharedpreference()
          val secondFragement= Schedule()
          val threadFragement= Setting()
          val FourthFragement= ForeService()
          SetCurrentFragement(secondFragement)
          BottomNAvigastion=findViewById(R.id.Btomnav)



              BottomNAvigastion.setOnNavigationItemSelectedListener {
              when(it.itemId){
                  R.id.Dash-> SetCurrentFragement(firstFragement)
                  R.id.Shudule-> {SetCurrentFragement(secondFragement)}
                  R.id.Setting->{ SetCurrentFragement(threadFragement) }
                  R.id.Service->{ SetCurrentFragement(FourthFragement) }
              }
              true
          }
      }
    private fun SetCurrentFragement(fragment:Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.FirstFrame,fragment)
            commit()
        }
    }
}