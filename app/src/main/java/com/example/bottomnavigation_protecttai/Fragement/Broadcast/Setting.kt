package com.example.bottomnavigation_protecttai.Fragement.Broadcast

import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.bottomnavigation_protecttai.R
import java.util.jar.Manifest

class Setting : Fragment() {

       lateinit var receiver: Airplane

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_setting, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receiver = Airplane()

        Askpermission()
        ONOFFAIRPLAINMODE()



    }
    private fun Askpermission() {
        if (ContextCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.READ_PHONE_STATE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_PHONE_STATE),369)
        }
    }
    private fun ONOFFAIRPLAINMODE(){
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            activity?.registerReceiver(receiver, it)
        }
    }
        override fun onStop() {
            super.onStop()
          activity?. unregisterReceiver(receiver)
        }
    }


