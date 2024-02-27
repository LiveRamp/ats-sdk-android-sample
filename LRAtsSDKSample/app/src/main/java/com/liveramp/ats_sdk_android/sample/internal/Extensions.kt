package com.liveramp.ats_sdk_android.sample.internal

import com.liveramp.ats.model.Envelope

    fun Envelope.stringRepresentation(): String {
        var stringRepresentation = ""
        this.envelope?.let { stringRepresentation = "envelope:\n$envelope\n" }
        this.envelope24?.let { stringRepresentation = stringRepresentation
            .plus("envelope24:\n$envelope24\n") }
        this.envelope25?.let { stringRepresentation = stringRepresentation
            .plus("envelope25:\n$envelope25\n") }
        return stringRepresentation
    }