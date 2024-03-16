package com.fa.products.presentation.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fa.products.domain.Result
import com.fa.products.domain.models.Product
import com.fa.products.presentation.product.Product

@Composable
fun Products() {
    val viewModel: ProductVM = viewModel()

    val products: State<Result<List<Product>>> = viewModel.productsFlow.collectAsState(initial = Result.Loading)

    LaunchedEffect(Unit) {
        viewModel.fetchAllProducts()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        with(products.value) result@{
            when (val result = this) {
                is Result.Success -> items(count = result.data.size) {
                    val product: Product = result.data[it]
                    Product(product = product)
                    Spacer(modifier = Modifier.size(size = 10.0.dp))
                }

                is Result.Error -> item {
                    SelectionContainer {
                        Text(text = result.message)
                    }
                }

                Result.Loading -> item {
                    CircularProgressIndicator()
                }
            }
        }
    }

}