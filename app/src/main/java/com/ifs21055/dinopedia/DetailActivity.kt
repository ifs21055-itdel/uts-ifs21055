package com.ifs21055.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ifs21055.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var keluarga: Keluarga? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnDino = findViewById<TextView>(R.id.buttondino)
        btnDino.setOnClickListener {
            val intent = Intent(this, DinoActivity::class.java)
            startActivity(intent)
        }

        binding.buttondino.setOnClickListener{
            val intentWithData = Intent(this@DetailActivity, DinoActivity::class.java)
            intentWithData.putExtra(DinoActivity.EXTRA_KELUARGA, keluarga!!)
            startActivity(intentWithData)
        }

        keluarga = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_KELUARGA,Keluarga::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_KELUARGA)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (keluarga != null) {
            supportActionBar?.title = "Keluarga ${keluarga!!.name}"
            loadData(keluarga!!)
        } else {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                // Membuat intent untuk memulai ProfileActivity
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            android.R.id.home -> {
                finish() // Menutup aktivitas saat tombol kembali ditekan
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun loadData(keluarga: Keluarga) {
        binding.ivDetailIcon.setImageResource(keluarga.icon)
        binding.tvDetailName.text = keluarga.name
        binding.tvDetailDescription.text = keluarga.description
        binding.tvDetailPeriode.text = keluarga.periode
        binding.tvDetailCharacteristic.text = keluarga.characteristic
        binding.tvDetailHabitat.text = keluarga.habitat
        binding.tvDetailPerilaku.text = keluarga.perilaku
        binding.tvDetailKlasifikasi.text = keluarga.klasifikasi
    }


    companion object {
        const val EXTRA_KELUARGA = "extra_keluarga"
    }
}