package com.capstone.trashure.data

import com.google.gson.annotations.SerializedName

data class OrderResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataItem(

	@field:SerializedName("status_pemesanan")
	val statusPemesanan: String? = null,

	@field:SerializedName("catatan")
	val catatan: String? = null,

	@field:SerializedName("berat_sampah")
	val beratSampah: Int? = null,

	@field:SerializedName("hargaPerKg")
	val hargaPerKg: Int? = null,

	@field:SerializedName("points")
	val points: Int? = null,

	@field:SerializedName("lokasi_user")
	val lokasiUser: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("lokasi_pengepul")
	val lokasiPengepul: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("jenis_sampah")
	val jenisSampah: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
