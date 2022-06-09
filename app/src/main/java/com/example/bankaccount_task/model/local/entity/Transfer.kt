package com.example.bankaccount_task.model.local.entity

class Transfer(
    val id: String,
    val senderAccountID: String,
    val senderAccountName: String,
    val ReceiverAccountID: String,
    val ReceiverAccountName: String,
    val TransferAmount: Int

    ) {
    override fun toString(): String {
        return "id: $id, senderAccountID: $senderAccountID, senderAccountName: $senderAccountName,TransferAmount: $TransferAmount,ReceiverAccountID: $ReceiverAccountID,ReceiverAccountName: $ReceiverAccountName"
    }
}