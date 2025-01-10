package com.nikmaram.products.ui.feature.details.comosables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.nikmaram.data.model.Product
import com.nikmaram.products.R
import com.nikmaram.products.ui.base.SIDE_EFFECTS_KEY
import com.nikmaram.products.ui.feature.common.NetworkError
import com.nikmaram.products.ui.feature.common.Progress
import com.nikmaram.products.ui.feature.details.DetailsContract
import com.nikmaram.products.ui.utility.generateFakeProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


@Composable
fun DetailsScreen(
    state: DetailsContract.DetailsState,
    effectFlow: Flow<DetailsContract.Effect>?,
    isBookMarked: Boolean,
    onNavigationRequested: (navigationEffect: DetailsContract.Effect.Navigation) -> Unit,
    onEventSent: (event: DetailsContract.Event) -> Unit,
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                DetailsContract.Effect.Navigation.Back -> {
                    onNavigationRequested(DetailsContract.Effect.Navigation.Back)
                }
            }
        }?.collect()
    }
    Scaffold(
        topBar = {
            DetailsTopBar(
                isBookMarked = isBookMarked,
                onBackClick = {
                    onEventSent(DetailsContract.Event.BackButtonClicked)
                },
                onBookmarkClick = {isBookMarked ->
                    onEventSent(DetailsContract.Event.BookMarkClicked(isBookMarked))
                }
            )
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            when (state) {
                is DetailsContract.DetailsState.DataLoaded -> {
                    state.product?.let {
                        DetailsScreenContent(it)
                    }
                }
                is DetailsContract.DetailsState.Error -> {
                    NetworkError { onEventSent(DetailsContract.Event.Retry) }
                }
                is DetailsContract.DetailsState.Initial -> {
                    Progress(Modifier.fillMaxSize())
                }
                is DetailsContract.DetailsState.LoadingState -> {
                    Progress(Modifier.fillMaxSize())
                }
            }
        }
    }
}


@Composable
fun DetailsScreenContent(product: Product) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(dimensionResource(id = R.dimen.size_16)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Text(
                text = product.title,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen.size_24).value.sp,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.size_16))
            )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_16)))

        ArticleImage(urlToImage = product.image)

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_16)))

        ArticleContent(content = product.description)

    }
}

@Composable
fun ArticleImage(urlToImage: String?) {
    if (urlToImage != null) {
        AsyncImage(
            model = urlToImage,
            contentDescription = stringResource(R.string.product_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.size_200))
                .background(
                    Color.Gray.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_8))
                )
                .padding(dimensionResource(id = R.dimen.size_8))
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.size_200))
                .background(
                    Color.Gray.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_8))
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.image_not_available),
                color = Color.White,
                fontSize = dimensionResource(id = R.dimen.font_16).value.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ArticleContent(content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(dimensionResource(id = R.dimen.size_8))
            )
            .padding(dimensionResource(id = R.dimen.size_16))
    ) {
        Text(
            text = content,
            fontSize = dimensionResource(id = R.dimen.font_16).value.sp,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenSuccessPreview() {
    DetailsScreen(
        state = DetailsContract.DetailsState.DataLoaded(
            product = generateFakeProduct().first(),
        ),
        effectFlow = null,
        onNavigationRequested = {},
        onEventSent = {},
        isBookMarked = true
    )
}
