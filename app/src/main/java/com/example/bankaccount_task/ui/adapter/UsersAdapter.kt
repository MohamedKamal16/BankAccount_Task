package com.example.bankaccount_task.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bankaccount_task.R
import com.example.bankaccount_task.databinding.ItemUserBinding
import com.example.bankaccount_task.model.local.entity.UserAccount
import com.example.bankaccount_task.utility.Constant.ID
import com.example.bankaccount_task.utility.Constant.NAME
import com.example.bankaccount_task.utility.Constant.EMAIL
import com.example.bankaccount_task.utility.Constant.BALANCE

class UsersAdapter(private val users:List<UserAccount>): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.binding){
            tvName.text=users[position].userName
            tvEmail.text=users[position].userEmail
            tvAccount.text=users[position].userAccountId
            tvBalance.text=users[position].currentBalance.toString()
        }

        holder.itemView.setOnClickListener {
           val bundle=Bundle().apply {
               putString(ID,users[position].userAccountId)
               putString(NAME,users[position].userName)
               putString(EMAIL,users[position].userEmail)
               putInt(BALANCE,users[position].currentBalance)
           }
            holder.itemView.findNavController().navigate(R.id.accountFragment,bundle)
        }

    }

    override fun getItemCount(): Int {
        return users.size
    }

}