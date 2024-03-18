package com.ifs21055.dinopedia

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21055.dinopedia.databinding.ActivityDinotailBinding

class DinotailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinotailBinding
    private var dino: Dino? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinotailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan data Dino dari intent
        dino = intent.getParcelableExtra(EXTRA_DINO)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (dino != null) {
            supportActionBar?.title = "Dino ${dino!!.name}"
            loadData(dino!!)
        } else {
            finish()
        }
    }

    private fun loadData(dino: Dino) {
        // Mengisi data ke tampilan berdasarkan objek Dino
        binding.ivDetailIcon.setImageResource(dino.icon)
        binding.tvDetailName.text = dino.name
        binding.tvDetailDescription.text = dino.description
        binding.tvDetailCharacteristic.text = dino.characteristic
        binding.tvDetailKelompok.text = dino.kelompok
        binding.tvDetailHabitat.text = dino.habitat
        binding.tvDetailMakanan.text = dino.makanan
        binding.tvDetailPanjang.text = dino.panjang
        binding.tvDetailTinggi.text = dino.tinggi
        binding.tvDetailBobot.text = dino.bobot
        binding.tvDetailKelemahan.text = dino.kelemahan
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
}
