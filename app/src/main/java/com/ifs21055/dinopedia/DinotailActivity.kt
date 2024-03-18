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

        // Menampilkan tombol back di action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Mendapatkan data Dino dari intent
        dino = intent.getParcelableExtra(EXTRA_DINO)

        // Memeriksa apakah data Dino tidak null sebelum memanggil loadData
        if (dino != null) {
            supportActionBar?.title = "Dino ${dino!!.name}"
            loadData(dino!!)
        } else {
            // Jika data Dino tidak ditemukan, tutup aktivitas
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
        return when (item.itemId) {
            // Handling untuk tombol back di action bar
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
}
