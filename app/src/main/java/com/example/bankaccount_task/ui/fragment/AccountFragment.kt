package com.example.bankaccount_task.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bankaccount_task.R
import com.example.bankaccount_task.databinding.FragmentAccountsBinding
import com.example.bankaccount_task.utility.Constant.BALANCE
import com.example.bankaccount_task.utility.Constant.EMAIL
import com.example.bankaccount_task.utility.Constant.ID
import com.example.bankaccount_task.utility.Constant.NAME

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountsBinding? = null
    private val binding get() = _binding!!

    lateinit var accountId:String
    lateinit var name:String
    lateinit var email:String
     var balance:Int =0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountsBinding.inflate(inflater, container, false)
        retrieveUserData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            tvName.text=name
            tvAccount.text=accountId
            tvBalance.text=balance.toString()
            tvEmail.text=email

            fab.setOnClickListener {
                moveToTransferWindow()
            }
        }

    }
  private fun retrieveUserData(){
     accountId = arguments?.getString(ID).toString()
     name = arguments?.getString(NAME).toString()
     email = arguments?.getString(EMAIL).toString()
     balance = arguments?.getInt(BALANCE) ?: 0
  }

    private fun moveToTransferWindow(){
        val bundle=Bundle().apply {
            putString(ID,accountId)
            putString(NAME,name)
            putInt(BALANCE,balance)
        }
        findNavController().navigate(R.id.transferFragment,bundle)
  }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}