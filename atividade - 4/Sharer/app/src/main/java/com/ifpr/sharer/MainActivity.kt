package com.ifpr.sharer

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.util.jar.Manifest
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    companion object {
        const val PICK_IMAGE = 777
        const val TEXT = 111
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSend.setOnClickListener { sendTxt(txtType.text.toString()) }
        btPickImage.setOnClickListener { selectImg() }
        btnPermission.setOnClickListener { requestPermission() }
    }


    fun sendTxt(txt: String) {
        if (txt != "") {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, txt)
            intent.type = "text/plain"

            val shareIntent = Intent.createChooser(intent, "Sharer text")
            startActivityForResult(shareIntent, TEXT)
        } else {
            Toast.makeText(this, "Enter your Infomation", Toast.LENGTH_SHORT).show()
        }

    }

    fun selectImg() {

        //OPEN GALLARY DEFAULT
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = "image/*"
//
//        val shareIntent = Intent.createChooser(intent, "Sharer Images")
//        startActivityForResult(shareIntent, PICK_IMAGE)

        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        val shareIntent = Intent.createChooser(intent, "Select a Image")
        startActivityForResult(shareIntent, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == PICK_IMAGE) {
            val inputStream = contentResolver.openInputStream(data?.data!!)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            imgImagem.setImageBitmap(bitmap)

            sharerImage(bitmap)
        }

        super.onActivityResult(requestCode, resultCode, data)

    }

    fun sharerImage(bitmap: Bitmap?) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("image/*")
        val path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null)
        val imageUri = Uri.parse(path)
        intent.putExtra(Intent.EXTRA_STREAM, imageUri)
        startActivity(Intent.createChooser(intent, "Select"))
    }

    fun requestPermission() {
        Dexter.withContext(this)
            .withPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener{
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    Toast.makeText(getApplicationContext(),"Access allowed",Toast.LENGTH_LONG).show()

                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) { }
                override fun onPermissionDenied(p0: PermissionDeniedResponse?) { }
            }).check()

    }

}
