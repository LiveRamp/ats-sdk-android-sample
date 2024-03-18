package com.liveramp.ats_sdk_android.sample.internal

import android.content.Context
import android.os.FileObserver
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import com.liveramp.ats.model.Envelope
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object Util {
    private lateinit var fileObserver: FileObserver

    fun configureLogObserver(context: Context?, textView: TextView) {
        textView.movementMethod = ScrollingMovementMethod()

        val logFile = File(context?.filesDir?.absolutePath.plus("/logs/${calendarToDate(Calendar.getInstance())}.log"))
        FileListener(logFile) {}.createLogFile()
        fileObserver = FileListener(logFile) {
            textView.text = it
            val scrollAmount = textView.layout.getLineTop(textView.lineCount) - textView.height
            if (scrollAmount > 0) {
                textView.scrollTo(0, scrollAmount)
            } else {
                textView.scrollTo(0, 0)
            }
        }

        fileObserver.startWatching()
    }

    fun stopLogObserver() {
        fileObserver.stopWatching()
    }

    fun startLogObserver() {
        fileObserver.startWatching()
    }

    private fun calendarToDate(date: Calendar): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(date.time)
    }

    fun envelopeToString(envelope: Envelope): String {
        var stringRepresentation = ""
        envelope.envelope?.let { stringRepresentation = "envelope:\n$it\n" }
        envelope.envelope24?.let { stringRepresentation = stringRepresentation
            .plus("envelope24:\n$it\n") }
        envelope.envelope25?.let { stringRepresentation = stringRepresentation
            .plus("envelope25:\n$it\n") }
        return stringRepresentation
    }
}