package com.example.bankaccount_task.model.local.db

import android.provider.BaseColumns


class BankDatabaseContract private constructor() // make non-creatable
{

    object UserAccountEntry : BaseColumns {
         const val TABLE_NAME = "user_info"
        const val COLUMN_ID = BaseColumns._ID
        const val COLUMN_Name = "user_name"
         const val COLUMN_Email = "user_email"
        const val COLUMN_Account_ID = "user_id"
        const val COLUMN_Balance = "user_balance"

        //Constant to CREATE TABLE course_info (course_id, course_title)
        const val SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_Name + " TEXT UNIQUE NOT NULL, " +
                COLUMN_Email + " TEXT UNIQUE NOT NULL, " +
                COLUMN_Account_ID + " TEXT NOT NULL, " +
                COLUMN_Balance + " TEXT NOT NULL)"

        const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
       // const val SQL_Alter_TABLE = "ALTER TABLE IF EXISTS $TABLE_NAME" //IN release version

    }

    object TransferEntry : BaseColumns {
         const val TABLE_NAME = "transfer_info"
        const val COLUMN_ID = BaseColumns._ID
        const val COLUMN_SENDER_ID = "SENDER_ID"
        const val COLUMN_SENDER_NAME = "SENDER_NAME"
        const val COLUMN_RECEIVER_ID = "RECEIVER_ID"
        const val COLUMN_RECEIVER_NAME = "RECEIVER_NAME"
         const val COLUMN_TRANSFER_AMOUNT = "TRANSFER_AMOUNT"

        const val SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_SENDER_NAME + " TEXT NOT NULL, " +
                COLUMN_SENDER_ID + " TEXT NOT NULL, " +
                COLUMN_RECEIVER_NAME + " TEXT NOT NULL, " +
                COLUMN_RECEIVER_ID + " TEXT NOT NULL, " +
                COLUMN_TRANSFER_AMOUNT + " TEXT NOT NULL)"


        const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS ${UserAccountEntry.TABLE_NAME}"
     //   const val SQL_Alter_TABLE = "ALTER TABLE IF EXISTS ${UserAccountEntry.TABLE_NAME}"
    }
}