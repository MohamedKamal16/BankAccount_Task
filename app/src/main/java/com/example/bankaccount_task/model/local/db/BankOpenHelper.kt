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
           DatabaseDataWorker.insertUsers()

        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL(UserAccountEntry.SQL_DROP_TABLE)
            db.execSQL(TransferEntry.SQL_DROP_TABLE)
            onCreate(db)
        }


}