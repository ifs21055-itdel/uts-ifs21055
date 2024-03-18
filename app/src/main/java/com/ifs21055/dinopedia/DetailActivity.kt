package com.ifs21055.dinopedia
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ifs21055.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var family: Family? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan data Family dari intent
        family = intent.getParcelableExtra(EXTRA_FAMILY)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (family != null) {
            supportActionBar?.title = "Family ${family!!.name}"
            loadData(family!!)
        } else {
            finish()
        }

        // Menambahkan event listener untuk tombol "LIHAT DINO"
        val buttonLihatDino: Button = findViewById(R.id.button)
        buttonLihatDino.setOnClickListener {
            // Membuat intent untuk membuka MainActivity
            val intent = Intent(this@DetailActivity, DinoActivity::class.java)
            // Memulai activity MainActivity
            startActivity(intent)
        }
    }

    private fun loadData(family: Family) {
        // Mengisi data ke tampilan berdasarkan objek Family
        binding.ivDetailIcon.setImageResource(family.icon)
        binding.tvDetailName.text = family.name
        binding.tvDetailDescription.text = family.description
        binding.tvDetailPeriode.text = family.periode
        binding.tvDetailCharacteristic.text = family.characteristic
        binding.tvDetailHabitat.text = family.habitat
        binding.tvDetailPerilaku.text = family.perilaku
        binding.tvDetailKlasifikasi.text = family.klasifikasi
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
        const val EXTRA_FAMILY = "extra_family"
    }
}
