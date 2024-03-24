package com.ifs21055.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Keluarga(
    var name: String,
    var icon: Int,
    var description: String,
    var periode: String,
    var characteristic: String,
    var habitat: String,
    var perilaku: String,
    var klasifikasi: String,
    var startIndex: Int,
    var endIndex: Int
) : Parcelable