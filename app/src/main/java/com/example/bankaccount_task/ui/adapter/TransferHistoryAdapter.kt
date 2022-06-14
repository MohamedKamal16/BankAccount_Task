package com.example.bankaccount_task.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bankaccount_task.databinding.ItemHistoryTransferBinding
import com.example.bankaccount_task.model.local.entity.Transfer


class TransferHistoryAdapter(): RecyclerView.Adapter<TransferHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemHistoryTransferBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryTransferBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transfer=differ.currentList[position]
        with(holder.binding){
            tvSenderName.text=transfer.senderAccountName
            tvSenderID.text=transfer.senderAccountID
            tvRecieverName.text=transfer.ReceiverAccountName
            tvRecieverID.text=transfer.ReceiverAccountID
            tvTransferMoney.text=transfer.TransferAmount.toString()
        }
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    //try AsyncListDiffer
    private val differCallBack = object : DiffUtil.ItemCallback<Transfer>(){

        override fun areItemsTheSame(oldItem: Transfer,newItem: Transfer):Boolean{
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Transfer, newItem: Transfer): Boolean {
            return oldItem == newItem
        }
    }

    //Async list differ take two list and compare them to change the difference only it run on background
    val differ= AsyncListDiffer(this,differCallBack)

}