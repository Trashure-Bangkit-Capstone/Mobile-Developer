package com.dicoding.picodiploma.mycamera.data.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrashItem(val id: Int, val name: String, val imageUrl: String) : Parcelable
