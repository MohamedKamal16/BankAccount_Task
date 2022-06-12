package com.example.bankaccount_task.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.bankaccount_task.databinding.TransferFragmentBinding
import com.example.bankaccount_task.model.local.entity.UserAccount
import com.example.bankaccount_task.ui.viewModel.TransferViewModel
import com.example.bankaccount_task.utility.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransferFragment : Fragment() {

    private lateinit var binding: TransferFragmentBinding

    lateinit var senderAccountId: String
    lateinit var senderName: String
    var senderBalance: Int = 0

    private val viewModel: TransferViewModel by viewModels()

    var receiverUser: UserAccount? = null
    lateinit var receiverAccountId: String
    lateinit var receiverName: String
    var receiverBalance: Int = 0

    var users = listOf<UserAccount>()

    companion object {
        fun newInstance() = TransferFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TransferFragmentBinding.inflate(inflater, container, false)
        retrieveUserData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAllUsers()

        with(binding) {
            tvName.text = senderName
            tvAccount.text = senderAccountId
            btnConfirm.setOnClickListener {
                confirmTransfer()

            }
        }

    }

    private fun retrieveUserData() {
        senderAccountId = arguments?.getString(Constant.ID).toString()
        senderName = arguments?.getString(Constant.NAME).toString()
        senderBalance = arguments?.getInt(Constant.BALANCE) ?: 0
    }

    private fun confirmTransfer() {
        if (checkData()) {
            transferMoney(
                senderName,
                senderAccountId,
                senderBalance,
                receiverName,
                receiverAccountId,
                receiverBalance,
                binding.etMoney.text.toString().toDouble()
            )
            Log.w("DATAA", "COMPARE $receiverAccountId ")
            Log.w("DATAA", "COMPARE $receiverName ")
            Log.w("DATAA", "COMPARE $senderAccountId ")
            Log.w("DATAA", "COMPARE $senderName ")
            Log.w("DATAA", "COMPARE $senderBalance ")
        }

    }

    private fun transferMoney(
        senderName: String,
        senderAccountId: String,
        senderBalance: Int,
        receiverName: String,
        receiverAccountId: String,
        receiverBalance: Int,
        moneyTransfer: Double
    ) {
      //TOdo 1.Dialog to confirm transfer 2.confirm Transfer 3. update sql data
    }

    private fun checkData(): Boolean {
        receiverAccountId = getReceiverAccount()

        with(binding) {
            if (receiverAccountId == "") {
                etAccount.requestFocus()
                etAccount.error = "Account Not Exist"
                return false
            } else {
                if (receiverAccountId == senderAccountId) {
                    Toast.makeText(
                        requireContext(),
                        "Cant Transfer Money To Sender Account",
                        Toast.LENGTH_LONG
                    ).show()
                    return false
                } else {
                    receiverUser = returnUserByAccount(receiverAccountId)
                    receiverName = receiverUser?.userName ?: ""
                    if (checkReceiverName(receiverName) && etMoney.text.isNotEmpty()) {
                        return checkSenderBalance()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "PLease Enter Money you want Transfer",
                            Toast.LENGTH_LONG
                        ).show()
                        return false
                    }
                }
            }
        }

    }

    private fun checkSenderBalance(): Boolean {
        return if (senderBalance >= binding.etMoney.text.toString().toDouble()) {
            true
        } else {
            Toast.makeText(
                requireContext(),
                "Your Balance not Cover this transfer",
                Toast.LENGTH_LONG
            ).show()
            false
        }
    }
    /*  543453546 adnan */
    private fun checkReceiverName(name: String): Boolean {
        with(binding) {
            if (etName.text.toString().equals(name, true)) {
                return true
            } else {
                etName.requestFocus()
                etName.error = "Required"
                return false
            }
        }
    }

    private fun getReceiverAccount(): String {
        with(binding) {
            for (user in users)
                if (etAccount.text.toString() == user.userAccountId) {
                    return user.userAccountId
                }
        }
        return ""
    }

    private fun returnUserByAccount(Id: String): UserAccount? {
        return viewModel.getUserNameByAccountId(Id)
    }

    private fun getAllUsers() {
        viewModel.getUsers().observe(viewLifecycleOwner, Observer {
            users = it
        })
    }

}