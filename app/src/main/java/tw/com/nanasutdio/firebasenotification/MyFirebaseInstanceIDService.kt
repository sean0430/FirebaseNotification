package tw.com.nanasutdio.firebasenotification

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * FirebaseNotification
 * Created by Sean Lin on 2017/8/27 上午12:04.
 */
class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {

    private val TAG: String = MyFirebaseInstanceIDService::class.java.simpleName

    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val firebaseToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG,"FirebaseToken = $firebaseToken")
    }
}