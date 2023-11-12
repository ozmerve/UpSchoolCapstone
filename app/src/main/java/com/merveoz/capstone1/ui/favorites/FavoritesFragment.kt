package com.merveoz.capstone1.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.merveoz.capstone1.R
import com.merveoz.capstone1.common.gone
import com.merveoz.capstone1.common.visible
import com.merveoz.capstone1.data.model.response.ProductUI
import com.merveoz.capstone1.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val binding by viewBinding(FragmentFavoritesBinding::bind)

    private val viewModel by viewModels<FavoritesViewModel>()

    private val favoritesAdapter = FavoritesAdapter(onProductClick = ::onProductClick, onDeleteClick = ::onDeleteClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFavorites()

        observeData()

        with(binding) {
            rvFavProducts.adapter = favoritesAdapter

            tvClear.setOnClickListener {
                onClearClick()
            }

            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun observeData() = with(binding) {
        viewModel.favoritesState.observe(viewLifecycleOwner) { state ->
            when (state) {
                FavoritesState.Loading -> progressBar.visible()

                is FavoritesState.SuccessState -> {
                    progressBar.gone()
                    favoritesAdapter.submitList(state.products)
                }

                is FavoritesState.EmptyScreen -> {
                    progressBar.gone()
                    ivError.visible()
                    tvError.visible()
                    rvFavProducts.gone()
                    tvError.text = state.failMessage
                    tvClear.gone()
                }

                is FavoritesState.ShowPopUp -> {
                    progressBar.gone()
                    tvClear.gone()
                    Snackbar.make(requireView(), state.errorMessage, 1000).show()
                }
            }
        }
    }

    private fun onProductClick(id: Int) {
        findNavController().navigate(FavoritesFragmentDirections.favoritesToDetail(id))
    }

    private fun onDeleteClick(product: ProductUI) {
        viewModel.deleteFromFavorites(product)
    }

    private fun onClearClick() {
        viewModel.clearFavorites()
    }
}