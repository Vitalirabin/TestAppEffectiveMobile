package com.example.testappeffectivemobile.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.testappeffectivemobile.BaseFragment
import com.example.testappeffectivemobile.R
import com.example.testappeffectivemobile.databinding.FragmentMainBinding

class MainFragment() : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_main

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = MainViewModel()
        val spinner = binding.locateSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.location,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        return binding.root
    }
}