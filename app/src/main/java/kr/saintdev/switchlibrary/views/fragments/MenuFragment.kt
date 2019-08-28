package kr.saintdev.switchlibrary.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kr.saintdev.switchlibrary.R

class MenuFragment : Fragment() {
    private lateinit var mBookingStartButton: Button
    private lateinit var mMyBookingButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragmn_booking, container, false)
        this.mBookingStartButton = view.findViewById(R.id.booking_start)
        this.mMyBookingButton = view.findViewById(R.id.my_booking)
        this.mBookingStartButton.setOnClickListener(BookingStartClickListener())
        this.mMyBookingButton.setOnClickListener(MyBookingStartClickListener())

        return view
    }

    inner class BookingStartClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            
        }
    }

    inner class MyBookingStartClickListener : View.OnClickListener {
        override fun onClick(v: View?) {

        }
    }
}