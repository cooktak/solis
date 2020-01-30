package com.example.cooktak.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cooktak.R
import com.example.cooktak.model.TitleModel
import kotlinx.android.synthetic.main.community_rv_item.view.*

class GridRecyclerViewAdapter(
    val context: Context,
    private val viewList: ArrayList<TitleModel>
) :
    RecyclerView.Adapter<GridRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.community_rv_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = viewList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(viewList[position])

        holder.itemView.setOnClickListener {
            Toast.makeText(
                context,
                "Title : ${holder.itemView.tex_title1.text}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(model: TitleModel) {
            val str = model.title.split("-")

            itemView.img_item1.setImageResource(str[0].toInt())
            itemView.tex_title1.text = str[1]
            itemView.tex_sub1.text = str[2]
        }
    }
}

