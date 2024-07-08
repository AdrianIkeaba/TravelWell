package com.ghostdev.travelwell.ui.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghostdev.travelwell.data.models.ProductResponse
import com.ghostdev.travelwell.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductListLogic : ViewModel() {
    private val repository = ProductRepository()

    private val _products = mutableStateOf<ProductResponse?>(null)
    val products: State<ProductResponse?> get() = _products

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            try {
                _products.value = repository.getProducts()
            } catch (e: Exception) {
                _products.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}

