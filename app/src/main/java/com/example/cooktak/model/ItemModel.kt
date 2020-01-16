package com.example.cooktak.model

import android.os.Parcel
import android.os.Parcelable

class ItemModel() : Parcelable {
    var content: String? = null

    constructor(str: String) : this() {
        content = str
    }

    constructor(parcel: Parcel) : this() {
        content = parcel.readString()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.run {
            writeString(this@ItemModel.content)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemModel> {
        override fun createFromParcel(parcel: Parcel): ItemModel {
            return ItemModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemModel?> {
            return arrayOfNulls(size)
        }
    }


}