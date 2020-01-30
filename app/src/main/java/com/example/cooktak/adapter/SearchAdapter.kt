package com.example.cooktak.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.cooktak.R
import com.example.cooktak.model.searchModel
import java.util.*

class SearchAdapter(val context: Context, private val list: ArrayList<searchModel>) :
    BaseAdapter() {
    val inflater: LayoutInflater = LayoutInflater.from(context)
    val searchList: ArrayList<searchModel> = list

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val holder = ViewHolder()
        var item = searchList[position]

        if (view == null) {
            view = inflater.inflate(R.layout.community_rv_item, null)
        }
        return view!!
    }

    override fun getItem(position: Int): Any = searchList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = searchList.size

    inner class ViewHolder {
        lateinit var tex_title: TextView
        lateinit var img_content: ImageView
    }

    fun filter(charText: String) {
        val charStr = charText.toLowerCase(Locale.getDefault())
        searchList.clear()
        if (charStr.isEmpty()) {
            searchList.addAll(list)
        } else {
            list.forEach {
                val name: String = it.title
                if (name.toLowerCase().contains(charStr)) {
                    searchList.add(it)
                }
            }
        }
    }
}