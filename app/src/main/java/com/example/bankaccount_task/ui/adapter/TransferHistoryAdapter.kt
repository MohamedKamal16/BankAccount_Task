package com.example.bankaccount_task.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankaccount_task.databinding.ItemHistoryTransferBinding
import com.example.bankaccount_task.model.local.entity.Transfer


class TransferHistoryAdapter(private val transfer:List<Transfer>): RecyclerView.Adapter<TransferHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemHistoryTransferBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryTransferBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.binding){
            tvSenderName.text=transfer[position].senderAccountName
            tvSenderID.text=transfer[position].senderAccountID
            tvRecieverName.text=transfer[position].ReceiverAccountName
            tvRecieverID.text=transfer[position].ReceiverAccountID
            tvTransferMoney.text=transfer[position].TransferAmount.toString()
        }
    }

    override fun getItemCount(): Int {
        return transfer.size
    }

}