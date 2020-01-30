package com.example.cooktak.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooktak.R
import com.example.cooktak.adapter.ExpandableAdapter
import com.example.cooktak.model.ItemModel
import com.example.cooktak.model.TitleModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_search.view.*


class SearchFragment : Fragment() {
    private val chipList = listOf("파스타", "피자", "스테이크", "볶음밥", "면", "빵", "쿠키", "오븐구이", "찌게류", "와인")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        view.edt_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchStr = view.edt_search.text.toString()
                Toast.makeText(view.context, searchStr, Toast.LENGTH_SHORT).show()
                true
            } else
                false
        }

        val chipGroup = view.findViewById<ChipGroup>(R.id.chip_tag_search)

        chipList.forEach {
            val chip = Chip(view.context)
            chip.text = it
            chip.isCheckable = true

            chipGroup.addView(chip)
        }

        view.tag_layout.setOnClickListener {
            Log.d("Touch!!!!!!", "aaaaaa")
            if (chipGroup.visibility == View.GONE) {
                chipGroup.visibility = View.VISIBLE
                view.tag_status.text = getString(R.string.text_tag_up)
            } else {
                chipGroup.visibility = View.GONE
                view.tag_status.text = getString(R.string.text_tag_down)
            }

        }


        val viewList: ArrayList<TitleModel> = arrayListOf()

        val arr: ArrayList<ItemModel> = arrayListOf()
        arr.add(ItemModel("abac"))
        viewList.add(TitleModel(R.drawable.example, "까르보나라", "맛있는 크림스파게티~", arr))

        val arr1: ArrayList<ItemModel> = arrayListOf()
        arr1.add(ItemModel("adad"))
        viewList.add(TitleModel(R.drawable.example, "test2", "test1", arr1))

        val arr2: ArrayList<ItemModel> = arrayListOf()
        arr2.add(ItemModel("aaaa"))
        viewList.add(TitleModel(R.drawable.example, "test3", "test1", arr2))

        view.recycler_search.layoutManager = LinearLayoutManager(view.context)
        view.recycler_search.adapter = ExpandableAdapter(viewList)

        return view
    }
}