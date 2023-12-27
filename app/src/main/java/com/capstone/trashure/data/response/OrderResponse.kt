package com.capstone.trashure.data.response

import com.google.gson.annotations.SerializedName

data class OrderResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String
)

data class DataItem(

	@field:SerializedName("status_pemesanan")
	val statusPemesanan: String,

	@field:SerializedName("catatan")
	val catatan: String,

	@field:SerializedName("berat_sampah")
	val beratSampah: Int,

	@field:SerializedName("hargaPerKg")
	val hargaPerKg: Int,

	@field:SerializedName("points")
	val points: Int,

	@field:SerializedName("lokasi_user")
	val lokasiUser: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("lokasi_pengepul")
	val lokasiPengepul: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("jenis_sampah")
	val jenisSampah: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
