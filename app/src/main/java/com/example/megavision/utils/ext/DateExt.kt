package com.example.megavision.utils.ext

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val FORMAT_DD_MMMM_YYYY = "dd MMMM yyyy"
const val FORMAT_DD_MM_YYYY = "dd-MM-yyyy"

fun Date?.formatDate(
    format: String = FORMAT_DD_MMMM_YYYY,
    locale: Locale = Locale("id", "ID")
): String {
    if (this == null) {
        return ""
    }
    return try {
        val formatter = SimpleDateFormat(format, locale)
        formatter.format(this)
    } catch (e: Exception) {
        ""
    }
}

fun String?.formatDate(
    locale: Locale = Locale("id", "ID")
): String {
    if (this == null) {
        return ""
    }
    return try {
        val inputFormat = SimpleDateFormat(FORMAT_DD_MM_YYYY, Locale.ENGLISH)
        val outputFormat = SimpleDateFormat(FORMAT_DD_MMMM_YYYY, locale)

        val formattedDate = outputFormat.format(inputFormat.parse(this) ?: "")
        formattedDate
    } catch (e: Exception) {
        ""
    }
}