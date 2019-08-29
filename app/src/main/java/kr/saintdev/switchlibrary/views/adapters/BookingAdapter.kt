package kr.saintdev.switchlibrary.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kr.saintdev.switchlibrary.R

class BookingAdapter(val seatIndex: ArrayList<Int>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = if(convertView == null) {
            val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(R.layout.seat_item_layout, parent, false)
        } else {
            convertView
        }

        val seatIndexView = view.findViewById<TextView>(R.id.item_text)
        seatIndexView.text = seatIndex[position].toString()

        return view
    }

    override fun getItem(position: Int) = seatIndex[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = seatIndex.size
}