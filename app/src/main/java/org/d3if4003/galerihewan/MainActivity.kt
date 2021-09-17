package org.d3if4003.galerihewan

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val hewan = listOf("ayam","bebek","domba","kambing","sapi")
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ButtonNext : Button = findViewById(R.id.ButtonNext)
        ButtonNext.setOnClickListener{showNext()}
    }

    private fun showNext() {
        Log.d("MainActivity","Klick Tombol")
        index = if (index == hewan.size-1) 0 else index + 1

        val imageView: ImageView = findViewById(R.id.GambarHewan)
        val resourceId = when(index){
            1 -> R.drawable.bebek
            2 -> R.drawable.domba
            3 -> R.drawable.kambing
            4 -> R.drawable.sapi

            else -> R.drawable.ayam
        }
        imageView.setImageResource(resourceId)
        val textView: TextView = findViewById(R.id.NamaHewan)
        textView.text = hewan[index]
    }
}