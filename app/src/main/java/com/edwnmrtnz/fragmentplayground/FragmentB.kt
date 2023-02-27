package com.edwnmrtnz.fragmentplayground

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class FragmentB : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireView().findViewById<TextView>(R.id.tvText).text = "B"
        requireView().findViewById<AppCompatButton>(R.id.btnNext).setOnClickListener {
            val ft = parentFragmentManager.beginTransaction()
            val fragment = FragmentC()
            val tag = FragmentC::class.java.name
            ft.replace(R.id.flContainer, fragment, tag)
            ft.addToBackStack(tag)
            ft.commit()
        }
    }
}