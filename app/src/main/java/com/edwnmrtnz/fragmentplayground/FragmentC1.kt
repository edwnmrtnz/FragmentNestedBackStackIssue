package com.edwnmrtnz.fragmentplayground

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.activity.addCallback

class FragmentC1 : Fragment() {

    private lateinit var listener : FragmentNestListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = parentFragment as FragmentNestListener
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this) {
                val popped = childFragmentManager.popBackStackImmediate()
                if (!popped) {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireView().findViewById<TextView>(R.id.tvText).text = "C1"
        requireView().findViewById<AppCompatButton>(R.id.btnNext).setOnClickListener {
            listener.onDone(this)
        }
    }
}