package com.raywenderlich.multiplicationtable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.raywenderlich.multiplicationtable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
}