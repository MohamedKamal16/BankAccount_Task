package com.example.bankaccount_task.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankaccount_task.R
import com.example.bankaccount_task.databinding.TransferHistoryFragmentFragmentBinding
import com.example.bankaccount_task.ui.adapter.TransferHistoryAdapter
import com.example.bankaccount_task.ui.viewModel.TransferHistoryFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransferHistoryFragment : Fragment() {

    private var _binding: TransferHistoryFragmentFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var transferAdapter: TransferHistoryAdapter

    private val viewModel:TransferHistoryFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TransferHistoryFragmentFragmentBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerview()

        with(binding){
            tvPageName.text=getString(R.string.tr_history)
            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }


        viewModel.getTransfers() .observe(viewLifecycleOwner, Observer {
            transferAdapter.differ.submitList(it)

        })

    }

    private fun setupRecyclerview() = binding.recHistory.apply {
        transferAdapter= TransferHistoryAdapter()
        adapter = transferAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }



}