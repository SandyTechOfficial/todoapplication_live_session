package com.sandytech.mainactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView

class ToDoItemAdapter (context : Context, todoItemList : MutableList<ToDoItemModel>) : BaseAdapter() {

    private var itemList = todoItemList
    private val mInflater : LayoutInflater = LayoutInflater.from(context)
    private val itemRowListener : ItemRowListener = context as ItemRowListener

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val objectID : String = itemList[position].objectID as String
        val itemText : String = itemList[position].itemText as String
        val isDone : Boolean = itemList[position].isDone as Boolean

        val view : View
        val listHolder : ListViewHolder

        if (convertView == null) {
            view = mInflater.inflate(R.layout.row_items, parent, false)
            listHolder = ListViewHolder(view)
            view.tag = listHolder
        }
        else {
            view = convertView
            listHolder = view.tag as ListViewHolder
        }

        listHolder.checkIsDone.setOnClickListener {
            itemRowListener.update(objectID, !isDone)
        }

        listHolder.btnDelete.setOnClickListener{
            itemRowListener.delete(objectID)
        }

        listHolder.todoText.text = itemText
        listHolder.checkIsDone.isChecked = isDone

        return view
    }

    override fun getItem(p0: Int): Any {
        return itemList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return  p0.toLong()
    }

    override fun getCount(): Int {
        return  itemList.size
    }

    private class ListViewHolder(view: View) {
        val checkIsDone : CheckBox = view.findViewById(R.id.checkDone)
        val todoText : TextView = view.findViewById(R.id.txtToDoTask)
        val btnDelete : ImageButton = view.findViewById(R.id.btnDelete)
    }
}