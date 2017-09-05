package tw.com.nanasutdio.firebasenotification

import android.app.NotificationChannel
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log


/**
 * FirebaseNotification
 * Created by Sean Lin on 2017/8/21 下午11:30.
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage?.data != null) {// 檢查 data 是不是空的
            val title = remoteMessage.data["title"]
                    ?: "Message Title null" // Title 拿到空值的時候的處理
            val body = remoteMessage.data["body"]
                    ?: "Message Body null"// Body 拿到空值的時候的處理

            sendNotification(title, body)
        }
    }

    private fun sendNotification(title: String, body: String) {

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)

        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {// Android 8.0 中需加入 Channel
            val channelId = "Channel ID"
            val notificationChannel = NotificationChannel(channelId/*ID of notification*/,
                    "Channel Name", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
            notificationBuilder.setChannelId(channelId)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

}