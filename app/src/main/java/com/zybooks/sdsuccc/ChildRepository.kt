package com.zybooks.sdsuccc

import android.content.Context

class ChildRepository private constructor(context: Context) {

    var childList: MutableList<Child> = mutableListOf()

    companion object {
        private var instance: ChildRepository? = null

        fun getInstance(context: Context): ChildRepository {
            if (instance == null) {
                instance = ChildRepository(context)
            }
            return instance!!
        }
    }

    init {
        val children = context.resources.getStringArray(R.array.children)
        val information = context.resources.getStringArray(R.array.information)
        for (i in children.indices) {
            childList.add(Child(i + 1, children[i], information[i]))
        }
    }

    fun getChild(childId: Int): Child? {
        return childList.find { it.id == childId }
    }
}

