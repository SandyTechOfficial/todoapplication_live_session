package com.sandytech.mainactivity

interface ItemRowListener {

    fun update(itemID : String, isDone : Boolean)

    fun delete(itemID: String)

}