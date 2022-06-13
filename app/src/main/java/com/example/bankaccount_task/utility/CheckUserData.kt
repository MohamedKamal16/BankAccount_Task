package com.example.bankaccount_task.utility

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import com.example.bankaccount_task.model.local.entity.UserAccount

object CheckUserData {

     fun checkSenderBalance(context: Context,etMoney:EditText,senderBalance:Int): Boolean {
        return if (senderBalance >= etMoney.text.toString().toDouble()) {
            true
        } else {
            Toast.makeText(
                context,
                "Your Balance not Cover this transfer",
                Toast.LENGTH_LONG
            ).show()
            false
        }
    }

     fun checkReceiverName(editText: EditText,name: String): Boolean {
        return if (editText.text.toString().equals(name, true)) {
            true
        } else {
            editText.requestFocus()
            editText.error = "Required"
            false
        }

    }
     fun getReceiverAccountId(Users:MutableList<UserAccount>,editText:String): String {
            for (user in Users)
                if (editText == user.userAccountId) {
                    return user.userAccountId
                }
        return ""
    }
}