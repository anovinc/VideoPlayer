package com.example.videoplayer.utils

import com.example.videoplayer.BuildConfig
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

        fun getImageUrlFromId(videoId: String) : String {
            return BuildConfig.BASE_URL + videoId + BuildConfig.URL_END
        }
    }
}