package com.example.newappkotlin_1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newappkotlin_1.adapter.RecyclerViewAdapter
import com.example.newappkotlin_1.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history) {

    private val viewModel: HistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHistoryBinding.bind(view)

        val recyclerViewAdapter = RecyclerViewAdapter()

        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = recyclerViewAdapter
            }
        }

        viewModel.getLiveDataDB.observe(viewLifecycleOwner) {
            recyclerViewAdapter.setItems(it)
        }

        setFragmentResultListener("updateKey") { _, bundle ->
            val result = bundle.getInt("update")

            if (result == 1) {
                viewModel.updateRecyclerView()
            }
        }
    }
}