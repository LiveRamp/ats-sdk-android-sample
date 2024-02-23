package com.liveramp.ats_sdk_android.sample.internal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.liveramp.ats_sdk_android.sample.sample.GetEnvelopeFragment
import com.liveramp.ats_sdk_android.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val getEnvelopeFragment = GetEnvelopeFragment.newInstance()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentContainer.id, getEnvelopeFragment)
        fragmentTransaction.commit()
    }
}