package com.ifpr.lubank.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifpr.lubank.R
import kotlinx.android.synthetic.main.fragment_confirmation.view.*


class ConfirmationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_confirmation, container, false)

        view.txtName.text= arguments?.getString("txtName")
        view.txtMoney.text= arguments?.getString("money")

        return view
    }

}