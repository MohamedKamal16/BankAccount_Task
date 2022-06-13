package com.example.bankaccount_task.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bankaccount_task.R
import com.example.bankaccount_task.databinding.TransferFragmentBinding
import com.example.bankaccount_task.model.local.entity.UserAccount
import com.example.bankaccount_task.ui.viewModel.TransferViewModel
import com.example.bankaccount_task.utility.CheckUserData.checkReceiverName
import com.example.bankaccount_task.utility.CheckUserData.checkSenderBalance
import com.example.bankaccount_task.utility.CheckUserData.getReceiverAccountId
import com.example.bankaccount_task.utility.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransferFragment : Fragment() {

    private lateinit var binding: TransferFragmentBinding

    private lateinit var senderAccountId: String
    private lateinit var senderName: String
    private var senderBalance: Int = 0

    private var receiverUser: UserAccount? = null
    private lateinit var receiverAccountId: String
    private lateinit var receiverName: String
    private var receiverBalance: Int? = 0

    private var users = mutableListOf<UserAccount>()
    var usersID = mutableListOf<String>()

    private val viewModel: TransferViewModel by viewModels()

   lateinit var alertDialog:AlertDialog


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
            tvBalance.text = senderBalance.toString()
            btnConfirm.setOnClickListener {
                if (checkData())
                    transferDialog()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        spinner()
    }

    private fun confirmTransfer() {
        transferMoney(
            senderName, senderAccountId, senderBalance,
            receiverName, receiverAccountId, receiverBalance,
            binding.etMoney.text.toString().toDouble()
        )
        successDialog()
    }


    private fun transferMoney(
        senderName: String,
        senderAccountId: String,
        senderBalance: Int,
        receiverName: String,
        receiverAccountId: String,
        receiverBalance: Int?,
        moneyTransfer: Double
    ) {
        val senderNewBalance = senderBalance - moneyTransfer

        val receiverNewBalance = receiverBalance!! + moneyTransfer

        viewModel.insertTransfer(
            senderAccountId,
            senderName,
            receiverAccountId,
            receiverName,
            moneyTransfer
        )
        Log.w(
            "Haa",
            "result $senderAccountId , $senderName , $receiverAccountId , $receiverName , $moneyTransfer "
        )
        viewModel.updateBalance(senderAccountId, senderNewBalance.toInt())
        viewModel.updateBalance(receiverAccountId, receiverNewBalance.toInt())
    }

    private fun spinner() {

        val usersName = listOf(
            " ",
            "Mohamed",
            "Adnan",
            "Bahaa",
            "Ali",
            "Ahmed",
            "Hossam",
            "Fady",
            "Moataz",
            "Marwan",
            "Ibrahim"
        )

        with(binding) {
            val adapter = ArrayAdapter(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, usersName
            )

            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    var user = adapterView?.getItemAtPosition(position).toString()
                    etName.setText(user)
                    etAccount.setText(usersID[position], TextView.BufferType.EDITABLE)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    private fun checkData(): Boolean {
        receiverAccountId = getReceiverAccountId(users, binding.etAccount.text.toString())
        receiverBalance = returnReceiverBalanceByAccountId(receiverAccountId)


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

                    receiverUser = returnUserByAccountId(receiverAccountId)
                    receiverName = receiverUser?.userName ?: ""

                    if (checkReceiverName(etName, receiverName) && etMoney.text.isNotEmpty()) {
                        return checkSenderBalance(requireContext(), etMoney, senderBalance)
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

    private fun returnUserByAccountId(Id: String): UserAccount? {
        return viewModel.getUserByAccountId(Id)
    }

    private fun returnReceiverBalanceByAccountId(Id: String): Int? {
        return viewModel.getUserByAccountId(Id)?.currentBalance
    }

    private fun getAllUsers() {
        viewModel.getUsers().observe(viewLifecycleOwner) {
            users = it.toMutableList()
            usersID.add(" ")
            for (userN in users) {
                usersID.add(userN.userAccountId)
            }
        }
    }

    private fun retrieveUserData() {
        senderAccountId = arguments?.getString(Constant.ID).toString()
        senderName = arguments?.getString(Constant.NAME).toString()
        senderBalance = arguments?.getInt(Constant.BALANCE) ?: 0
    }

    private fun transferDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.dialogTitle))
        builder.setMessage(getString(R.string.dialogMessage) + binding.etName.text.toString() + "?")
        builder.setNegativeButton(getString(R.string.no)) { _, _ ->
            Toast.makeText(requireContext(), "No", Toast.LENGTH_SHORT).show()
        }
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            confirmTransfer()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundColor(Color.BLUE)
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.WHITE)
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setBackgroundColor(Color.BLUE)
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.WHITE)
    }

    private fun successDialog() {
        val builder = AlertDialog.Builder(requireContext())

        val layoutView = layoutInflater.inflate(R.layout.transfer_dialog, null)
        val btnOk=layoutView.findViewById<Button>(R.id.btnSuccess)
        builder.setView(layoutView)
        alertDialog=builder.create()
        alertDialog.show()

        btnOk.setOnClickListener {
            alertDialog.dismiss()
            findNavController().navigate(R.id.action_transferFragment_to_userFragment)
        }
    }

}