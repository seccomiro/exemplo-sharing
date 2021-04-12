package com.ifpr.ifbank.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ifpr.ifbank.R
import kotlinx.android.synthetic.main.fragment_specify_amount.*
import kotlinx.android.synthetic.main.fragment_specify_amount.view.*


class SpecifyAmountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_specify_amount, container, false)

        view.txtName.text= arguments?.getString("txtName")

        view.btNext.setOnClickListener { nextTo(txtMoney.text.toString(),arguments?.getString("txtName").toString()) }
        return view
    }

    private fun nextTo(money :String,name :String) {
        val bundle = Bundle()
        bundle.putString("money", money)
        bundle.putString("txtName", name)
        findNavController().navigate(R.id.navigateToConfirmation, bundle)
    }


}
