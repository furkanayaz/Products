package com.fa.products.domain.base

import androidx.lifecycle.ViewModel
import com.fa.products.data.Endpoints
import io.ktor.client.HttpClient

abstract class BaseVM(
    private val client: HttpClient
): ViewModel() {
    protected val fetchAllProducts: String = Endpoints.OBJECTS
    protected val executeSingleProduct: (Int) -> String = { id: Int ->
        "${Endpoints.OBJECTS}/$id"
    }

    override fun onCleared() {
        client.close()
    }
}