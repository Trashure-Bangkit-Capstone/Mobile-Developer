package com.capstone.trashure.data

data class CardItem(val date: String, val username: String, val location: String)

fun OrderResponse?.toCardItemList(): List<CardItem> {
    return this?.data?.mapNotNull {
        it?.let { dataItem ->
            CardItem(
                date = dataItem.createdAt ?: "",
                username = dataItem.username ?: "",
                location = dataItem.lokasiUser ?: ""
            )
        }
    } ?: emptyList()
}