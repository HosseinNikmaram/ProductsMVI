package com.nikmaram.products.ui.home.comosables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import com.nikmaram.data.model.Product
import com.nikmaram.products.R
import com.nikmaram.products.ui.home.comosables.HomeListItem


@Composable
fun HomeListItems(
    products: List<Product>,
    onProductClicked: (Product) -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.size_4))
                .testTag("homeListItemsLazyColumn"),
        ) {
            item { Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.size_4))) }

                items(
                    count = products.size,
                    key = {index -> products[index].id },
                ) { index ->
                    products[index].let { product ->
                        HomeListItem(
                            product = product,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onProductClicked(product) }
                                .padding(dimensionResource(id = R.dimen.size_12))
                                .testTag("homeListItem")
                        )
                    }
                }
            item { Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.size_4))) }
        }
    }
}
