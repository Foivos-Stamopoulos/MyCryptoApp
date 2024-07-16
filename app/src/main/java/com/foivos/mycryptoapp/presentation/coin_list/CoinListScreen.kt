package com.foivos.mycryptoapp.presentation.coin_list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.foivos.mycryptoapp.R
import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.util.preview_data.CoinProvider
import com.foivos.mycryptoapp.domain.util.preview_data.CoinsProvider
import com.foivos.mycryptoapp.presentation.coin_list.components.CoinListItem
import com.foivos.mycryptoapp.presentation.components.MyCryptoToolbar
import com.foivos.mycryptoapp.presentation.ui.theme.MyCryptoAppTheme

@Composable
fun CoinListScreenRoot() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListScreen(
    state: CoinListState,
    onAction: (CoinListAction) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyCryptoToolbar(
                showBackButton = false,
                title = stringResource(id = R.string.app_name),
                startContent = { Icon(
                    Icons.Default.AttachMoney,
                    stringResource(id = R.string.content_description_money)
                ) }
            )
        }
    ) {
        paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)) {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = state.coins
                ) { coin ->
                    CoinListItem(
                        coin = coin,
                        onItemClick = {

                        }
                    )
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun CoinListScreenDarkPreview(@PreviewParameter(CoinsProvider::class) coins: List<Coin>) {
    MyCryptoAppTheme {
        CoinListScreen(
            state = CoinListState(
                coins = coins
            ),
            onAction = {}
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun CoinListScreenPreview(@PreviewParameter(CoinsProvider::class) coins: List<Coin>) {
    MyCryptoAppTheme {
        CoinListScreen(
            state = CoinListState(
                coins = coins
            ),
            onAction = {}
        )
    }
}

