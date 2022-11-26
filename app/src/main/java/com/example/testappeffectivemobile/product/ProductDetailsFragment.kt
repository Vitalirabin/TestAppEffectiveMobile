package com.example.testappeffectivemobile.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import com.example.testappeffectivemobile.BaseFragment
import com.example.testappeffectivemobile.ItemOnClickListener
import com.example.testappeffectivemobile.R
import com.example.testappeffectivemobile.cart.BasketModel
import com.example.testappeffectivemobile.databinding.FragmentProductDetailesBinding
import com.example.testappeffectivemobile.product.capacity.CapacityAdapter
import com.example.testappeffectivemobile.product.color.ColorAdapter
import com.example.testappeffectivemobile.product.photo_product.CarouselLayoutManager
import com.example.testappeffectivemobile.product.photo_product.PhotoProductAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailsFragment : BaseFragment() {
    private var _binding: FragmentProductDetailesBinding? = null
    private val binding get() = _binding!!
    val productDetailsViewModel: ProductDetailsViewModel by viewModel()
    private var capacityAdapter: CapacityAdapter? = null
    private var colorAdapter: ColorAdapter? = null
    private var photoAdapter: PhotoProductAdapter? = null


    override fun getLayoutId(): Int = R.layout.fragment_product_detailes


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        _binding = FragmentProductDetailesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.productViewModel = productDetailsViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (capacityAdapter == null) {
            capacityAdapter = CapacityAdapter(object : ItemOnClickListener {
                override fun onClick(capacity: String) {
                    capacityClick(capacity)
                }

                override fun onClickPlus(name: BasketModel) {
                    TODO("Not yet implemented")
                }

                override fun onClickMinus(name: BasketModel) {
                    TODO("Not yet implemented")
                }
            })
        }
        productDetailsViewModel.productCapacityModels.observe(viewLifecycleOwner, Observer {
            capacityAdapter?.submitList(it)
        })
        binding.capacityRecycleView.adapter = capacityAdapter
        binding.capacityRecycleView.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }

        if (colorAdapter == null) {
            colorAdapter = ColorAdapter(object : ItemOnClickListener {
                override fun onClick(color: String) {
                    colorClick(color)
                }

                override fun onClickPlus(name: BasketModel) {
                    TODO("Not yet implemented")
                }

                override fun onClickMinus(name: BasketModel) {
                    TODO("Not yet implemented")
                }
            })
        }
        productDetailsViewModel.productColorModels.observe(viewLifecycleOwner, Observer {
            colorAdapter?.submitList(it)
        })
        binding.colorRecyclerView.adapter = colorAdapter
        binding.colorRecyclerView.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }

        if (photoAdapter == null) {
            photoAdapter = PhotoProductAdapter(object : ItemOnClickListener {
                override fun onClick(photo: String) {
                }

                override fun onClickPlus(name: BasketModel) {
                    TODO("Not yet implemented")
                }

                override fun onClickMinus(name: BasketModel) {
                    TODO("Not yet implemented")
                }
            })
        }
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.photoProductDetailsRecycleView)
        binding.photoProductDetailsRecycleView.adapter = photoAdapter
        binding.photoProductDetailsRecycleView.layoutManager =
            CarouselLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        productDetailsViewModel.productDetailsModel.observe(viewLifecycleOwner, Observer {
            if (it.isFavorites) {
                Glide.with(view).load(R.drawable.vecror_like_white)
                    .into(binding.productDetailsLikeButton)
            }
            binding.addToCurtButton.text = String.format(
                "%s        $%s0",
                getString(R.string.add_to_cart).toString(),
                productDetailsViewModel.productDetailsModel.value?.price.toString().toDouble()
                    .toString()
            )
            photoAdapter?.submitList(it.images)
        })

        binding.backButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_productDetailsFragment_to_mainFragment)
        }
        productDetailsViewModel.loadProductDetails()
        binding.productCartButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_productDetailsFragment_to_cartFragment)
        }
    }


    fun colorClick(color: String) {
        productDetailsViewModel.colorClick(color)
        colorAdapter?.notifyDataSetChanged()
    }

    fun capacityClick(capacity: String) {
        productDetailsViewModel.capacityClick(capacity)
        capacityAdapter?.notifyDataSetChanged()
    }
}