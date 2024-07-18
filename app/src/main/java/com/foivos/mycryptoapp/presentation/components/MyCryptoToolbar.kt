@file:OptIn(ExperimentalMaterial3Api::class)

package com.foivos.mycryptoapp.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.foivos.mycryptoapp.R
import com.foivos.mycryptoapp.presentation.ui.theme.MyCryptoAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCryptoToolbar(
    showBackButton: Boolean,
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    startContent: (@Composable () -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                startContent?.invoke()
                Text(text = title)
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.content_description_go_back),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TopBarDarkPreview() {
    MyCryptoAppTheme {
        MyCryptoToolbar(
            showBackButton = true,
            stringResource(id = R.string.app_name),
            startContent = {
                Icon(
                    Icons.Default.AttachMoney,
                    stringResource(id = R.string.content_description_money)
                )
            }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun TopBarPreview() {
    MyCryptoAppTheme {
        MyCryptoToolbar(
            showBackButton = true,
            stringResource(id = R.string.app_name),
            startContent = {
                Icon(
                    Icons.Default.AttachMoney,
                    stringResource(id = R.string.content_description_money)
                )
            }
        )
    }
}