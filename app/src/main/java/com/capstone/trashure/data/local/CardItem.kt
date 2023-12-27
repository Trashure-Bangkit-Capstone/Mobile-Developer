package com.capstone.trashure.data.local

import com.capstone.trashure.data.response.OrderResponse

data class CardItem(val date: String, val username: String, val location: String)

fun OrderResponse?.toCardItemList(): List<CardItem> {
    return this?.data?.map {
        it.let { dataItem ->
            CardItem(
                date = dataItem.createdAt,
                username = dataItem.username,
                location = dataItem.lokasiUser
            )
        }
    } ?: emptyList()
}