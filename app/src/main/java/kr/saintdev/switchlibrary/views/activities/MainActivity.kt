package kr.saintdev.switchlibrary.views.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.request.MeV2Request
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import kotlinx.android.synthetic.main.activity_main.*
import kr.saintdev.switchlibrary.R
import kr.saintdev.switchlibrary.engine.auth.kakao.SessionCallback
import kr.saintdev.switchlibrary.engine.auth.switchlib.SwitchLibAuthContainer
import kr.saintdev.switchlibrary.engine.auth.switchlib.SwitchLibAuthManager
import kr.saintdev.switchlibrary.engine.lib.SwitchLibDialog
import kr.saintdev.switchlibrary.views.fragments.LoginFragment
import kr.saintdev.switchlibrary.views.fragments.MenuFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.security.MessageDigest


class MainActivity : AppCompatActivity() {
    private lateinit var callback: SessionCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(SwitchLibAuthManager.getCurrentToken() == null) {
            UserManagement.getInstance().me(object : MeV2ResponseCallback() {
                override fun onSuccess(result: MeV2Response?) {
                    // 로그인이 이미 된 사용자.
                    if(result != null) {
                        val notifiDialog = SwitchLibDialog.openNotificationDialog(
                            R.string.login_title,
                            R.string.login_title_message,
                            this@MainActivity
                        )
                        onKakaoLoginSuccessAndRegist(result, notifiDialog)
                    } else {
                        onSessionClosed(null)
                    }
                }

                override fun onSessionClosed(errorResult: ErrorResult?) {
                    // 로그인이 필요한 사용자.
                    // 콜백 생성
                    callback = SessionCallback(this@MainActivity)
                    Session.getCurrentSession().addCallback(callback)
                    Session.getCurrentSession().checkAndImplicitOpen()

                    // 프래그먼트 생성
                    val fragmnTrans = supportFragmentManager.beginTransaction()
                    fragmnTrans.replace(R.id.main_content, LoginFragment())
                    fragmnTrans.commit()
                }
            })
        }
//
//        try {
//            val info = packageManager.getPackageInfo("kr.saintdev.switchlibrary", PackageManager.GET_SIGNATURES)
//            for(sign in info.signatures) {
//                val md = MessageDigest.getInstance("SHA")
//                md.update(sign.toByteArray())
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
//            }
//        } catch (ex: Exception) {
//
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStop() {
        super.onStop()
        Session.getCurrentSession().removeCallback(this.callback)
    }

    fun onLoginSuccessed() {
        // 서버로 요청을 위해 대기 창을 엽니다.
        val notifiDialog = SwitchLibDialog.openNotificationDialog(R.string.login_title, R.string.login_title_message, this)
        UserManagement.getInstance().me(object : MeV2ResponseCallback() {
            override fun onSuccess(result: MeV2Response?) {
                // 로그인이 이미 된 사용자.
                if(result != null) {
                    onKakaoLoginSuccessAndRegist(result, notifiDialog)
                } else {
                    onSessionClosed(null)
                }
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                // 로그인이 필요한 사용자.
                // 콜백 생성
                callback = SessionCallback(this@MainActivity)
                Session.getCurrentSession().addCallback(callback)
                Session.getCurrentSession().checkAndImplicitOpen()

                // 프래그먼트 생성
                val fragmnTrans = supportFragmentManager.beginTransaction()
                fragmnTrans.replace(R.id.main_content, LoginFragment())
                fragmnTrans.commit()
            }
        })
    }

    fun onLoginFailed(exception: KakaoException?) {
        val errorTitle = getString(R.string.login_error_title)
        val errorMessage = getString(R.string.login_error_title_message) + exception?.message
        SwitchLibDialog.openMessageDialog(errorTitle, errorMessage, this@MainActivity)
    }


    /**
     * @Date 09.02 2019
     * KakaoLogin Response
     * 카카오 로그인을 마치고, SwitchLib 에 요청을 보냅니다.
     */
    private fun onKakaoLoginSuccessAndRegist(result: MeV2Response, notifiDialog: AlertDialog) {
        val switchlibService = SwitchLibAuthManager.getInstance()
        switchlibService.tryAuth(result, object : Callback<SwitchLibAuthContainer> {
            override fun onFailure(call: Call<SwitchLibAuthContainer>, t: Throwable?) {
                // Switch lib request failed.
                notifiDialog.dismiss()
                SwitchLibDialog.openMessageDialog(R.string.login_neterror_title, R.string.login_neterror_title_message, this@MainActivity)
            }

            override fun onResponse(
                call: Call<SwitchLibAuthContainer>,
                response: Response<SwitchLibAuthContainer>
            ) {
                // Switch lib login response.
                notifiDialog.dismiss()
                if(response.isSuccessful) {
                    // 요청에 성공 했습니다.
                    val newToken = response.body()?.body?.get("token")?.asString
                    if(newToken == null) {
                        // Token recv failed.
                        SwitchLibDialog.openMessageDialog(R.string.login_tokenerror_title, R.string.login_tokenerror_title_message, this@MainActivity)
                    } else {
                        // Token recv success.
                        val authManager = SwitchLibAuthManager.getInstance()
                        authManager.setRefreshToken(newToken)

                        // Login successed.
                    }
                } else {
                    // 요청에 오류가 발생 했습니다.
                    onFailure(call, null)
                }
            }
        })
    }

    private fun onKakaoLoginFailed(errorResult: ErrorResult?, notifiDialog: AlertDialog) {
        val errorTitle = getString(R.string.login_error_title)
        val errorMessage = getString(R.string.login_error_title_message) + errorResult?.errorMessage
        SwitchLibDialog.openMessageDialog(errorTitle, errorMessage, this@MainActivity)
    }
}