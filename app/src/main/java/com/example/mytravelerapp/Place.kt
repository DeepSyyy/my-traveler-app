package com.example.mytravelerapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    val title: String,
    val image: String,
    val location: String,
    val rating: Int,
    val description: String,
    val openTime: String,
) : Parcelable
