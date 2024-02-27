package com.liveramp.ats_sdk_android.sample.examples

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liveramp.ats.LRAtsManager
import com.liveramp.ats.model.Envelope
import com.liveramp.ats.model.LRAtsConfiguration
import com.liveramp.ats.model.LREmailIdentifier
import com.liveramp.ats_sdk_android.sample.databinding.FragmentGetEnvelopeBinding
import com.liveramp.ats_sdk_android.sample.internal.stringRepresentation
class GetEnvelopeFragment : Fragment() {
    private lateinit var binding: FragmentGetEnvelopeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetEnvelopeBinding.inflate(inflater)
        binding.btnGetEnvelope.setOnClickListener {
            val appID = binding.etAppId.text.toString()
            // You should provide your appID here
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
                //Covering case if init is successful
                if (success) {
                    //Calling getEnvelope method from sdk
                    // You can use email, phone or custom identifier to get envelope
                    val identifier = LREmailIdentifier("example@mail.com")
                    //val identifier = LRAtsManager.getEnvelope(LRPhoneIdentifier("0123456789"))
                    //val identifier = LRAtsManager.getEnvelope(LRCustomIdentifier("54321:abc123"))
                    LRAtsManager.getEnvelope(identifier) { envelope, error ->
                        error?.let { binding.tvEnvelopes.text = error.message }
                        envelope?.let { binding.tvEnvelopes.text = envelope.stringRepresentation() }
                    }
                }
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            GetEnvelopeFragment()
    }
}