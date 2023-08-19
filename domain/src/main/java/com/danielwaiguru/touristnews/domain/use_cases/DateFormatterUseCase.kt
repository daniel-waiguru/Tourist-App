package com.danielwaiguru.touristnews.domain.use_cases

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateFormatterUseCase {
    operator fun invoke(
        inPattern: String = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS",
        outFormat: String = "d MMM, yyyy",
        jsonDate: String?
    ): String {
        return try {
            val inFormatter = SimpleDateFormat(
                inPattern,
                Locale.getDefault()
            )
            val outFormatter = SimpleDateFormat(
                outFormat,
                Locale.getDefault()
            )
            if (jsonDate == null) return outFormatter.format(Date())
            val date = inFormatter.parse(jsonDate) ?: return ""
            outFormatter.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            /**
             * Log to Crashlytics/Sentry etc
             */
            ""
        }
    }
}