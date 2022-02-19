package com.example.videoplayer.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

class YoutubeIdHelper {
    companion object {
        fun getYouTubeId(youTubeUrl: String): String {
            val pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
            val compiledPattern: Pattern = Pattern.compile(pattern)
            val matcher: Matcher = compiledPattern.matcher(youTubeUrl)
            return if (matcher.find()) {
                matcher.group()
            } else {
                "error"
            }
        }
    }
}