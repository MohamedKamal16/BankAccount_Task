package com.example.bankaccount_task.model.local.db

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.MutableLiveData
import com.example.bankaccount_task.model.local.db.BankDatabaseContract.TransferEntry
import com.example.bankaccount_task.model.local.db.BankDatabaseContract.UserAccountEntry
import com.example.bankaccount_task.model.local.entity.Transfer
import com.example.bankaccount_task.model.local.entity.UserAccount
import javax.inject.Inject

/*
 ADD simple to db fill table with some data
 */
class DatabaseDataWorker @Inject constructor(var databaseHelper: BankOpenHelper) {


   fun insertTransfer(
        senderAccountID: String,
        senderAccountName: String,
        receiverAccountID: String,
        receiverAccountName: String,
        transferAmount: Double
    ) {
        val db = databaseHelper.writableDatabase

        val values = ContentValues()
        values.put(TransferEntry.COLUMN_SENDER_NAME, senderAccountName)
        values.put(TransferEntry.COLUMN_SENDER_ID, senderAccountID)
        values.put(TransferEntry.COLUMN_RECEIVER_NAME, receiverAccountName)
        values.put(TransferEntry.COLUMN_RECEIVER_ID, receiverAccountID)
        values.put(TransferEntry.COLUMN_TRANSFER_AMOUNT, transferAmount)

        db.insert(TransferEntry.TABLE_NAME, null, values)
        db.close()
    }

    fun getAllUsers(): MutableLiveData<List<UserAccount>> {
        val users = mutableListOf<UserAccount>()    //Empty list of users

        val liveUserResult = MutableLiveData<List<UserAccount>>()//liveResult to post value

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
            val accountId = cursor.getString(accountIdPos)
            val balance = cursor.getInt(balancePos)
            users.add(UserAccount(id, name, email, accountId, balance))
        }
        cursor.close() // close cursor
        liveUserResult.postValue(users)
        return liveUserResult
    }

    fun getTransferList(): MutableLiveData<ArrayList<Transfer>> {
        val transfers = arrayListOf<Transfer>()
        val liveResult = MutableLiveData<ArrayList<Transfer>>()

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

       // cursor.moveToFirst()
        while (cursor.moveToNext()) {
            val id = cursor.getString(idPos)
            val senderId = cursor.getString(senderIdPos)
            val senderName = cursor.getString(senderNamePos)
            val receiverId = cursor.getString(receiverIdPos)
            val receiverName = cursor.getString(receiverPos)
            val amount = cursor.getDouble(amountPos)

            transfers.add(Transfer(id, senderId, senderName, receiverId, receiverName, amount))
        }
        cursor.close()
        liveResult.postValue(transfers)
        return liveResult
    }

    fun updateUserBalance(userAccountId: String, amount: Int) {

        val db: SQLiteDatabase = databaseHelper.writableDatabase
        val values = ContentValues()
        values.put(UserAccountEntry.COLUMN_Balance, amount)

        db.update(
            UserAccountEntry.TABLE_NAME,
            values,
            "user_id = ?",
            arrayOf(userAccountId)
        )
        db.close()
    }


    fun getUserByAccountId(receiverAccountId: String): UserAccount? {
        val db = databaseHelper.readableDatabase
         var  user: UserAccount? =null

      val columns = arrayOf(
            UserAccountEntry.COLUMN_ID,
            UserAccountEntry.COLUMN_Name,
            UserAccountEntry.COLUMN_Email,
            UserAccountEntry.COLUMN_Account_ID,
            UserAccountEntry.COLUMN_Balance
        )

        val selection = UserAccountEntry.COLUMN_Account_ID + " = ? "
        val selectionArgs = arrayOf(receiverAccountId)

        val cursor = db.query(
            UserAccountEntry.TABLE_NAME,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val idPos = cursor.getColumnIndex(UserAccountEntry.COLUMN_ID)
        val namePos = cursor.getColumnIndex(UserAccountEntry.COLUMN_Name)
        val emailPos = cursor.getColumnIndex(UserAccountEntry.COLUMN_Email)
        val balancePos = cursor.getColumnIndex(UserAccountEntry.COLUMN_Balance)

      if(cursor.moveToFirst()) {
          val id = cursor.getString(idPos)
          val name = cursor.getString(namePos)
          val email = cursor.getString(emailPos)
          val balance = cursor.getInt(balancePos)

          user = UserAccount(id, name, email, receiverAccountId, balance)
      }
        cursor.close()
        return user
    }
    fun deleteTransferHistory(id: String){
        val db=databaseHelper.writableDatabase
        db.delete(TransferEntry.TABLE_NAME,
            TransferEntry.COLUMN_ID +" = ? ",
            arrayOf(id) )
    }






}