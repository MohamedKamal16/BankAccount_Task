package com.example.bankaccount_task.model.local.entity

class UserAccount (
    val id:String,
    val userName:String,
    val userEmail:String,
    val userAccountId: String,
    val currentBalance:Int
        ){
    override fun toString(): String {
        return "id: $id, name: $userName, email: $userEmail, AccountId: $userAccountId, Balance: $currentBalance"
    }
}
