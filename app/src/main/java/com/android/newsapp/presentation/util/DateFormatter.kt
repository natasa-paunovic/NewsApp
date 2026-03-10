package com.android.newsapp.presentation.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private val DISPLAY_FORMATTER: DateTimeFormatter = DateTimeFormatter
    .ofPattern("MMM d, yyyy", Locale.getDefault())
    .withZone(ZoneId.systemDefault())


fun String.toDisplayDate(): String {
    val instant = Instant.parse(this)
    return DISPLAY_FORMATTER.format(instant)
}