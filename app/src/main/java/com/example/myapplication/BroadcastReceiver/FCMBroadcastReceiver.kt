package com.example.myapplication.BroadcastReceiver

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import com.example.myapplication.CustomApplication
import com.example.myapplication.Services.NotificationService
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FCMService : FirebaseMessagingService() {
    var remoteMessage: RemoteMessage? = null

    @SuppressLint("WrongConstant")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages.
        val notificationService = NotificationService(applicationContext)

        val payloadIsEmpty = remoteMessage.data.size == 0
        Log.d("FCM Trigger", "Message received")
        if (payloadIsEmpty) {
            Log.d("FCM Trigger", "Payload was empty")
            return
        }
        Log.d(TAG, "Payload is not empty")
        val payload = remoteMessage.data
    }

    override fun onMessageSent(s: String) {
        super.onMessageSent(s)
        Log.d(TAG, "Message has been sent")
    }

    override fun onTaskRemoved(rootIntent: Intent) {
        Log.e(TAG, "Task has been removed")
    }

    override fun onDestroy() {
        Log.d(TAG, "Task has been destroyed")
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.d(TAG, "Device is on low memory")
    }

    override fun handleIntentOnMainThread(intent: Intent): Boolean {
        super.handleIntentOnMainThread(intent)
        return false
    }

    companion object {
        private const val TAG = "FCM Trigger"
    }
}