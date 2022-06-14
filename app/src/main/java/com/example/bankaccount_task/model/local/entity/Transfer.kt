package com.example.bankaccount_task.model.local.entity

data class Transfer(
    val id: String,
    val senderAccountID: String,
    val senderAccountName: String,
    val ReceiverAccountID: String,
    val ReceiverAccountName: String,
    val TransferAmount: Double

    )
