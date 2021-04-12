package com.ifpr.receptor

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.action == Intent.ACTION_SEND && intent.type == "image/*") {
            (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let { uri ->
                imgView.setImageURI(uri)
            }
        } else if (intent.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            txtText.setText(intent.getStringExtra(Intent.EXTRA_TEXT))
        }

    }
}