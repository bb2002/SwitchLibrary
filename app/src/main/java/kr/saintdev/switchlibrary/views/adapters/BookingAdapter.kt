package kr.saintdev.switchlibrary.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kr.saintdev.switchlibrary.R

class BookingAdapter : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        if(convertView == null) {
            val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.boo)
        }
    }

    override fun getItem(position: Int): Any {
    }

    override fun getItemId(position: Int): Long {
    }

    override fun getCount(): Int {
    }
}