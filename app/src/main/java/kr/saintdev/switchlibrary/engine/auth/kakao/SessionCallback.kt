package kr.saintdev.switchlibrary.engine.auth.kakao

import android.util.Log
import com.kakao.auth.ISessionCallback
import com.kakao.usermgmt.UserManagement
import com.kakao.util.exception.KakaoException
import kr.saintdev.switchlibrary.views.activities.MainActivity

class SessionCallback(private val delegateActivity: MainActivity) : ISessionCallback {

    override fun onSessionOpenFailed(exception: KakaoException?) {
        delegateActivity.onLoginFailed(exception)
    }

    override fun onSessionOpened() {
        delegateActivity.onLoginSuccessed()
    }
}