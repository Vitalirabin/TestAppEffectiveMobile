package com.example.testappeffectivemobile.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testappeffectivemobile.BaseFragment
import com.example.testappeffectivemobile.ItemOnClickListener
import com.example.testappeffectivemobile.R
import com.example.testappeffectivemobile.databinding.FragmentMainBinding
import com.example.testappeffectivemobile.main.category.CategoryAdapter
import com.example.testappeffectivemobile.main.hot.HotSalesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment() : BaseFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var categoryAdapter: CategoryAdapter? = null
    private var hotSalesAdapter: HotSalesAdapter? = null
    private val mainViewModel: MainViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val categoryList = mainViewModel.getCategory(requireContext(), "Phone")
        super.onViewCreated(view, savedInstanceState)
        categoryAdapter = CategoryAdapter(object : ItemOnClickListener {
            override fun onClick(name: String) {
                clickCategory(name)
            }
        })
        binding.selectCategoryRecycleView.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.selectCategoryRecycleView.adapter = categoryAdapter
        categoryAdapter!!.submitList(categoryList)

        mainViewModel.hotSalesList.observe(viewLifecycleOwner, Observer {
            hotSalesAdapter?.submitList(it)
        })
        if (hotSalesAdapter == null) {
            hotSalesAdapter = HotSalesAdapter(object : ItemOnClickListener {
                override fun onClick(name: String) {
                    clickCategory(name)
                }
            })
        }
        binding.hotSellerRecycleView.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.hotSellerRecycleView.adapter = hotSalesAdapter
        mainViewModel.updateHotSalesList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        categoryAdapter = null
    }

    fun clickCategory(name: String) {
        Log.d("MainFragment", name)
        mainViewModel.updateCategory(name)
        categoryAdapter?.notifyDataSetChanged()
    }
}