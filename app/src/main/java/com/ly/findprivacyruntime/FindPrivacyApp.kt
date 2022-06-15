package com.ly.findprivacyruntime

import android.app.Application

class FindPrivacyApp :Application(){

    override fun onCreate() {
        super.onCreate()
        FindPrivacyUtils.init(this)
    }


}