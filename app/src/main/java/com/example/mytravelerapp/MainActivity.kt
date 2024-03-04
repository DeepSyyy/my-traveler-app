package com.example.mytravelerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private lateinit var rvPlaces: RecyclerView
    private val listItem = ArrayList<Place>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: MaterialToolbar = findViewById(R.id.home_appbar)

        toolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.about ->{
                    val intent = Intent(this@MainActivity, AboutActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        rvPlaces = findViewById(R.id.rv_places)
        rvPlaces.setHasFixedSize(true)


        getListPlace()
        showRecycleView()
    }

    private fun showRecycleView() {
        rvPlaces.layoutManager = GridLayoutManager(this, 2)
        val listPlaceAdapter = ListPlaceAdapter(listItem)
        rvPlaces.adapter = listPlaceAdapter

        listPlaceAdapter.setOnItemClickCallback(object: ListPlaceAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Place) {
                val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveWithObjectIntent.putExtra(DetailActivity.EXTRA_PLACE, data)
                startActivity(moveWithObjectIntent)
            }
        })
    }


    private fun getListPlace(): ArrayList<Place> {
        val dataPlace = resources.getStringArray(R.array.data_place)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataOpenTime = resources.getStringArray(R.array.data_open_time)
        val dataImage = resources.getStringArray(R.array.data_image)
        val dataLocation = resources.getStringArray(R.array.data_location)
        val dataRating = resources.getIntArray(R.array.data_rating)
        for (i in dataPlace.indices) {
            val places = Place(
                dataPlace[i],
                dataImage[i],
                dataLocation[i],
                dataRating[i],
                dataDescription[i],
                dataOpenTime[i],
            )
            listItem.add(places)
        }
        return listItem
    }
}