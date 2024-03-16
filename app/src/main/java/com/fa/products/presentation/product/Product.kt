package com.fa.products.presentation.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fa.products.R
import com.fa.products.domain.models.Product

@Composable
fun Product(
    product: Product
) {
    Column(
        modifier = Modifier.fillMaxWidth().clickable {

        }
    ) {
        Text(text = stringResource(id = R.string.show_id, product.id.toString()))
        Text(
            text = stringResource(
                id = R.string.show_name, product.name ?: stringResource(id = R.string.name_empty)
            )
        )
    }
}