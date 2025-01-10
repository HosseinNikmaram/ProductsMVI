package com.nikmaram.products.ui.feature.details.comosables

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nikmaram.products.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    isBookMarked:Boolean,
    onBackClick: () -> Unit,
    onBookmarkClick: (Boolean) -> Unit,
) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.productDetails)) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    onBookmarkClick(!isBookMarked)
                }
            ) {
                Icon(
                    imageVector = if (isBookMarked) {
                        Icons.Default.Bookmark
                    } else {
                        Icons.Default.BookmarkBorder
                    },
                    contentDescription = if (isBookMarked) {
                        stringResource(R.string.remove_bookmark)
                    } else {
                        stringResource(R.string.add_bookmark)
                    }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeTopBarPreview() {
    DetailsTopBar(true,{},{})
}
