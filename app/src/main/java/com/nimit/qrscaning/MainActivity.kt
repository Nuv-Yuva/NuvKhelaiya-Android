package com.nimit.qrscaning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.brendangoldberg.kotlin_jwt.KtJwtDecoder
import com.brendangoldberg.kotlin_jwt.KtJwtVerifier
import com.brendangoldberg.kotlin_jwt.algorithms.HSAlgorithm
import com.google.zxing.integration.android.IntentIntegrator
import java.sql.DriverManager.println

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.txtview)
        val qrButton: ImageButton = findViewById(R.id.qr_button)
        qrButton.setOnClickListener{
            println("hello")
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.initiateScan()

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(resultCode, data)
        //Toast.makeText(this@MainActivity, "${result}", Toast.LENGTH_SHORT).show()

        if (result != null) {

            try {
                val algorithm = HSAlgorithm.HS256("nuvkhelaiyaftw")
                val verified = KtJwtVerifier(algorithm).verify(result.toString())


                println("verified: $verified")
                Log.i("verified", verified.toString())

                // Decode JWT
                val decoded = KtJwtDecoder.decode(result.toString())
                println("$decoded")
            }
            catch (e: Exception){
                println("some error occurred")
            }

            //AlertDialog.Builder(this)
            //   .setMessage(result.contents)
            //  .create()
            //  .show()

        }

    }

}