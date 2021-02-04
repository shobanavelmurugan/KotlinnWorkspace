/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.shobanasha.navigationGraph.helper

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.google.gson.Gson
import com.shobanasha.navigationGraph.R
import com.shobanasha.navigationGraph.model.Letter

class NotificationHelper(val context: Context) {

    companion object {
        private const val CHANNEL_ID_LETTER = "channel_letter"
        private const val CHANNEL_NAME_LETTER = "letter"
        private const val EXTRA_LETTER = "letter"
        private const val NOTIFICATION_ID_LETTER = 1
    }

    private val gson by lazy { Gson() }

    fun sendLocalNotification(letter: Letter) {
        val pendingIntent = buildPendingIntentFromNavigation(letter)
        val notification = buildLetterNotification(letter, pendingIntent!!)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID_LETTER,
                    CHANNEL_NAME_LETTER,
                    NotificationManager.IMPORTANCE_HIGH
                )
            )
        }
        notificationManager.notify(NOTIFICATION_ID_LETTER, notification)
    }

    private fun buildLetterNotification(
        letter: Letter,
        pendingIntent: PendingIntent
    ): Notification? {
        val contentText = "${letter.fromName} has sent you a love letter."
        return NotificationCompat.Builder(context, CHANNEL_ID_LETTER)
            .setContentTitle(letter.title)
            .setContentText(contentText)
            .setSmallIcon(R.drawable.ic_notification)
            .setAutoCancel(true)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(contentText)
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .build()
    }

    private fun buildPendingIntentFromNavigation(letter: Letter): PendingIntent? {
        val bundle = Bundle()
        bundle.putString(EXTRA_LETTER, gson.toJson(letter))
        return NavDeepLinkBuilder(context)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.presentationFragment)
            .setGraph(R.navigation.nav_graph)
            .setArguments(bundle)
            .createPendingIntent()
    }
}
