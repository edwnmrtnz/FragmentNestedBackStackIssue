package com.edwnmrtnz.fragmentplayground

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback

class FragmentC : Fragment(), FragmentNestListener {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null) {
            val c1 = FragmentC1()
            val tag = FragmentC1::class.java.name
            val ft = childFragmentManager.beginTransaction()
            ft.replace(R.id.flContainer, c1, tag)
            ft.commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.container, container, false)
    }

    override fun onDone(fragment: Fragment) {
        if(fragment is FragmentC1) {
            val c2 = FragmentC2()
            val tag = FragmentC2::class.java.name
            val ft = childFragmentManager.beginTransaction()
            ft.replace(R.id.flContainer, c2, tag)
            ft.addToBackStack(tag)
            ft.commit()
        } else if(fragment is FragmentC2) {
            val c3 = FragmentC3()
            val tag = FragmentC3::class.java.name
            val ft = childFragmentManager.beginTransaction()
            ft.replace(R.id.flContainer, c3, tag)
            ft.addToBackStack(tag)
            ft.commit()
        } else if(fragment is FragmentC3) {
            val d2 = FragmentD()
            val tag = FragmentD::class.java.name
            val ft = parentFragmentManager.beginTransaction()
            ft.replace(R.id.flContainer, d2, tag)
            ft.addToBackStack(tag)
            ft.commit()
        }
    }
}