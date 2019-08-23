package kr.saintdev.switchlibrary.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kakao.auth.Session
import kr.saintdev.switchlibrary.R
import kr.saintdev.switchlibrary.engine.auth.kakao.SessionCallback

class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragmn_login, container, false)

        return view
    }
}