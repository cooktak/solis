package com.example.cooktak.holder

import android.app.AlertDialog
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.cooktak.R
import com.example.cooktak.model.TitleModel
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder

class TitleViewHolder(itemView: View) : GroupViewHolder(itemView) {
    private val titleTex: TextView = itemView.findViewById(R.id.tex_title_item)
    private val subTex: TextView = itemView.findViewById(R.id.tex_sub_item)
    private val imgView: ImageView = itemView.findViewById(R.id.img_item)
    private val starView: ImageView = itemView.findViewById(R.id.img_star_item)

    fun bind(items: TitleModel) {
        val str = items.title.split("-")
        var starFlag = false

        imgView.setImageResource(str[0].toInt())
        titleTex.text = str[1]
        subTex.text = str[2]
        starView.setImageResource(R.drawable.star_off)

        starView.setOnClickListener {
            if (starFlag) {
                val builder = AlertDialog.Builder(itemView.context)
                builder.setMessage("즐겨찾기를 취소하시곘습니까?")
                builder.setPositiveButton("네") { _, _ ->
                    starFlag = !starFlag
                    starView.setImageResource(R.drawable.star_off)
                }.setNegativeButton("아니오") { _, _ -> }.show()

            } else {
                starFlag = !starFlag
                starView.setImageResource(R.drawable.star_on)
            }
        }
    }
}
