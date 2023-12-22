package com.dicoding.picodiploma.mycamera.data.api

import com.google.gson.annotations.SerializedName

data class FileUploadResponse(
    @field:SerializedName("jenis_sampah")
    val jenisSampah: String,

    @field:SerializedName("predictions")
    val predictions: String
)