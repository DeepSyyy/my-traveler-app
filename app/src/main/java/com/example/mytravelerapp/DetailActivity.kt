package com.example.mytravelerapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar

class DetailActivity : AppCompatActivity() {
    private var data: Place? = null

    companion object {
        const val EXTRA_PLACE = "extra_place"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar: MaterialToolbar = findViewById(R.id.appbar)
        val ivPlace: ImageView = findViewById(R.id.iv_place)
        val tvPlace: TextView = findViewById(R.id.tv_detail_place)
        val tvLocation: TextView = findViewById(R.id.tv_detail_location_place)
        val tvRating: TextView = findViewById(R.id.tv_detail_rating)
        val tvHours: TextView = findViewById(R.id.tv_open_hours)
        val tvDesc: TextView = findViewById(R.id.tv_detail_description)

        data = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PLACE, Place::class.java)
        } else {
            @Suppress("DEPRECATION") intent.getParcelableExtra(EXTRA_PLACE)
        }
        tvPlace.text = data?.title.orEmpty()
        tvDesc.text = data?.description.orEmpty()
        tvHours.text = data?.openTime.orEmpty()
        tvLocation.text = data?.location.orEmpty()
        tvRating.text = data?.rating.toString()
        Glide.with(this@DetailActivity).load(data?.image).into(ivPlace)

        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        toolbar.inflateMenu(R.menu.detail_menu)

        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.ic_share -> {
                    val sharedItem =
                        "Nama tempat" + data?.title.orEmpty() + "\nDesc" + data?.description.orEmpty()
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, sharedItem)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(intent,null)
                    startActivity(shareIntent)
                    true
                }
                else -> false

            }
        }

    }


}