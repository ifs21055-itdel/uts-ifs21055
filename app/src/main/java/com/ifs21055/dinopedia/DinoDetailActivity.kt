package com.ifs21055.dinopedia

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21055.dinopedia.databinding.ActivityDinoDetailBinding

class DinoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinoDetailBinding
    private var dino: Dino? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil objek Dino dari intent
        dino = intent.getParcelableExtra(EXTRA_DINO)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (dino != null) {
            supportActionBar?.title = "Family ${dino!!.name}"
            loadData(dino!!)
        } else {
            finish()
        }
    }

    private fun loadData(dino: Dino) {
        binding.apply {
            ivDetailIcon.setImageResource(dino.icon)
            tvDetailName.text = dino.name
            tvDetailDescription.text = dino.description
            tvDetailKelompok.text = dino.kelompok
            tvDetailCharacteristic.text = dino.characteristic
            tvDetailHabitat.text = dino.habitat
            tvDetailMakanan.text = dino.makanan
            tvDetailPanjang.text = dino.panjang
            tvDetailTinggi.text = dino.tinggi
            tvDetailBobot.text = dino.bobot
            tvDetailKelemahan.text = dino.kelemahan
        }
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

    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
}
