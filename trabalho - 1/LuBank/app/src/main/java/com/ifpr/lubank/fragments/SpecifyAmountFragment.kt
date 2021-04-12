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
import com.ifpr.lubank.databases.AppDatabase
import com.ifpr.lubank.models.Record
import com.ifpr.lubank.models.User
import com.ifpr.lubank.util.Util
import kotlinx.android.synthetic.main.fragment_specify_amount.view.*
import java.lang.Double.parseDouble


class SpecifyAmountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_specify_amount, container, false)

        val name = arguments?.getString("name")

        view.txtName.text = name

        if (arguments?.getBoolean("flag")!!) {
            // deposit

            view.txtMsg.text = getString(R.string.receiving)
            view.txtName.text = ""

            view.btNext.text = getString(R.string.receive)

            view.btNext.setOnClickListener {
                name?.let { name ->
                    nextToWithRercordForReceive(
                        name,
                        parseDouble(view.txtMoney.text.toString())
                    )
                }
            }

        } else {
            // send


            val name = view.txtName.text.toString()


            if (name != "") {

                view.btNext.setOnClickListener {

                    name?.let { name ->
                        nextToWithRercordForSending(
                            name,
                            parseDouble(view.txtMoney.text.toString())
                        )
                    }

                }

            }

        }

        view.btCancel.setOnClickListener { callCancel() }
        return view
    }

    private fun callCancel() {
        findNavController().navigate(R.id.navigateToHomeFromSpecify)
    }


    private fun nextToWithRercordForReceive(name: String, value: Double) {


        if (value > 0) {
            val r = Util.user.id?.let {
                name?.let { name ->
                    Record(
                        name,
                        "",
                        value,
                        it,
                        true
                    )
                }
            }
            r?.let { AppDatabase.getInstance(requireContext()).recordsDao().insert(it) }

            Util.user.balance += value
            val u = Util.user

            val bundle = Bundle()
            bundle.putString("txtName", getString(R.string.yourself))
            bundle.putString("money", value.toString())

            AppDatabase.getInstance(requireContext()).usersDao().update(u)

            findNavController().navigate(R.id.navigateToConfirmation,bundle)
        } else {
            Toast.makeText(requireContext(), R.string.msg_value_erro, Toast.LENGTH_SHORT).show()
        }
    }

    private fun nextToWithRercordForSending(name: String, value: Double) {


        val balance = Util.user.balance

        if (value > 0 && value <= balance) {
            val r = Util.user.id?.let {
                name?.let { name ->
                    Record(
                        name,
                        "",
                        value,
                        it,
                        false
                    )
                }
            }


            Util.user.balance -= value
            val u = Util.user

            r?.let { AppDatabase.getInstance(requireContext()).recordsDao().insert(it) }
            AppDatabase.getInstance(requireContext()).usersDao().update(u)

            val bundle = Bundle()
            bundle.putString("txtName", name)
            bundle.putString("money", value.toString())

            findNavController().navigate(R.id.navigateToConfirmation,bundle)

        } else {
            Toast.makeText(requireContext(), R.string.msg_value_erro, Toast.LENGTH_SHORT).show()
        }
    }
}