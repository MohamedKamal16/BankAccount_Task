package com.example.bankaccount_task.model.local.entity

class Transfer (
    var senderAccountID: String,
    var senderAccountName:String,
    var TransferAmount: String,
    var ReceiverAccountID:String,
    var ReceiverAccountName:String
)
   /* constructor(
        senderAccountID: UserAccount,
        senderAccountName: String,
        TransferAmount: String,
        ReceiverAccountID: String,
        ReceiverAccountName: String
    ) {
        this.senderAccountID = senderAccountID
        this.senderAccountName = senderAccountName
        this.TransferAmount = TransferAmount
        this.ReceiverAccountID = ReceiverAccountID
        this.ReceiverAccountName = ReceiverAccountName
    }

    constructor(
        Id: Int,
        senderAccountID: UserAccount,
        senderAccountName: String,
        TransferAmount: String,
        ReceiverAccountID: String,
        ReceiverAccountName: String
    ) {
        this.Id = Id
        this.senderAccountID = senderAccountID
        this.senderAccountName = senderAccountName
        this.TransferAmount = TransferAmount
        this.ReceiverAccountID = ReceiverAccountID
        this.ReceiverAccountName = ReceiverAccountName
    }
}*/