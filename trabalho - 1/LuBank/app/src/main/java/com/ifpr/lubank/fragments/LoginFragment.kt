package com.ifpr.lubank.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ifpr.lubank.MainActivity

import com.ifpr.lubank.R
import com.ifpr.lubank.databases.AppDatabase
import com.ifpr.lubank.util.Util
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val sharedPref = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)

        if (sharedPref != null) {
            val username = sharedPref.getString("username", "")
            val password = sharedPref.getString("password", "")
            login(username.toString(), password.toString(), false)

        }

        view.singup.setOnClickListener { nextTo() }
        view.btSingIn.setOnClickListener {
            login(
                txtUsername.text.toString(),
                txtPassword.text.toString()
            )
        }


        return view
    }

    private fun login(username: String, password: String, verify: Boolean = true) {
        if (AppDatabase.getInstance(requireActivity().applicationContext.applicationContext)
                .usersDao().login(username, password)
        ) {

            val sharedPref = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)

            if (sharedPref != null) {
                with(sharedPref.edit()) {
                    putString("username", username)
                    putString("password", password)
                    commit()
                }
            }

            Util.user=AppDatabase.getInstance(requireActivity().applicationContext.applicationContext)
                .usersDao().user(username, password)

            val intent = Intent(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

        } else {
            if (verify)
                Toast.makeText(activity, R.string.login_field_error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun nextTo() {
        findNavController().navigate(R.id.navigateToRegister)
    }

}
