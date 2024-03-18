package com.ifs21055.dinopedia

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21055.dinopedia.databinding.ActivityDinoBinding

class DinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinoBinding
    private val datadinos = ArrayList<Dino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvDino.setHasFixedSize(false)
        datadinos.addAll(getListdinos())
        showRecyclerList()
    }

    private fun getListdinos(): ArrayList<Dino> {
        val dataName = resources.getStringArray(R.array.dinos_name)
        val dataIcon = resources.obtainTypedArray(R.array.dinos_icon)
        val dataDescription = resources.getStringArray(R.array.dinos_description)
        val dataCharacteristic = resources.getStringArray(R.array.dinos_characteristic)
        val dataKelompok = resources.getStringArray(R.array.dinos_kelompok)
        val dataHabitat = resources.getStringArray(R.array.dinos_habitat)
        val dataMakanan = resources.getStringArray(R.array.dinos_makanan)
        val dataPanjang = resources.getStringArray(R.array.dinos_panjang)
        val dataTinggi = resources.getStringArray(R.array.dinos_tinggi)
        val dataBobot = resources.getStringArray(R.array.dinos_bobot)
        val dataKelemahan = resources.getStringArray(R.array.dinos_kelemahan)

        val listdino = ArrayList<Dino>()
        for (i in dataName.indices) {
            val dino = Dino(
                dataName[i],
                dataIcon.getResourceId(i, -1),
                dataDescription[i],
                dataKelompok[i],
                dataCharacteristic[i],
                dataHabitat[i],
                dataMakanan[i],
                dataPanjang[i],
                dataTinggi[i],
                dataBobot[i],
                dataKelemahan[i]
            )
            listdino.add(dino)
        }
        return listdino
    }

    private fun showRecyclerList() {
        val orientation = resources.configuration.orientation
        binding.rvDino.layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }

        val listdinoAdapter = ListDinoAdapter(datadinos)
        binding.rvDino.adapter = listdinoAdapter

        listdinoAdapter.setOnItemClickCallback(object : ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelecteddino(data)
            }
        })
    }

    private fun showSelecteddino(dino: Dino) {
        val intentWithData = Intent(this@DinoActivity, DinotailActivity::class.java)
        intentWithData.putExtra(DinotailActivity.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }
}
