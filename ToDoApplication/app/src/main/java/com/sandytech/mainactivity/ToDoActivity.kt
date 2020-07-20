package com.sandytech.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ToDoActivity : AppCompatActivity(), ItemRowListener {

    private lateinit var mDatabase : FirebaseDatabase
    private lateinit var mReference: DatabaseReference
    private lateinit var mAuth : FirebaseAuth

    private var toDoItemList : MutableList<ToDoItemModel>? = null
    private lateinit var adapter: ToDoItemAdapter
    private var listViewItem : ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)

        listViewItem = findViewById(R.id.getDataListView)

        mDatabase = FirebaseDatabase.getInstance()
        mReference = mDatabase.reference
        mAuth = FirebaseAuth.getInstance()

        toDoItemList = mutableListOf()
        adapter = ToDoItemAdapter(this, toDoItemList!!)
        listViewItem!!.adapter = adapter

        mReference.orderByKey().addListenerForSingleValueEvent(itemListener)
    }

    private val itemListener : ValueEventListener = object : ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
            Log.d("TAG", "onCancelled" + error.toException())
        }

        override fun onDataChange(snapshot: DataSnapshot) {
            getDataInList(snapshot)
        }

    }

    private fun getDataInList(snapshot: DataSnapshot) {

        val items = snapshot.children.iterator()

        // check if collection is there

        if(items.hasNext())
        {
            val todoListIndex = items.next()
            val itemsIterator = todoListIndex.children.iterator()

            // check if document is there
            while (itemsIterator.hasNext()) {
                //get current item
                val currentItem = itemsIterator.next()
                val toDoItemModel = ToDoItemModel.create()

                // ek jagah pe store krne k leye
                val map = currentItem.value as HashMap<*, *>

                //key will be firebase id

                toDoItemModel.objectID = currentItem.key
                toDoItemModel.itemText = map["itemText"] as String?
                toDoItemModel.isDone = map["done"] as Boolean?
                toDoItemList?.add(toDoItemModel)

            }
        }

        adapter.notifyDataSetChanged()
    }

    fun actionSwap(view: View) {
        val intent = Intent(this, ImageSwap::class.java)
        startActivity(intent)
    }
    fun actionRoll(view: View) {
        val intent = Intent(this, RollTheDiceActivity::class.java)
        startActivity(intent)
    }

    fun addToDoItem(view: View) {

        val itemEditText = EditText(this)

        val alert = AlertDialog.Builder(this)
        alert.setMessage("Add New Item")
        alert.setTitle("Enter To-Do Item Text")
        alert.setView(itemEditText)

        alert.setPositiveButton("Submit") {
            dialog, positveButton ->
                val todoItem = ToDoItemModel.create()
                todoItem.itemText = itemEditText.text.toString()
                todoItem.isDone = false

                val newItem = mReference.child("todo_item").push()
                todoItem.objectID = newItem.key

                newItem.setValue(todoItem)

                dialog.dismiss()

                Toast.makeText(this, "Item is Saved", Toast.LENGTH_SHORT).show()
        }
        alert.show()
    }

    fun actionSignOut(view: View) {
        mAuth.signOut()
        // activity change
        val intent = Intent(this, LoginRegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun update(itemID: String, isDone: Boolean) {
        // refer of to-do item (update)
        val itemRef = mReference.child("todo_item").child(itemID)
        itemRef.child("done").setValue(isDone)
    }

    override fun delete(itemID: String) {
        //delete
        val itemRef = mReference.child("todo_item").child(itemID)
        itemRef.removeValue()
    }
}