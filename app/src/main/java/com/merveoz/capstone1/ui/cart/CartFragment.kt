package com.merveoz.capstone1.ui.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.merveoz.capstone1.R
import com.merveoz.capstone1.common.gone
import com.merveoz.capstone1.common.visible
import com.merveoz.capstone1.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {

    private val binding by viewBinding(FragmentCartBinding::bind)

    private val viewModel by viewModels<CartViewModel>()

    private val cartAdapter = CartAdapter(onProductClick = ::onProductClick, onDeleteClick = ::onDeleteClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCartProducts()

        observeData()

        with(binding) {

            rvCartProducts.adapter = cartAdapter

            ivClearCart.setOnClickListener{
                onClearClick()
            }

            ivBackCart.setOnClickListener{
                findNavController().navigateUp()
            }

            btnGoToPayment.setOnClickListener{
                findNavController().navigate(CartFragmentDirections.cartToPayment())
            }
        }
    }

    private fun observeData() = with(binding) {
        viewModel.cartProductState.observe(viewLifecycleOwner) {
            when (it) {
                CartProductState.Loading -> {
                    progressBar.visible()
                    tvTotalAmount.gone()
                    tvTotalAmountText.gone()
                    ivBackCart.gone()
                    ivClearCart.gone()
                    tvCart.gone()
                    btnGoToPayment.gone()
                }

                is CartProductState.SuccessState -> {
                    cartAdapter.submitList(it.products)
                    progressBar.gone()
                    tvTotalAmount.visible()
                    tvTotalAmountText.visible()
                    ivBackCart.visible()
                    ivClearCart.visible()
                    tvCart.visible()
                    btnGoToPayment.visible()
                }

                is CartProductState.EmptyScreen -> {
                    progressBar.gone()
                    rvCartProducts.gone()
                    tvError.text = it.failMessage
                    tvError.visible()
                    ivError.visible()
                    tvTotalAmount.gone()
                    tvTotalAmountText.gone()
                    ivClearCart.gone()
                    tvCart.gone()
                    btnGoToPayment.gone()
                }

                is CartProductState.ShowPopUp -> {
                    progressBar.gone()
                    tvTotalAmount.gone()
                    tvTotalAmountText.gone()
                    Snackbar.make(requireView(), it.errorMessage, 1000).show()
                }
            }
        }

        viewModel.totalPriceAmount.observe(viewLifecycleOwner) { amount->
            tvTotalAmount.text = String.format("$ %.2f", amount)
        }
    }

    private fun onProductClick(id: Int) {
        findNavController().navigate(CartFragmentDirections.cartToDetail(id))
    }

    private fun onDeleteClick(productId: Int) {
        viewModel.deleteFromCart(productId)
    }

    private fun onClearClick() {
        viewModel.clearCart()
    }
}