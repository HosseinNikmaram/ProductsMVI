package com.nikmaram.products.ui.home.comosables
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nikmaram.products.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeTopBarPreview() {
    HomeTopBar()
}