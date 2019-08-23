package kr.saintdev.switchlibrary.views.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kr.saintdev.switchlibrary.R
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    private lateinit var mTimer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()

        this.mTimer = Timer()
        this.mTimer.schedule(timerTask {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        },2500)
    }

    override fun onStop() {
        super.onStop()
        this.mTimer.cancel()
    }
}
