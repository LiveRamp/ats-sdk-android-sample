package com.liveramp.ats_sdk_android.sample.internal

import android.os.FileObserver
import java.io.File

class FileListener(private val file: File, private val callback: FileUpdateCallback) : FileObserver(file) {
    override fun onEvent(event: Int, path: String?) {
        if (event == MODIFY) {
            val logs = file.readLines().joinToString(prefix = "", postfix = "", separator = "") {
                it.split(":").drop(4).joinToString(":").trim().plus("\n\n")
            }
            callback.invoke(logs)
        }
    }

    fun createLogFile() {
        val logsFolder = file.parentFile?.absoluteFile
        if (logsFolder?.exists() == true) {
            logsFolder.deleteRecursively()
            logsFolder.mkdir()
        }
        file.createNewFile()
    }
}