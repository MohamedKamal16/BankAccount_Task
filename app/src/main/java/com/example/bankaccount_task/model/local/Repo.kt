package com.example.bankaccount_task.model.local

import com.example.bankaccount_task.model.local.db.DatabaseDataWorker
import com.example.bankaccount_task.model.local.entity.UserAccount
import javax.inject.Inject

class Repo @Inject constructor(private val worker: DatabaseDataWorker) {

  fun insertTransfer(
        senderAccountID: String,
        senderAccountName: String,
        receiverAccountID: String,
        receiverAccountName: String,
        transferAmount: Double
    ) = worker.insertTransfer(
        senderAccountID,
        senderAccountName,
        receiverAccountID,
        receiverAccountName,
        transferAmount
    )
    fun getAllUsers()=worker.getAllUsers()
    fun getTransfers()=worker.getTransferList()
    fun getUserByAccountId(accountId:String)=worker.getUserByAccountId(accountId)
    fun updateBalance(userAccountId: String, amount: Int)=worker.updateUserBalance(userAccountId, amount)
    fun deleteTransferHistory(id: String)= worker.deleteTransferHistory(id)
}