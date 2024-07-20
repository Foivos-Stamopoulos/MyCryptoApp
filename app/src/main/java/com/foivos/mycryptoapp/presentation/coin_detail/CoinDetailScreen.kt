package com.foivos.mycryptoapp.presentation.coin_detail

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.foivos.mycryptoapp.R
import com.foivos.mycryptoapp.domain.model.CoinDetail
import com.foivos.mycryptoapp.domain.util.preview_data.CoinDetailProvider
import com.foivos.mycryptoapp.presentation.coin_detail.components.CoinTag
import com.foivos.mycryptoapp.presentation.coin_detail.components.TeamMemberListItem
import com.foivos.mycryptoapp.presentation.components.MyCryptoToolbar
import com.foivos.mycryptoapp.presentation.ui.ObserveAsEvents
import com.foivos.mycryptoapp.presentation.ui.theme.MyCryptoAppTheme


@Composable
fun CoinDetailScreenRoot(
    onBackClick: (Unit) -> Unit,
    viewModel: CoinDetailViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            is CoinDetailEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    CoinDetailScreen(
        state = viewModel.state,
        action = { action ->
            when (action) {
                CoinDetailAction.OnBackClick -> {
                    onBackClick(Unit)
                }
            }
        }
    )

}


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CoinDetailScreen(
    state: CoinDetailState,
    action: (CoinDetailAction) -> Unit
) {
    Scaffold(
        topBar = {
            MyCryptoToolbar(
                showBackButton = true,
                title = "",
                onBackClick = {
                    action(CoinDetailAction.OnBackClick)
                })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
        ) {
            state.coinDetail?.let { coin ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                    ,
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier
                            )
                            Text(
                                text = stringResource(id = if (coin.isActive) R.string.active_coin else R.string.inactive_coin) ,
                                color = if (coin.isActive) Color.Green else Color.Red,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.End,
                                modifier = Modifier.align(CenterVertically)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = coin.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.tags),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            coin.tags.forEach { tag ->
                                CoinTag(tag = tag)
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.team_members),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    items(items = coin.team) { teamMember ->
                        TeamMemberListItem(
                            teamMember = teamMember,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                        HorizontalDivider()
                    }
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun CoinDetailScreenPreview(@PreviewParameter(CoinDetailProvider::class) coinDetail: CoinDetail) {
    MyCryptoAppTheme {
        CoinDetailScreen(
            CoinDetailState(
                isLoading = false,
                coinDetail = coinDetail
            ),
            action = {}
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun CoinDetailScreenDarkPreview(@PreviewParameter(CoinDetailProvider::class) coinDetail: CoinDetail) {
    MyCryptoAppTheme {
        CoinDetailScreen(
            CoinDetailState(
                isLoading = false,
                coinDetail = coinDetail
            ),
            action = {}
        )
    }
}