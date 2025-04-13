package com.pedrosiccha.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FooterLinks(
    modifier: Modifier = Modifier,
    links: List<Pair<String, () -> Unit>>,
    textColor: Color = MaterialTheme.colorScheme.primary,
    fontSize: Int = 12
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        links.forEachIndexed { index, pair ->
            LinkButton(
                text = pair.first,
                onClick = pair.second,
                textColor = textColor,
                fontSize = fontSize,
                modifier = Modifier.padding(horizontal = 4.dp),
                textAlign = TextAlign.Center
            )
            if (index < links.size - 1) {
                Text(
                    text = "|",
                    color = Color.Gray,
                    fontSize = fontSize.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FooterLinksPreview() {
    FooterLinks(
        links = listOf(
            "Términos" to {},
            "Planes" to {},
            "Contáctanos" to {}
        )
    )
}
