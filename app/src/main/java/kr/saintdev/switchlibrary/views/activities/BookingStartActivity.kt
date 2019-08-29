package kr.saintdev.switchlibrary.views.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_booking_start.*
import kr.saintdev.switchlibrary.R
import kr.saintdev.switchlibrary.views.adapters.BookingAdapter

class BookingStartActivity : AppCompatActivity() {
    private lateinit var seatAdapter: BookingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_start)

        val array = arrayListOf<Int>()
        for(i in 1 .. 50) {
            array.add(i)
        }
        seatAdapter = BookingAdapter(array)
        booking_seat_list.adapter = seatAdapter
        booking_seat_list.onItemClickListener = SeatItemClickListener()
    }

    inner class SeatItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        }
    }
}
