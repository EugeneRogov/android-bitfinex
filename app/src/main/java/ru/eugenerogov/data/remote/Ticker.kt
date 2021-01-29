package ru.eugenerogov.data.remote

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
data class Ticker(
    @PrimaryKey val uuid: UUID = UUID.randomUUID(),
    var date: Date = Date(),

    var title: String = "",
    var urlIcon: String = "",

    var id: Int = 0,
    var channelId: Int = 0,
    var bid: Float = 0.0f,
    var bidSize: Float = 0.0f,
    var ask: Float = 0.0F,
    var askSize: Float = 0.0F,
    var dailyChange: Float = 0.0F,
    var dailyChangePerc: Float = 0.0F,
    var lastPrice: Float = 0.0F,
    var volume: Float = 0.0F,
    var high: Float = 0.0F,
    var low: Float = 0.0F
) : Parcelable