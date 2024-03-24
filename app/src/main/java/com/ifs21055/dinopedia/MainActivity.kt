package com.ifs21055.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21055.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataKeluarga = ArrayList<Keluarga>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvKeluarga.setHasFixedSize(false)
        dataKeluarga.addAll(getListKeluarga())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                // Membuat intent untuk memulai ProfileActivity
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Recycle")
    private fun getListKeluarga(): ArrayList<Keluarga> {
        val dataName =
            resources.getStringArray(R.array.keluargas_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.keluargas_icon)
        val dataDescription =
            resources.getStringArray(R.array.keluargas_description)
        val dataPeriode =
            resources.getStringArray(R.array.keluargas_periode)
        val dataKarakteristik =
            resources.getStringArray(R.array.keluargas_characteristic)
        val dataHabitat =
            resources.getStringArray(R.array.keluargas_habitat)
        val dataPerilaku =
            resources.getStringArray(R.array.keluargas_perilaku)
        val dataKlasifikasi =
            resources.getStringArray(R.array.keluargas_klasifikasi)
        val dataStartIndex =
            resources.getStringArray(R.array.start_dino_array)
        val dataEndIndex =
            resources.getStringArray(R.array.end_dino_array)

        val listKeluarga = ArrayList<Keluarga>()
        for (i in dataName.indices) {
            val keluarga = Keluarga(
                dataName[i],
                dataIcon.getResourceId(i, -1),
                dataDescription[i],
                dataPeriode[i],
                dataKarakteristik[i],
                dataHabitat[i],
                dataPerilaku[i],
                dataKlasifikasi[i],
                dataStartIndex[i].toInt(),
                dataEndIndex[i].toInt())
            listKeluarga.add (keluarga)
        }
        return listKeluarga
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) { binding.rvKeluarga.layoutManager =
            GridLayoutManager(this, 2)
        } else {
            binding.rvKeluarga.layoutManager =
                LinearLayoutManager(this)
        }
        val listkeluargaAdapter = ListKeluargaAdapter(dataKeluarga)
        binding.rvKeluarga.adapter = listkeluargaAdapter
        listkeluargaAdapter.setOnItemClickCallback(object :
            ListKeluargaAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Keluarga) {
                showSelectedKeluarga(data)
            }
        })
    }

    private fun showSelectedKeluarga(keluarga: Keluarga) {
        val intentWithData = Intent(
            this@MainActivity,
            DetailActivity::class.java)
        intentWithData.putExtra (DetailActivity.EXTRA_KELUARGA, keluarga)
        startActivity(intentWithData)
    }
}