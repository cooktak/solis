package com.example.cooktak.ui.main.community

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooktak.R
import com.example.cooktak.adapter.ExpandableAdapter
import com.example.cooktak.adapter.GridRecyclerViewAdapter
import com.example.cooktak.model.ItemModel
import com.example.cooktak.model.TitleModel
import kotlinx.android.synthetic.main.fragment_community.view.*


class CommunityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_community, container, false)

        var expandFlag = false
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

        val arr3: ArrayList<ItemModel> = arrayListOf()
        arr3.add(ItemModel("ssd"))
        viewList.add(TitleModel(R.drawable.example, "test4", "test1", arr3))

        val arr4: ArrayList<ItemModel> = arrayListOf()
        arr4.add(ItemModel("fgff"))
        viewList.add(TitleModel(R.drawable.example, "test5", "test1", arr4))

        val arr5: ArrayList<ItemModel> = arrayListOf()
        arr5.add(ItemModel("dsghs"))
        viewList.add(TitleModel(R.drawable.example, "test6", "test1", arr5))

        val arr6: ArrayList<ItemModel> = arrayListOf()
        arr6.add(ItemModel("dsfya"))
        viewList.add(TitleModel(R.drawable.example, "test7", "test1", arr6))

        val arr7: ArrayList<ItemModel> = arrayListOf()
        arr7.add(ItemModel("hjuj"))
        viewList.add(TitleModel(R.drawable.example, "test8", "test1", arr7))

        view.recycler_recommend.layoutManager = LinearLayoutManager(view.context)
        view.recycler_recommend.adapter = ExpandableAdapter(viewList)

        view.btn_category_recommend.setOnClickListener {
            expandFlag = !expandFlag

            if (expandFlag) {
                view.btn_category_recommend.setImageResource(R.drawable.baseline_view_module_black_48)
                view.recycler_recommend.layoutManager = GridLayoutManager(view.context, 3)
                view.recycler_recommend.setHasFixedSize(true)
                view.recycler_recommend.adapter = GridRecyclerViewAdapter(view.context, viewList)
            } else {
                view.btn_category_recommend.setImageResource(R.drawable.baseline_view_list_black_48)
                view.recycler_recommend.layoutManager = LinearLayoutManager(view.context)
                view.recycler_recommend.adapter = ExpandableAdapter(viewList)
            }
        }

        view.fab_create_community.setOnClickListener {
            val intent = Intent(this.activity, CreateActivity::class.java)
            startActivity(intent)
        }
        return view
    }
}