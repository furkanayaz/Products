package com.fa.products.presentation.products

import androidx.lifecycle.viewModelScope
import com.fa.products.data.dto.ProductDTO
import com.fa.products.domain.Result
import com.fa.products.domain.base.BaseVM
import com.fa.products.domain.mappers.ProductMapper
import com.fa.products.domain.models.Product
import com.fa.products.domain.services.Executor
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductVM @Inject constructor(
    private val client: HttpClient,
    private val executor: Executor,
    private val mapper: ProductMapper
) : BaseVM(client = client) {
    private lateinit var allObjectsJob: Job

    private val _productsFlow: MutableSharedFlow<Result<List<Product>>> = MutableSharedFlow()
    val productsFlow: SharedFlow<Result<List<Product>>> = _productsFlow.asSharedFlow()

    suspend fun fetchAllProducts() {
        allObjectsJob = viewModelScope.launch(executor.io) {
            try {
                with(client.get(fetchAllProducts)) {
                    if (this.status.value in 200..299)
                        _productsFlow.emit(Result.Success(data = (body() as List<ProductDTO>).map { dto ->
                            with(mapper) {
                                dto.mapToModel()
                            }
                        }))
                    else
                        _productsFlow.emit(Result.Error(bodyAsText()))
                }
            } catch (e: Exception) {
                _productsFlow.emit(Result.Error(message = e.message ?: "Empty"))
                e.printStackTrace()
            }
        }
    }
}