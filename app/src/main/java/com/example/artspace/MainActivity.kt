package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.data.DataSource
import com.example.artspace.model.Artwork
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }

}

@Composable
private fun ArtSpaceApp() {
    val artworks = DataSource().artworks
    var currentIndex by remember { mutableStateOf<Int>(0) }
    ArtSpaceLayout(
        artwork = artworks[currentIndex],
        onPreviousClick = { currentIndex-- },
        onNextClick = { currentIndex++ },
        isPreviousEnabled = currentIndex != 0,
        isNextEnabled = currentIndex != artworks.lastIndex
    )
}

@Composable
private fun ArtSpaceLayout(
    artwork: Artwork,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    isPreviousEnabled: Boolean,
    isNextEnabled: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkPicture(artwork.imageResId)
        Spacer(modifier = Modifier.height(24.dp))
        ArtworkInfo(
            artwork.title,
            artwork.artist,
            artwork.year
        )
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeGesturesPadding()
                .border(2.dp, Color.Blue),
            contentAlignment = Alignment.BottomCenter
        ) {
            NavigationButtons(
                onPreviousClick = onPreviousClick,
                onNextClick = onNextClick,
                isPreviousEnabled = isPreviousEnabled,
                isNextEnabled = isNextEnabled
            )
        }
    }
}

@Composable
private fun ArtworkPicture(
    painterResId: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.size(240.dp, 320.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Image(
            painter = painterResource(id = painterResId),
            contentDescription = stringResource(id = R.string.artwork_pic_content_description),
            modifier = modifier
                .padding(20.dp)
                .fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
private fun ArtworkInfo(
    title: String,
    artist: String,
    year: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(240.dp)
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "$artist($year)",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun NavigationButtons(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    isPreviousEnabled: Boolean,
    isNextEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.width(140.dp),
            enabled = isPreviousEnabled
        ) {
            Text(
                text = "Previous",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Button(
            onClick = onNextClick,
            modifier = Modifier.width(140.dp),
            enabled = isNextEnabled
        ) {
            Text(
                text = "Next",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}