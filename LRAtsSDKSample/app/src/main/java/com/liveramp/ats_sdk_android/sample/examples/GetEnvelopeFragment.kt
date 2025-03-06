package com.liveramp.ats_sdk_android.sample.examples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.liveramp.ats.LRAtsManager
import com.liveramp.ats.model.*
import com.liveramp.ats_sdk_android.sample.databinding.FragmentGetEnvelopeBinding
import com.liveramp.ats_sdk_android.sample.internal.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetEnvelopeFragment : Fragment() {
    private lateinit var binding: FragmentGetEnvelopeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetEnvelopeBinding.inflate(inflater)
        Util.configureLogObserver(context, binding.tvLogs)
        binding.btnGetEnvelope.setOnClickListener {
            configureLRAtsExample()
        }
        return binding.root
    }

    private fun configureLRAtsExample() {
        val appID = binding.etAppId.text.toString()
        // You should provide your appID here
        val lrAtsConfiguration = LRAtsConfiguration(
            configID = appID,
            isTestMode = false,
            logToFileEnabled = true // Logging is enabled for debugging purposes, do not use it in your production app!
        )
        // SDK should be initialized only once
        LRAtsManager.initialize(lrAtsConfiguration) { success, initializeError ->
            // Covering case if init failed
            initializeError?.let {
                binding.tvEnvelopes.text = initializeError.message
            }
            // Covering case if init is successful
            if (success) {
                // Calling getEnvelope method from sdk
                // You can use email, phone or envelope identifiers to get envelope
                val identifier = LREmailIdentifier("example@mail.com")
                // val identifier = LRPhoneIdentifier("0123456789")
                // val identifier = LREnvelopeIdentifier("")
                LRAtsManager.getEnvelope(identifier) { envelope, error ->
                    CoroutineScope(Dispatchers.Main).launch {
                        error?.let { binding.tvEnvelopes.text = error.message }
                        envelope?.let { binding.tvEnvelopes.text = Util.envelopeToString(it) }
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            GetEnvelopeFragment()
    }

    override fun onResume() {
        Util.startLogObserver()
        super.onResume()
    }

    override fun onPause() {
        Util.stopLogObserver()
        super.onPause()
    }
}
