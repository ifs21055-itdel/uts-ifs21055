package com.ifs21055.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21055.dinopedia.databinding.ActivityAllDinoBinding

class AllDinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllDinoBinding
    private val dataDino = ArrayList<Dino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvDino.setHasFixedSize(false)
        dataDino.addAll(getListDino())
        showRecyclerList()
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

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) { binding.rvDino.layoutManager =
            GridLayoutManager(this, 2)
        } else {
            binding.rvDino.layoutManager =
                LinearLayoutManager(this)
        }
        val listDinoAdapter = ListDinoAdapter(dataDino)
        binding.rvDino.adapter = listDinoAdapter

        listDinoAdapter.setOnItemClickCallback(object :
            ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }

    @SuppressLint("Recycle")
    private fun getListDino(): ArrayList<Dino> {
        val keluarga = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_KELUARGA,
                Keluarga::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_KELUARGA)
        }

        val dataName =
            resources.getStringArray(R.array.dinos_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.dinos_icon)
        val dataDescription =
            resources.getStringArray(R.array.dinos_description)
        val dataKelompok =
            resources.getStringArray(R.array.dinos_kelompok)
        val dataKarakteristik =
            resources.getStringArray(R.array.dinos_characteristic)
        val dataHabitat =
            resources.getStringArray(R.array.dinos_habitat)
        val dataMakanan =
            resources.getStringArray(R.array.dinos_makanan)
        val dataPanjang =
            resources.getStringArray(R.array.dinos_panjang)
        val dataTinggi =
            resources.getStringArray(R.array.dinos_tinggi)
        val dataBobot =
            resources.getStringArray(R.array.dinos_bobot)
        val dataKelemahan =
            resources.getStringArray(R.array.dinos_kelemahan)

        val listDino = ArrayList<Dino>()
        for (i in dataName.indices) {
            val dino = Dino(
                dataName[i],
                dataIcon.getResourceId(i, -1),
                dataDescription[i],
                dataKelompok[i],
                dataKarakteristik[i],
                dataHabitat[i],
                dataMakanan[i],
                dataPanjang[i],
                dataTinggi[i],
                dataBobot[i],
                dataKelemahan[i])
            listDino.add (dino)
        }
        return listDino
    }


    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(
            this@AllDinoActivity,
            DinoDetailActivity::class.java)
        intentWithData.putExtra (DinoDetailActivity.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }

    companion object{
        const val EXTRA_KELUARGA = "extra_keluarga"
    }

}