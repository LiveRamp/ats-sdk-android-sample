package com.liveramp.ats_sdk_android.sample.internal

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

//This class should not be used in production, it is only for demonstration purposes
//We simulate the user adding consent
object ConsentManager {
    private lateinit var preferences: SharedPreferences
    fun simulateUserConsent(context: Context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        giveCCPAConsent()
        giveGDPRConsent()
        giveGPPNationalConsent()
    }

    private fun setSharedPreferencesKey(key: String, value: Any) {
        val editor = preferences.edit()
        when (value) {
            is Int -> editor.putInt(key, value)
            is String -> editor.putString(key, value)
            else -> throw IllegalArgumentException()
        }
        editor.apply()
    }

    private fun giveCCPAConsent() {
        setSharedPreferencesKey("IABUSPrivacy_String", "1YNN")
    }

    private fun giveGDPRConsent() {
        setSharedPreferencesKey(
            "IABTCF_TCString",
            "CPKZ42oPKZ5YtADABCENBlCgAP_AAAAAAAAAAwwAQAwgDDABADCAAA.YAAAAAAAA4AA"
        )
        setSharedPreferencesKey(
            "IABTCF_VendorConsents",
            "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001"
        )
        setSharedPreferencesKey("IABTCF_PurposeConsents", "1111111111")
    }

    private fun giveGPPNationalConsent() {
        setSharedPreferencesKey("IABGPP_HDR_GppString", "DBAA")
        setSharedPreferencesKey("IABGPP_USNAT_Version", 1)
        setSharedPreferencesKey("IABGPP_USNAT_SharingNotice", 1)
        setSharedPreferencesKey("IABGPP_USNAT_SaleOptOutNotice", 1)
        setSharedPreferencesKey("IABGPP_USNAT_SharingOptOutNotice", 1)
        setSharedPreferencesKey("IABGPP_USNAT_TargetedAdvertisingOptOutNotice", 1)
        setSharedPreferencesKey("IABGPP_USNAT_SensitiveDataProcessingOptOutNotice", 0)
        setSharedPreferencesKey("IABGPP_USNAT_SensitiveDataLimitUseNotice", 1)
        setSharedPreferencesKey("IABGPP_USNAT_SaleOptOut", 2)
        setSharedPreferencesKey("IABGPP_USNAT_SharingOptOut", 2)
        setSharedPreferencesKey("IABGPP_USNAT_TargetedAdvertisingOptOut", 2)
        setSharedPreferencesKey("IABGPP_USNAT_SensitiveDataProcessing", "0_0_0_0_0_0_0_0_0_0_0_0")
        setSharedPreferencesKey("IABGPP_USNAT_KnownChildSensitiveDataConsents", "0_0")
        setSharedPreferencesKey("IABGPP_USNAT_PersonalDataConsents", 2)
        setSharedPreferencesKey("IABGPP_USNAT_MspaCoveredTransaction", 1)
        setSharedPreferencesKey("IABGPP_USNAT_MspaOptOutOptionMode", 1)
        setSharedPreferencesKey("IABGPP_USNAT_MspaServiceProviderMode", 1)
    }
}