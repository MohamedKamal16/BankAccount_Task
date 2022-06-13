package com.example.bankaccount_task.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.bankaccount_task.model.local.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransferHistoryFragmentViewModel @Inject constructor(private val repository: Repo) : ViewModel() {
    fun getTransfers()=repository.getTransfers()
}
