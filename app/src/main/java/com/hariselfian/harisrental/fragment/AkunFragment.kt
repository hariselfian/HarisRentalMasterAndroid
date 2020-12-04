package com.hariselfian.harisrental.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.hariselfian.harisrental.R
import com.hariselfian.harisrental.helper.SharedPref

/**
 * Kode dibuat dengan cinta oleh Haris Elfian.
 */
class AkunFragment : Fragment() {

    lateinit var s: SharedPref
    lateinit var btnLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_akun, container, false)
        btnLogout = view.findViewById(R.id.btn_logout)

        s = SharedPref(activity!!)

        btnLogout.setOnClickListener {
            s.setStatusLogin(false)
        }

        return view

    }
}