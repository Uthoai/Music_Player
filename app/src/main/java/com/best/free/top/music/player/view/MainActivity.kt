package com.best.free.top.music.player.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.best.free.top.music.player.R
import com.best.free.top.music.player.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object{
        const val STORAGE_PERMISSION_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarLayout)


        binding.btnShuffle.setOnClickListener {
            startActivity(Intent(this@MainActivity,PlayActivity::class.java))
        }

        binding.btnFavourites.setOnClickListener {
            startActivity(Intent(this@MainActivity,FavouritesActivity::class.java))
        }

        binding.btnPlaylists.setOnClickListener {
            startActivity(Intent(this@MainActivity,PlaylistsActivity::class.java))
        }

    }

    private fun requestPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO)
            == PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_MEDIA_AUDIO),STORAGE_PERMISSION_CODE)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_MEDIA_AUDIO), 101)
        }
    }
}