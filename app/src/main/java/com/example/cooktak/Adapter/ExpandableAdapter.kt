package com.example.cooktak.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cooktak.R
import com.example.cooktak.holder.ItemViewHolder
import com.example.cooktak.holder.TitleViewHolder
import com.example.cooktak.model.ItemModel
import com.example.cooktak.model.TitleModel
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup


class ExpandableAdapter(groups: List<ExpandableGroup<*>?>?) :
    ExpandableRecyclerViewAdapter<TitleViewHolder, ItemViewHolder>(groups) {
    override fun onCreateGroupViewHolder(parent: ViewGroup?, viewType: Int): TitleViewHolder {
        val v: View =
            LayoutInflater.from(parent?.context).inflate(R.layout.expandable_title, parent, false)

        return TitleViewHolder(v)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        val v: View =
            LayoutInflater.from(parent?.context).inflate(R.layout.expandable_item, parent, false)

        return ItemViewHolder(v)
    }

    override fun onBindChildViewHolder(
        holder: ItemViewHolder?,
        flatPosition: Int,
        group: ExpandableGroup<*>?,
        childIndex: Int
    ) {
        val item1: ItemModel = group?.items?.get(childIndex) as ItemModel
        holder?.bind(item1)
    }

    override fun onBindGroupViewHolder(
        holder: TitleViewHolder?,
        flatPosition: Int,
        group: ExpandableGroup<*>?
    ) {
        val item2: TitleModel = group as TitleModel
        holder?.bind(item2)
    }
}
