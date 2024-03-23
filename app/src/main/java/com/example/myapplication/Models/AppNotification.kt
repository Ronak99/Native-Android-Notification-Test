package com.example.myapplication.Models

import Article
import com.example.myapplication.CustomApplication
import kotlinx.serialization.*

enum class NotificationType {
    delivery,
    cancel,
}

@Serializable
data class AppNotification (
    val type: NotificationType,
    val article: Article,
    val id: Int = CustomApplication.notificationIdentifier.getNotificationId(article.id, type),
)
