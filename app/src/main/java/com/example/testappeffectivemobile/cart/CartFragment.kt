package com.example.testappeffectivemobile.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testappeffectivemobile.BaseFragment
import com.example.testappeffectivemobile.ItemOnClickListener
import com.example.testappeffectivemobile.R
import com.example.testappeffectivemobile.databinding.FragmentCartBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : BaseFragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val cartViewModel: CartViewModel by viewModel()
    private var basketAdapter: BasketAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_cart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.cartViewModel = cartViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (basketAdapter == null) {
            basketAdapter = BasketAdapter(object : ItemOnClickListener {
                override fun onClick(id: String) {
                }

                override fun onClickPlus(basket: BasketModel) {
                    basketChangeCount(basket, true)
                }

                override fun onClickMinus(basket: BasketModel) {
                    basketChangeCount(basket, false)
                }
            })
        }
        cartViewModel.cartModel.observe(
            viewLifecycleOwner,
            Observer {
                basketAdapter?.submitList(it.basketList)
                binding.totalPriseTextView.text =
                    String.format("$%s us", cartViewModel.cartModel.value?.total)
            })
        binding.itemCartRecycleView.adapter = basketAdapter
        binding.itemCartRecycleView.layoutManager = LinearLayoutManager(context)
        cartViewModel.getCartInfo()
        binding.cartBackButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_cartFragment_to_mainFragment)
        }
    }

    private fun basketChangeCount(basketModel: BasketModel, plus: Boolean) {
        val index = cartViewModel.cartModel.value?.basketList?.indexOf(basketModel)
        if (plus) {
            cartViewModel.cartModel.value?.basketList?.get(index ?: 0)!!.count += 1
        } else {
            if (cartViewModel.cartModel.value?.basketList?.get(index ?: 0)!!.count > 0)
                cartViewModel.cartModel.value?.basketList?.get(index ?: 0)!!.count -= 1
        }
        basketAdapter?.notifyDataSetChanged()
    }
}