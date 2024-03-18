package com.ifs21055.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Dino(
    var name: String,
    var icon: Int,
    var description: String,
    var kelompok: String,
    var characteristic: String,
    var habitat: String,
    var makanan: String,
    var panjang: String,
    var tinggi: String,
    var bobot: String,
    var kelemahan: String,
) : Parcelable