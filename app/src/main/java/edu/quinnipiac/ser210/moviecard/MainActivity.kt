package edu.quinnipiac.ser210.moviecard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.quinnipiac.ser210.moviecard.ui.theme.MovieCardTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    MovieCardTheme {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            MovieCard(
                R.drawable.deadpool2016,
                "Deadpool",
                "1:48",
                "Eng",
                8.0,
                "1.7k"
            )
        }
    }
}

@Composable
fun MovieCard(
    @DrawableRes drawable: Int,
    title: String,
    length: String,
    lang: String,
    rating: Double,
    review: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.8f to MaterialTheme.colorScheme.background,
                        0.9f to Color(190, 190, 190)
                    )
                )
            )
    ){
        CoverImage(drawable, modifier)
        Column(
            modifier = modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(380.dp)
        ){
            TitleRow(drawable, title, rating, modifier)
            MovieInfo(length, lang, rating, review, modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun CoverImage(@DrawableRes drawable: Int, modifier:Modifier = Modifier) {
    Image(
        painter = painterResource(drawable),
        contentDescription = "Deadpool",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .clip(RoundedCornerShape(24.dp))
    )
}

@Composable
fun TitleRow(@DrawableRes drawable: Int, title: String, rating: Double, modifier:Modifier = Modifier) {
    val stars = (rating / 2.0).roundToInt()
    Log.d("stars", "$stars")
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .padding(start = 36.dp, top = 28.dp)
            .fillMaxWidth()
    ) {
        TinyImage(drawable, modifier = modifier)
        MovieTitle(title, stars, modifier = modifier)
    }
}

@Composable
fun TinyImage(@DrawableRes drawable: Int, modifier:Modifier = Modifier) {
    Image(
        painter = painterResource(drawable),
        contentDescription = "Deadpool",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .width(120.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(4.dp))
    )
}

@Composable
fun MovieTitle(title: String, stars: Int, modifier:Modifier = Modifier) {
    Column{
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.secondary,
            modifier = modifier.padding(bottom = 24.dp)
        )
        Row {
            for (i in 1..5) {
                Icon(
                    painter = painterResource(R.drawable.baseline_star_rate_24),
                    contentDescription = "Star Rating",
                    tint = if (i <= stars) Color.Unspecified else Color.DarkGray,
                    modifier = modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun MovieInfoElement(infoType: String, info: String, modifier: Modifier = Modifier) {
    Column {
        Text(text = infoType,
            style = MaterialTheme.typography.labelSmall,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.secondary,
            modifier = modifier
        )
        Text(text = info,
            style = MaterialTheme.typography.labelSmall,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.secondary,
            modifier = modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun MovieInfo(length: String, lang: String, rating: Double, reviews: String, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(44.dp),
        modifier = modifier.padding(32.dp)
    ) {
        MovieInfoElement(infoType = "Length", length, modifier = modifier)
        MovieInfoElement(infoType = "Lang", lang, modifier = modifier)
        MovieInfoElement(infoType = "Rating", rating.toString(), modifier = modifier)
        MovieInfoElement(infoType = "Review", reviews, modifier = modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCardPreview() {
    MovieCardTheme {
        MyApp()
    }
}