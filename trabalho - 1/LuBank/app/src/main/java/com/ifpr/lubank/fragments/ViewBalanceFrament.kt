package com.ifpr.lubank.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifpr.lubank.R
import com.ifpr.lubank.databases.AppDatabase
import com.ifpr.lubank.util.Util
import kotlinx.android.synthetic.main.fragment_view_balance_frament.view.*

class ViewBalanceFrament : Fragment() {


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_balance_frament, container, false)
//        view.txtValue.text =
//            Util.user.id?.let {
//                AppDatabase.getInstance(requireContext().applicationContext).recordsDao()
//                    .getSumBalance(it).toString()
//            }
        val r= getString(R.string.currency)
        view.txtBalance.text = r+":"+Util.user.balance.toString()
        view.txtExpenses.text = r+":"+AppDatabase.getInstance(requireContext().applicationContext).recordsDao().getSumExpenses(Util.user.id!!).toString()
        view.txtRevenue.text = r+":"+AppDatabase.getInstance(requireContext().applicationContext).recordsDao().getSumRevenue(Util.user.id!!).toString()
        return view
    }
}


