package com.ifpr.lubank.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ifpr.lubank.R
import com.ifpr.lubank.util.Util
import kotlinx.android.synthetic.main.fragment_choose_recipient.view.*


class ChooseRecipientFragment : Fragment() {
    var flag: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_choose_recipient, container, false)


        view.sw.setOnCheckedChangeListener { _, isChecked ->
            setFields(isChecked)
            flag = isChecked
        }

        view.btSend.setOnClickListener { nextTo(view.txtName.text.toString(), flag) }
        view.btCancel.setOnClickListener { callCancel() }

        return view
    }

    private fun nextTo(name: String, status: Boolean) {
        if (view?.txtName?.text?.isNotEmpty()!!) {

            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putBoolean("flag", status)
            findNavController().navigate(R.id.navigateToSpecifyAmount, bundle)

        } else {
            Toast.makeText(activity, R.string.name_isempty, Toast.LENGTH_SHORT).show()
        }
    }

    private fun callCancel() {
        findNavController().navigate(R.id.navigateToHome)
    }


    private fun setFields(b: Boolean) {
        if (b) {
            view?.textView?.text = getString(R.string.receiving)
            view?.btSend?.text = getString(R.string.receive)
            view?.txtName?.setText(Util.user.username)
//            view?.txtName?.isEnabled = false
        } else {
            view?.textView?.text = getString(R.string.sending)
            view?.btSend?.text = getString(R.string.send)
//            view?.txtName?.isEnabled = true
            view?.txtName?.setText("")
        }
    }
}