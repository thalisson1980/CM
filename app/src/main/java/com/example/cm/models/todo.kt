package com.example.cm.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.util.*
@Parcelize
data class todo(val nome: String,val idade: Number,val genero: String): Parcelable {



}