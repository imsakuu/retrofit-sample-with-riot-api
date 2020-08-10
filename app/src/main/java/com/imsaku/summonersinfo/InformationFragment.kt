package com.imsaku.summonersinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.imsaku.summonersinfo.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {

    private val viewModel: InformationViewModel by lazy {
        ViewModelProvider(this, InformationViewModel.Factory())
            .get(InformationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentInformationBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_information,
            container,
            false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.buttonSearch.setOnClickListener {
            viewModel.onSearch(binding.editQuery.text.toString())
        }

        return binding.root
    }
}