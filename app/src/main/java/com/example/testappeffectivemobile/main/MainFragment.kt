package com.example.testappeffectivemobile.main

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.testappeffectivemobile.BaseFragment
import com.example.testappeffectivemobile.BestSellerItemOnClickListener
import com.example.testappeffectivemobile.ItemOnClickListener
import com.example.testappeffectivemobile.R
import com.example.testappeffectivemobile.cart.BasketModel
import com.example.testappeffectivemobile.databinding.FragmentMainBinding
import com.example.testappeffectivemobile.main.best.BestSellerAdapter
import com.example.testappeffectivemobile.main.category.CategoryAdapter
import com.example.testappeffectivemobile.main.hot.HotSalesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment() : BaseFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var categoryAdapter: CategoryAdapter? = null
    private var hotSalesAdapter: HotSalesAdapter? = null
    private var bestSellerAdapter: BestSellerAdapter? = null
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
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.location,
            R.layout.locate_spinner
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.locateSpinner.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.brands,
            R.layout.filter_option_spiner
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.brandFilterSpinner.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.prise,
            R.layout.filter_option_spiner
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.priseFilterSpinner.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.size_filter,
            R.layout.filter_option_spiner
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.sizeFilterSpinner.adapter = adapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainViewModel.sizeOfCart.observe(viewLifecycleOwner, Observer {
            if (it <= 0) {
                binding.sizeOfCartTextView.visibility = View.INVISIBLE
            } else binding.sizeOfCartTextView.text = it.toString()
        })
        val categoryList = mainViewModel.getCategory(requireContext(), "Phone")
        super.onViewCreated(view, savedInstanceState)
        categoryAdapter = CategoryAdapter(object : ItemOnClickListener {
            override fun onClick(name: String) {
                clickCategory(name)
            }

            override fun onClickPlus(name: BasketModel) {
                TODO("Not yet implemented")
            }

            override fun onClickMinus(name: BasketModel) {
                TODO("Not yet implemented")
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
            hotSalesAdapter = HotSalesAdapter(object : BestSellerItemOnClickListener {
                override fun onClick(view: View) {
                    goToProductDetails(view)
                }
            })
        }
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.hotSellerRecycleView)
        binding.hotSellerRecycleView.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.hotSellerRecycleView.adapter = hotSalesAdapter

        mainViewModel.bestSellerList.observe(viewLifecycleOwner, Observer {
            bestSellerAdapter?.submitList(it)
        })
        if (bestSellerAdapter == null) {
            bestSellerAdapter = BestSellerAdapter(object : BestSellerItemOnClickListener {
                override fun onClick(view: View) {
                    goToProductDetails(view)
                }
            })
        }
        binding.bestSellerRecycleView.layoutManager = GridLayoutManager(context, 2)
        binding.bestSellerRecycleView.adapter = bestSellerAdapter

        mainViewModel.updateLists()
        mainViewModel.getSizeOfCartBasket()

        val openingValueAnimator = ValueAnimator.ofInt(1100, 0)
        openingValueAnimator.duration = 200
        openingValueAnimator.addUpdateListener {
            binding.filterOptionsConteiner.translationY =
                openingValueAnimator.animatedValue.toString().toFloat()
        }
        val hidingValueAnimator = ValueAnimator.ofInt(0, 1100)
        hidingValueAnimator.duration = 200
        hidingValueAnimator.addUpdateListener {
            binding.filterOptionsConteiner.translationY =
                hidingValueAnimator.animatedValue.toString().toFloat()
        }
        binding.filterButton.setOnClickListener {
            it.isClickable = false
            binding.filterOptionsConteiner.visibility = View.VISIBLE
            openingValueAnimator.start()
        }
        binding.closeFilterButton.setOnClickListener {
            hidingValueAnimator.start()
            android.os.Handler().postDelayed({
                binding.filterOptionsConteiner.visibility = View.INVISIBLE
            }, 200)
            binding.filterButton.isClickable = true
        }
        binding.doneButton.setOnClickListener {
            hidingValueAnimator.start()
            android.os.Handler().postDelayed({
                binding.filterOptionsConteiner.visibility = View.INVISIBLE
            }, 200)
            binding.filterButton.isClickable = true
        }

        binding.mainCartButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_cartFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        categoryAdapter = null
    }

    fun clickCategory(name: String) {
        Log.d("MainFragment", name)
        mainViewModel.changeCategory(name)
        categoryAdapter?.notifyDataSetChanged()
    }

    fun clickLike(id: String) {
        mainViewModel.clickLike(id)
        bestSellerAdapter?.notifyDataSetChanged()
    }

    fun goToProductDetails(view: View) {
        view.findNavController().navigate(R.id.action_mainFragment_to_productDetailsFragment)
    }
}