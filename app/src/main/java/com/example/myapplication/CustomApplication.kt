package com.example.myapplication

import NotificationIdentifier
import android.app.Application

class CustomApplication : Application() {

    companion object {
        // Static member variable
        val notificationIdentifier: NotificationIdentifier = NotificationIdentifier()
    }

    override fun onCreate() {
        super.onCreate()
    }
}