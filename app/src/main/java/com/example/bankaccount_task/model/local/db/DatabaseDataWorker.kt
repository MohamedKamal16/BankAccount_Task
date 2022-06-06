package com.example.bankaccount_task.model.local.db

import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues

/*
 ADD simple to db fill table with some data
 */
class DatabaseDataWorker(private val mDb: SQLiteDatabase) {
    fun insertUsers() {
        insertUser("Mohamed", "Mohamed@gmail.com","55875937",120000)
        insertUser("Adnan", "Adnan@gmail.com","543453546",220000)
        insertUser("Bahaa", "Bahaa@gmail.com","235453452",320000)
        insertUser("Ali", "Ali@gmail.com","345654345",420000)
        insertUser("Ahmed", "Ahmed@gmail.com","786546546",520000)
        insertUser("Hossam", "Hossam@gmail.com","45132563",620000)
        insertUser("Fady", "Fady@gmail.com","345876543",720000)
        insertUser("Moataz", "Moataz@gmail.com","1253435876",820000)
        insertUser("Marwan", "Marwan@gmail.com","34567675456",920000)
        insertUser("Ibrahim", "Ibrahim@gmail.com","3462856586",150000)
    }


    private fun insertUser(userName:String, userEmail:String,userAccountId: String,currentBalance:Int) {
        val values = ContentValues()
        values.put(BankDatabaseContract.UserAccountEntry.COLUMN_Account_ID, userAccountId)
        values.put(BankDatabaseContract.UserAccountEntry.COLUMN_Email, userEmail)
        values.put(BankDatabaseContract.UserAccountEntry.COLUMN_Name, userName)
        values.put(BankDatabaseContract.UserAccountEntry.COLUMN_Balance, currentBalance)
    }

    private fun insertTransfer(senderAccountID: String,
                               senderAccountName:String,
                               receiverAccountID:String,
                               receiverAccountName:String,
                               transferAmount: String) {
        val values = ContentValues()
        values.put(BankDatabaseContract.TransferEntry.COLUMN_SENDER_NAME,senderAccountName)
        values.put(BankDatabaseContract.TransferEntry.COLUMN_SENDER_ID,senderAccountID)
        values.put(BankDatabaseContract.TransferEntry.COLUMN_RECEIVER_NAME,receiverAccountName)
        values.put(BankDatabaseContract.TransferEntry.COLUMN_RECEIVER_ID,receiverAccountID)
        values.put(BankDatabaseContract.TransferEntry.COLUMN_TRANSFER_AMOUNT,transferAmount)

    }
}