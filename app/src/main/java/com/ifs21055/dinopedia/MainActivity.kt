package com.ifs21055.dinopedia

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21055.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val datafamilys = ArrayList<Family>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvFamily.setHasFixedSize(false)
        datafamilys.addAll(getListfamilys())
        showRecyclerList()
    }

    private fun getListfamilys(): ArrayList<Family> {
        val dataName = resources.getStringArray(R.array.familys_name)
        val dataIcon = resources.obtainTypedArray(R.array.familys_icon)
        val dataDescription = resources.getStringArray(R.array.familys_description)
        val dataPeriode = resources.getStringArray(R.array.familys_periode)
        val dataCharacteristic = resources.getStringArray(R.array.familys_characteristic)
        val dataHabitat = resources.getStringArray(R.array.familys_habitat)
        val dataPerilaku = resources.getStringArray(R.array.familys_perilaku)
        val dataKlasifikasi = resources.getStringArray(R.array.familys_klasifikasi)

        val listfamily = ArrayList<Family>()
        for (i in dataName.indices) {
            val family = Family(
                dataName[i],
                dataIcon.getResourceId(i, -1),
                dataDescription[i],
                dataPeriode[i],
                dataCharacteristic[i],
                dataHabitat[i],
                dataPerilaku[i],
                dataKlasifikasi[i]
            )
            listfamily.add(family)
        }
        return listfamily
    }

    private fun showRecyclerList() {
        val orientation = resources.configuration.orientation
        binding.rvFamily.layoutManager = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }

        val listfamilyAdapter = ListFamilyAdapter(datafamilys)
        binding.rvFamily.adapter = listfamilyAdapter

        listfamilyAdapter.setOnItemClickCallback(object : ListFamilyAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Family) {
                showSelectedfamily(data)
            }
        })
    }

    private fun showSelectedfamily(family: Family) {
        val intentWithData = Intent(this@MainActivity, DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_FAMILY, family)
        startActivity(intentWithData)
    }
}
