package com.example.cooktak.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cooktak.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_search.view.*


class SearchFragment : Fragment() {
    val chipList = listOf("파스타", "피자", "스테이크", "볶음밥", "면", "빵", "쿠키", "오븐구이", "찌게류", "와인")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        return view
    }
}