package com.example.bankaccount_task.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankaccount_task.databinding.FragmentUsersBinding
import com.example.bankaccount_task.model.local.db.BankOpenHelper
import com.example.bankaccount_task.model.local.db.DatabaseDataWorker
import com.example.bankaccount_task.ui.adapter.UsersAdapter
import com.example.bankaccount_task.ui.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    lateinit var userAdapter: UsersAdapter


    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUsers().observe(viewLifecycleOwner, Observer {
            userAdapter = UsersAdapter(it)
            setupRecyclerview()
        })

    }

    private fun setupRecyclerview() = binding.userRec.apply {
        adapter = userAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}