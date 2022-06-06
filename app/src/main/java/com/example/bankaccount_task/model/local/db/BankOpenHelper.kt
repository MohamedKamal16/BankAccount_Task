package com.example.bankaccount_task.model.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.bankaccount_task.model.local.db.BankDatabaseContract.TransferEntry
import com.example.bankaccount_task.model.local.db.BankDatabaseContract.UserAccountEntry


class BankOpenHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME="BankDataBase"
        private const val DATABASE_VERSION=1
    }
        override fun onCreate(db: SQLiteDatabase) {
            //create table
            db.execSQL(UserAccountEntry.SQL_CREATE_TABLE)
            db.execSQL(TransferEntry.SQL_CREATE_TABLE)
            //fill table with data with simple that we do
            val worker = DatabaseDataWorker(db)
            worker.insertUsers()

            db.execSQL(UserAccountEntry.SQL_CREATE_INDEX1)
            db.execSQL(TransferEntry.SQL_CREATE_INDEX1)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            if (oldVersion < 2) {
                db.execSQL(UserAccountEntry.SQL_CREATE_INDEX1)
                db.execSQL(TransferEntry.SQL_CREATE_INDEX1)
            }
        }


}