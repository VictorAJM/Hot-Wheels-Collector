package com.example.hotwheelscollector

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseManager(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "ITEMS"
        private const val COLUMN_ID = "id"
        private const val COLUMN_ITEM_NAME = "itemName"
        private const val COLUMN_QUANTITY = "quantity"
        private const val COLUMN_PRICE = "price"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_ITEM_NAME TEXT,
                $COLUMN_QUANTITY INTEGER,
                $COLUMN_PRICE DOUBLE
            );
        """.trimIndent()
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertItem(item: Item): Long{
        val values = ContentValues().apply {
            put(COLUMN_ITEM_NAME, item.name)
            put(COLUMN_QUANTITY, item.quantity)
            put(COLUMN_PRICE, item.price)
        }

        val db = writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    fun editItem(item: Item): Int{
        val values = ContentValues().apply {
            put(COLUMN_ITEM_NAME, item.name)
            put(COLUMN_QUANTITY, item.quantity)
            put(COLUMN_PRICE, item.price)
        }

        val db = writableDatabase
        return db.update(
            TABLE_NAME,
            values,
            "WHERE $COLUMN_ID=${item.id}",
            null
        )
    }

    // TODO: Implement to get a list with all items in the DB
    fun listItems(): ArrayList<Item>{
        val itemList = ArrayList<Item>();

        return itemList;
    }
}