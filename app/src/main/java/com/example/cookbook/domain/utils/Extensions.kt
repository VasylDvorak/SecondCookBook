package com.example.cookbook.domain.utils

import android.widget.Toast
import com.example.cookbook.R
import com.example.cookbook.application.App

class Extensions {
        fun showToast(length: Int = Toast.LENGTH_LONG) {
            val context = App.instance.applicationContext
            Toast.makeText(App.instance.applicationContext,
                context!!.getString(R.string.check_internet), length).show()
        }
}