package com.foivos.mycryptoapp.presentation.coin_list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.foivos.mycryptoapp.R
import com.foivos.mycryptoapp.domain.model.Coin
import com.foivos.mycryptoapp.domain.util.preview_data.CoinsProvider
import com.foivos.mycryptoapp.presentation.coin_list.components.CoinListItem
import com.foivos.mycryptoapp.presentation.components.MyCryptoToolbar
import com.foivos.mycryptoapp.presentation.ui.theme.MyCryptoAppTheme
import com.foivos.mycryptoapp.presentation.util.ObserveAsEvents
import com.foivos.mycryptoapp.presentation.util.TestTags
import kotlinx.coroutines.launch

@Composable
fun CoinListScreenRoot(
    onCoinClick: (String) -> Unit,
    viewModel: CoinListViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    ObserveAsEvents(
        flow = viewModel.events
    ) { event ->
            when (event) {
                is CoinListEvent.Error -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(event.error.asString(context))
                    }
                }
            }
    }

    CoinListScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is CoinListAction.OnCoinClick -> {
                    onCoinClick(action.coinId)
                }
            }
        },
        snackbarHostState = snackbarHostState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListScreen(
    state: CoinListState,
    onAction: (CoinListAction) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )

    Scaffold(
        modifier = Modifier,
        topBar = {
            MyCryptoToolbar(
                showBackButton = false,
                title = stringResource(id = R.string.app_name),
                startContent = { Icon(
                    Icons.Default.AttachMoney,
                    stringResource(id = R.string.content_description_money)
                ) },
                scrollBehavior = scrollBehavior
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .testTag(TestTags.COIN_LIST)
            ) {
                items(
                    items = state.coins,
                    //key = { it.id }
                ) { coin ->
                    CoinListItem(
                        coin = coin,
                        onItemClick = {
                            onAction(CoinListAction.OnCoinClick(coin.id))
                        }
                    )
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp),
                    strokeWidth = 2.dp
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
                coins = coins,
                isLoading = true
            ),
            onAction = {},
            remember { SnackbarHostState() }
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun CoinListScreenPreview(@PreviewParameter(CoinsProvider::class) coins: List<Coin>) {
    MyCryptoAppTheme {
        CoinListScreen(
            state = CoinListState(
                coins = coins,
                isLoading = true
            ),
            onAction = {},
            remember { SnackbarHostState() }
        )
    }
}

