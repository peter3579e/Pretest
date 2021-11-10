package com.peter.pretest

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi

object PretestFunctions {

    /**
     *
     * For selecting corresponding logo depends on its input name
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun selectImage(symbol: String): Drawable? {
        return when (symbol) {
            "BCH" -> PretestApplication.instance.getDrawable(R.drawable.logo_bch)
            "BNB" -> PretestApplication.instance.getDrawable(R.drawable.logo_bnb)
            "CRO" -> PretestApplication.instance.getDrawable(R.drawable.logo_cro)
            "EOS" -> PretestApplication.instance.getDrawable(R.drawable.logo_eos)
            "BTC" -> PretestApplication.instance.getDrawable(R.drawable.logo_btc)
            "ETH" -> PretestApplication.instance.getDrawable(R.drawable.logo_eth)
            "XRP" -> PretestApplication.instance.getDrawable(R.drawable.logo_xrp)
            "LTC" -> PretestApplication.instance.getDrawable(R.drawable.logo_ltc)
            "LINK" -> PretestApplication.instance.getDrawable(R.drawable.logo_link)
            "NEO" -> PretestApplication.instance.getDrawable(R.drawable.logo_neo)
            "ETC" -> PretestApplication.instance.getDrawable(R.drawable.logo_etc)
            "ONT" -> PretestApplication.instance.getDrawable(R.drawable.logo_ont)
            "CUC" -> PretestApplication.instance.getDrawable(R.drawable.logo_cuc)
            "USDC" -> PretestApplication.instance.getDrawable(R.drawable.logo_usdc)
            else -> null
        }
    }

}