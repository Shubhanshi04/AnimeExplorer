package com.shubhanshi.animeexplorer.util

import android.content.Context
import android.content.Intent
import android.net.Uri

fun openYouTube(context: Context, videoUrl: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}


