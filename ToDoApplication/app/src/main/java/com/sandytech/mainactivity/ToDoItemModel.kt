package com.sandytech.mainactivity

class ToDoItemModel {

    companion object Factory {
        fun create() : ToDoItemModel = ToDoItemModel()
    }

    var objectID : String? = null
    var itemText : String? = null
    var isDone : Boolean? = false

}