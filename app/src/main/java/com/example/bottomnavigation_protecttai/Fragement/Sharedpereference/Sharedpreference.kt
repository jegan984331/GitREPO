package com.example.bottomnavigation_protecttai.Fragement.Sharedpereference

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bottomnavigation_protecttai.R


class Sharedpreference : Fragment() {
    private val sharedPrefFile = "kotlinAndroid"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? { return inflater.inflate(R.layout.fragment_dash_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inputId = view.findViewById<EditText>(R.id.editId)
        val inputName = view.findViewById<EditText>(R.id.editName)
        val inputGender = view.findViewById<EditText>(R.id.textViewShowGender)
        val inputDes =view. findViewById<EditText>(R.id.textViewShowDesignation)

        val btnSave = view.findViewById<Button>(R.id.save)
        val btnView =view. findViewById<Button>(R.id.view)


        val sharedPreferences: SharedPreferences? = this.activity?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        btnSave.setOnClickListener(View.OnClickListener {
            val id:Int = Integer.parseInt(inputId.text.toString())
            val name:String = inputName.text.toString()
           val Gender:String= inputGender.text.toString()
           val DES:String= inputDes.text.toString()
            val editor:SharedPreferences.Editor= sharedPreferences!!.edit()
            editor.putInt("id_key",id)
            editor.putString("name_key",name)
            editor.putString("gender_key",Gender)
            editor.putString("DES_key",DES)
            editor.apply()
            editor.commit()
            Toast.makeText(activity,"ADD DETAILS SUCCESFULL!!",Toast.LENGTH_SHORT).show()
        })
        btnView.setOnClickListener {
            val intent = Intent (activity, view_perference::class.java)
           activity?.startActivity(intent)

        }
    }
}


