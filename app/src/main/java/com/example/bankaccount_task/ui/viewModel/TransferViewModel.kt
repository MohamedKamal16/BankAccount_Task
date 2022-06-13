package com.example.bankaccount_task.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankaccount_task.model.local.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(private val repository: Repo) : ViewModel() {

    fun getUsers() = repository.getAllUsers()

    fun getUserByAccountId(accountId: String) = repository.getUserByAccountId(accountId)

    fun insertTransfer(
        senderAccountID: String,
        senderAccountName: String,
        receiverAccountID: String,
        receiverAccountName: String,
        transferAmount: Double
    )=viewModelScope.launch {
        repository.insertTransfer(senderAccountID, senderAccountName, receiverAccountID, receiverAccountName, transferAmount)
    }

    fun updateBalance(userAccountId: String, amount: Int)=repository.updateBalance(userAccountId, amount)
}