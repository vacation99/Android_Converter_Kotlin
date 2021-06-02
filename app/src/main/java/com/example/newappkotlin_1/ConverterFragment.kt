package com.example.newappkotlin_1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.example.newappkotlin_1.databinding.FragmentConverterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConverterFragment : Fragment(R.layout.fragment_converter) {

    private val viewModel: ConverterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentConverterBinding.bind(view)

//        val viewModel = ViewModelProvider(this).get(ConverterViewModel::class.java)

        binding.button.setOnClickListener {
            if (binding.etInputNum.text.isNullOrBlank() || binding.etInputNum.text.toString() == ".") {
                Toast.makeText(requireContext(), "Заполните поле только цифрами", Toast.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.convert(
                    binding.spinnerFrom.selectedItem.toString(),
                    binding.spinnerTo.selectedItem.toString(),
                    binding.etInputNum.text.toString()
                )

                setFragmentResult("updateKey", bundleOf("update" to 1))
            }
        }

        viewModel.getLiveDataResult.observe(viewLifecycleOwner) { result ->
            binding.tvResult.text = "${getString(R.string.tv_result)} ${binding.etInputNum.text} ${binding.spinnerFrom.selectedItem} = $result ${binding.spinnerTo.selectedItem}"

            viewModel.insertInDb(binding.tvResult.text.toString())
        }

        viewModel.getLiveDataErrorMessage.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { content ->
                Toast.makeText(requireContext(), content, Toast.LENGTH_SHORT).show()
            }
        }
    }
}