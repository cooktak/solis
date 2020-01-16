package com.example.cooktak.holder

import android.view.View
import android.widget.TextView
import com.example.cooktak.R
import com.example.cooktak.model.ItemModel
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder


class ItemViewHolder(itemView: View) : ChildViewHolder(itemView) {
    private val contentTex: TextView = itemView.findViewById(R.id.tex_content_item)

    fun bind(item1: ItemModel) {
        contentTex.text = item1.content
    }
}
