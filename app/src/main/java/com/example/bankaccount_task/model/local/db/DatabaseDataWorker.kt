package com.example.bankaccount_task.model.local.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.bankaccount_task.model.local.db.BankDatabaseContract.TransferEntry
import com.example.bankaccount_task.model.local.db.BankDatabaseContract.UserAccountEntry
import com.example.bankaccount_task.model.local.entity.Transfer
import com.example.bankaccount_task.model.local.entity.UserAccount

/*
 ADD simple to db fill table with some data
 */
object DatabaseDataWorker {

    fun insertUsers() {
        insertUser("Mohamed", "Mohamed@gmail.com", "55875937", 120000)
        insertUser("Adnan", "Adnan@gmail.com", "543453546", 220000)
        insertUser("Bahaa", "Bahaa@gmail.com", "235453452", 320000)
        insertUser("Ali", "Ali@gmail.com", "345654345", 420000)
        insertUser("Ahmed", "Ahmed@gmail.com", "786546546", 520000)
        insertUser("Hossam", "Hossam@gmail.com", "45132563", 620000)
        insertUser("Fady", "Fady@gmail.com", "345876543", 720000)
        insertUser("Moataz", "Moataz@gmail.com", "1253435876", 820000)
        insertUser("Marwan", "Marwan@gmail.com", "34567675456", 920000)
        insertUser("Ibrahim", "Ibrahim@gmail.com", "3462856586", 150000)
    }


    private fun insertUser(

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
    }

    fun insertTransfer(
        senderAccountID: String,
        senderAccountName: String,
        receiverAccountID: String,
        receiverAccountName: String,
        transferAmount: Int
    ) {
        val values = ContentValues()
        values.put(TransferEntry.COLUMN_SENDER_NAME, senderAccountName)
        values.put(TransferEntry.COLUMN_SENDER_ID, senderAccountID)
        values.put(TransferEntry.COLUMN_RECEIVER_NAME, receiverAccountName)
        values.put(TransferEntry.COLUMN_RECEIVER_ID, receiverAccountID)
        values.put(TransferEntry.COLUMN_TRANSFER_AMOUNT, transferAmount)
    }

    fun getAllUsers(databaseHelper: BankOpenHelper): ArrayList<UserAccount> {

        val users = arrayListOf<UserAccount>()//Empty array of users
        val db = databaseHelper.readableDatabase //open db for read information

        val columns = arrayOf(
            UserAccountEntry.COLUMN_ID,
            UserAccountEntry.COLUMN_Name,
            UserAccountEntry.COLUMN_Email,
            UserAccountEntry.COLUMN_Account_ID,
            UserAccountEntry.COLUMN_Balance
        ) // table columns

        val cursor = db.query(
            UserAccountEntry.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        ) //create cursor

        //get each column index
        val idPos = cursor.getColumnIndex(UserAccountEntry.COLUMN_ID)
        val namePos = cursor.getColumnIndex(UserAccountEntry.COLUMN_Name)
        val emailPos = cursor.getColumnIndex(UserAccountEntry.COLUMN_Email)
        val accountIdPos = cursor.getColumnIndex(UserAccountEntry.COLUMN_Account_ID)
        val balancePos = cursor.getColumnIndex(UserAccountEntry.COLUMN_Balance)

        while (cursor.moveToNext()) { //loop in db data and add it on array
            val id = cursor.getString(idPos)
            val name = cursor.getString(namePos)
            val email = cursor.getString(emailPos)
            val accountIdPos = cursor.getString(accountIdPos)
            val balancePos = cursor.getInt(balancePos)
            users.add(UserAccount(id, name, email, accountIdPos, balancePos))
        }
        cursor.close() // close cursor
        return users
    }

    fun getTransferList(databaseHelper: BankOpenHelper): ArrayList<Transfer> {

        val transfers = arrayListOf<Transfer>()
        val db = databaseHelper.readableDatabase

        val columns = arrayOf(
            TransferEntry.COLUMN_ID,
            TransferEntry.COLUMN_SENDER_ID,
            TransferEntry.COLUMN_SENDER_NAME,
            TransferEntry.COLUMN_RECEIVER_ID,
            TransferEntry.COLUMN_RECEIVER_NAME,
            TransferEntry.COLUMN_TRANSFER_AMOUNT
        )

        val cursor = db.query(
            TransferEntry.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        val idPos = cursor.getColumnIndex(TransferEntry.COLUMN_ID)
        val senderIdPos = cursor.getColumnIndex(TransferEntry.COLUMN_SENDER_ID)
        val senderNamePos = cursor.getColumnIndex(TransferEntry.COLUMN_SENDER_NAME)
        val receiverIdPos = cursor.getColumnIndex(TransferEntry.COLUMN_RECEIVER_ID)
        val receiverPos = cursor.getColumnIndex(TransferEntry.COLUMN_RECEIVER_NAME)
        val amountPos = cursor.getColumnIndex(TransferEntry.COLUMN_TRANSFER_AMOUNT)

        while (cursor.moveToNext()) {
            val id = cursor.getString(idPos)
            val senderId = cursor.getString(senderIdPos)
            val senderName = cursor.getString(senderNamePos)
            val receiverId = cursor.getString(receiverIdPos)
            val receiverName = cursor.getString(receiverPos)
            val amount = cursor.getInt(amountPos)

            transfers.add(Transfer(id, senderId, senderName, receiverId, receiverName, amount))
        }
        cursor.close()
        return transfers
    }

    fun updateUserBalance(databaseHelper: BankOpenHelper, userAccountId: String, amount: Int) {

        val db: SQLiteDatabase = databaseHelper.writableDatabase
        val values = ContentValues()
        values.put(UserAccountEntry.COLUMN_Balance, amount)

        db.update(
            UserAccountEntry.TABLE_NAME,
            values,
            "userAccountId = ?",
            arrayOf(userAccountId)
        )//TODO Maybe cause error
        db.close()
    }


}