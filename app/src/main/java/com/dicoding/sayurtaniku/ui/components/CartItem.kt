package com.dicoding.sayurtaniku.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.sayurtaniku.R
import com.dicoding.sayurtaniku.ui.theme.SayurTanikuTheme
import com.dicoding.sayurtaniku.ui.theme.Shapes

@Composable
fun CartItem(
    sayurId: Long,
    image: Int,
    title: String,
    totalHarga: Int,
    count: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small)
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(
                    R.string.required_harga,
                    totalHarga
                ),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall,
            )
        }
        ProductCounter(
            orderId = sayurId,
            orderCount = count,
            onProductIncreased = { onProductCountChanged(sayurId, count + 1) },
            onProductDecreased = { onProductCountChanged(sayurId, count - 1) },
            modifier = Modifier.padding(8.dp)
        )

    }
}

@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
    SayurTanikuTheme {
        CartItem(
            4, R.drawable.sayur_4, "Jagung", 4000, 0,
            onProductCountChanged = { sayurId, count -> },
        )
    }
}