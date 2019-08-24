package kr.saintdev.switchlibrary.views.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.request.MeV2Request
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import kr.saintdev.switchlibrary.R
import kr.saintdev.switchlibrary.engine.auth.kakao.SessionCallback
import kr.saintdev.switchlibrary.engine.auth.switchlib.SwitchLibAuthContainer
import kr.saintdev.switchlibrary.engine.auth.switchlib.SwitchLibAuthManager
import kr.saintdev.switchlibrary.engine.lib.SwitchLibDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var callback: SessionCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.callback = SessionCallback(this)
        Session.getCurrentSession().addCallback(this.callback)
        Session.getCurrentSession().checkAndImplicitOpen()
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

                if(result == null) {
                    // RESPONSE -> NULL
                    onSessionClosed(null)
                } else {
                    // Response -> NOT NULL.
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
                                val authContainer = response.body()
                                Log.e("Switch", "OKOKOKOKOKO!!!!!!")
                                Log.e("Switch", "status : ${authContainer?.status}")
                            } else {
                                // 요청에 오류가 발생 했습니다.
                                onFailure(call, null)
                            }
                        }
                    })
                }
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                notifiDialog.dismiss()

                val errorTitle = getString(R.string.login_error_title)
                val errorMessage = getString(R.string.login_error_title_message) + errorResult?.errorMessage
                SwitchLibDialog.openMessageDialog(errorTitle, errorMessage, this@MainActivity)
            }
        })
    }

    fun onLoginFailed(exception: KakaoException?) {
        val errorTitle = getString(R.string.login_error_title)
        val errorMessage = getString(R.string.login_error_title_message) + exception?.message
        SwitchLibDialog.openMessageDialog(errorTitle, errorMessage, this@MainActivity)
    }
}