package com.example.bankaccount_task.model.local.db

import android.content.ContentValues
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

            insertUsers(db)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL(UserAccountEntry.SQL_DROP_TABLE)
            db.execSQL(TransferEntry.SQL_DROP_TABLE)
            onCreate(db)
        }


    fun insertUsers(db: SQLiteDatabase ) {
        insertStaticUsers(db,"Mohamed", "Mohamed@gmail.com", "55875937", 120000)
        insertStaticUsers(db,"Adnan", "Adnan@gmail.com", "543453546", 220000)
        insertStaticUsers(db,"Bahaa", "Bahaa@gmail.com", "235453452", 320000)
        insertStaticUsers(db,"Ali", "Ali@gmail.com", "345654345", 420000)
        insertStaticUsers(db,"Ahmed", "Ahmed@gmail.com", "786546546", 520000)
        insertStaticUsers(db,"Hossam", "Hossam@gmail.com", "45132563", 620000)
        insertStaticUsers(db,"Fady", "Fady@gmail.com", "345876543", 720000)
        insertStaticUsers(db,"Moataz", "Moataz@gmail.com", "1253435876", 820000)
        insertStaticUsers(db,"Marwan", "Marwan@gmail.com", "34567675456", 920000)
        insertStaticUsers(db,"Ibrahim", "Ibrahim@gmail.com", "3462856586", 150000)
    }


    private fun insertStaticUsers(
        db: SQLiteDatabase,
        userName: String,
        userEmail: String,
        userAccountId: String,
        currentBalance: Int
    ) {
        val values = ContentValues()
        values.put(UserAccountEntry.COLUMN_Account_ID, userAccountId)
        values.put(UserAccountEntry.COLUMN_Email, userEmail)
        values.put(UserAccountEntry.COLUMN_Name, userName)
        values.put(UserAccountEntry.COLUMN_Balance, currentBalance)
        db.insert(UserAccountEntry.TABLE_NAME,null,values)
    }
}