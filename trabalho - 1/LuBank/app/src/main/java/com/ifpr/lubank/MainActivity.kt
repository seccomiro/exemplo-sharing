package com.ifpr.lubank

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ifpr.lubank.databases.AppDatabase
import com.ifpr.lubank.models.Record
import com.ifpr.lubank.util.Util
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val record = Util.user.id?.let {
//            Record(
//                Util.user.username, "descrição", 400.0,
//                it
//            )
//        }
//        record?.let { AppDatabase.getInstance(this).recordsDao().insert(it) }

        val all = AppDatabase.getInstance(this).recordsDao().getRecordsByUser(Util.user.id)

        Log.i("tag", all.toString())


        bottomNavigationView.setupWithNavController(findNavController(R.id.navHostFragment))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_app, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btLogout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun logout() {
        val sharedPref = applicationContext?.getSharedPreferences("user", Context.MODE_PRIVATE)

        sharedPref?.edit()?.putString("username", "")
            ?.putString("password", "")
            ?.apply()


        val intent = Intent(applicationContext, AcessActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}







