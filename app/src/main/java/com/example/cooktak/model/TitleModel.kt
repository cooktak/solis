package com.example.cooktak.model

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class TitleModel(img: Int?, title: String?, sub: String?, items: List<ItemModel?>?) :
    ExpandableGroup<ItemModel?>("${img.toString()}-$title-$sub", items)