package com.ifpr.ifbank.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ifpr.ifbank.R
import kotlinx.android.synthetic.main.fragment_choose_recipient.*
import kotlinx.android.synthetic.main.fragment_choose_recipient.view.*
import kotlinx.android.synthetic.main.fragment_choose_recipient.view.btSend

class ChooseRecipientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_choose_recipient, container, false)

        view.btSend.setOnClickListener { nextTo(txtName.text.toString()) }

        return view
    }
    private fun nextTo(name: String) {
        val bundle = Bundle()
        bundle.putString("txtName", name)
        findNavController().navigate(R.id.navigateToRecipientFragment, bundle)
    }

}
