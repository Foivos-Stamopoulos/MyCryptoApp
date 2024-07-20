package com.foivos.mycryptoapp.presentation.coin_detail.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foivos.mycryptoapp.domain.model.TeamMember
import com.foivos.mycryptoapp.presentation.ui.theme.MyCryptoAppTheme

@Composable
fun TeamMemberListItem(
    teamMember: TeamMember,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = teamMember.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = teamMember.position,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
fun TeamMemberListItemPreview() {
    MyCryptoAppTheme {
        TeamMemberListItem(
            TeamMember(
                id = "1",
                name =  "John Smith",
                position = "CTO"
            )
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun TeamMemberListItemDarkPreview() {
    MyCryptoAppTheme {
        TeamMemberListItem(
            TeamMember(
                id = "1",
                name =  "John Smith",
                position = "CTO"
            )
        )
    }
}