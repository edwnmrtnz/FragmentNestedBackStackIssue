package com.edwnmrtnz.fragmentplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            val c1 = ContainerFragment()
            val tag = ContainerFragment::class.java.name
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.flContainer, c1, tag)
            ft.commit()
        }
    }
}