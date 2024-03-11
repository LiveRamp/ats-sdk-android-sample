package com.liveramp.ats_sdk_android.sample.examples

import android.os.Bundle
import android.os.FileObserver
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liveramp.ats.LRAtsManager
import com.liveramp.ats.model.LRAtsConfiguration
import com.liveramp.ats.model.LREmailIdentifier
import com.liveramp.ats_sdk_android.sample.databinding.FragmentGetEnvelopeBinding
import com.liveramp.ats_sdk_android.sample.internal.FileListener
import com.liveramp.ats_sdk_android.sample.internal.stringRepresentation
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class GetEnvelopeFragment : Fragment() {
    private lateinit var binding: FragmentGetEnvelopeBinding
    private lateinit var fileObserver: FileObserver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetEnvelopeBinding.inflate(inflater)
        configureLogObserver()
        binding.btnGetEnvelope.setOnClickListener {
            configureLRAtsExample()
        }
        return binding.root
    }

    private fun configureLogObserver() {
        binding.tvLogs.movementMethod = ScrollingMovementMethod()
        val logFile = File(context?.filesDir?.absolutePath.plus("/logs/${calendarToDate(Calendar.getInstance())}.log"))
        FileListener(logFile) {}.createLogFile()
        fileObserver = FileListener(logFile) {
            binding.tvLogs.text = it
            val scrollAmount = binding.tvLogs.layout.getLineTop(binding.tvLogs.lineCount) - binding.tvLogs.height
            if (scrollAmount > 0) {
                binding.tvLogs.scrollTo(0, scrollAmount)
            } else {
                binding.tvLogs.scrollTo(0, 0)
            }
        }
        fileObserver.startWatching()
    }

    private fun configureLRAtsExample() {
        val appID = binding.etAppId.text.toString()
        // You should provide your appID here
        // isLogToFileEnabled is set to true for debugging purposes, do not use it in your production app
        val lrAtsConfiguration = LRAtsConfiguration(
            configurationId = appID,
            isTestMode = false,
            logToFileEnabled = true
        )
        // SDK should be initialized only once
        LRAtsManager.initialize(lrAtsConfiguration) { success, initializeError ->
            //Covering case if init failed
            initializeError?.let {
                binding.tvEnvelopes.text = initializeError.message
            }
            // Covering case if init is successful
            if (success) {
                // Calling getEnvelope method from sdk
                // You can use email, phone, envelope or custom identifier to get envelope
                val identifier = LREmailIdentifier("example@mail.com")
                // val identifier = LRPhoneIdentifier("0123456789")
                // val identifier = LREnvelopeIdentifier("")
                // val identifier = LRCustomIdentifier("54321:abc123")
                LRAtsManager.getEnvelope(identifier) { envelope, error ->
                    activity?.runOnUiThread {
                        error?.let { binding.tvEnvelopes.text = error.message }
                        envelope?.let { binding.tvEnvelopes.text = envelope.stringRepresentation() }
                    }
                }
            }
        }
    }

    private fun calendarToDate(date: Calendar): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(date.time)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            GetEnvelopeFragment()
    }

    override fun onResume() {
        fileObserver.startWatching()
        super.onResume()
    }

    override fun onPause() {
        fileObserver.stopWatching()
        super.onPause()
    }
}