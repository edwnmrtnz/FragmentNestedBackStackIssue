package com.edwnmrtnz.fragmentplayground

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback

class ContainerFragment : Fragment() {

    private val onBackPressedCallback = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            childFragmentManager.popBackStackImmediate()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        childFragmentManager.addOnBackStackChangedListener {
            onBackPressedCallback.isEnabled = childFragmentManager.backStackEntryCount > 0
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null) {
            val c1 = FragmentA()
            val tag = FragmentA::class.java.name
            val ft = childFragmentManager.beginTransaction()
            ft.replace(R.id.flContainer, c1, tag)
            ft.commit()
        }
    }
}