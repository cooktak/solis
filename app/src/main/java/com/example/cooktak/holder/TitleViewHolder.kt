package com.example.cooktak.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.cooktak.R
import com.example.cooktak.model.TitleModel
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder

class TitleViewHolder(itemView: View) : GroupViewHolder(itemView) {
    private val titleTex: TextView = itemView.findViewById(R.id.tex_title_item)
    private val subTex: TextView = itemView.findViewById(R.id.tex_sub_item)
    private val imgView : ImageView = itemView.findViewById(R.id.img_item)

    fun bind(items: TitleModel) {
        val str = items.title.split("-")

        imgView.setImageResource(str[0].toInt())
        titleTex.text = str[1]
        subTex.text = str[2]
    }
}