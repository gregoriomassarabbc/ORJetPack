package uk.co.bbc.orjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.co.bbc.orjetpack.ui.theme.ORJetPackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ORJetPackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(24.dp),
            shape = RoundedCornerShape(
                corner = CornerSize(20.dp)
            ),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.height(400.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile(modifier = Modifier.padding(40.dp))
                Divider(
                    modifier = Modifier.padding(20.dp, top = 0.dp, bottom = 20.dp),
                    color = Color.Red,
                    thickness = 1.dp
                )
                CreateInfo(
                    name = "Gregorio", modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Button(modifier = Modifier.padding(1.dp), onClick = {
                    buttonClickedState.value = !buttonClickedState.value
                }) {
                    Text(
                        text = "Portfolio",
                        modifier = Modifier.padding(2.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
            if (buttonClickedState.value) {
                Content()
            } else {
                Box {}
            }
        }
    }

}

// we can pass an optional Modifier into our function
@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier,
        shape = CircleShape,
        border = BorderStroke(1.5.dp, Color.LightGray),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.me_fixed),
            contentDescription = "Gregorio image",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}


@Preview
@Composable
private fun Content() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.Red)
        ) {
            Portfolio(data = listOf("Project1", "Project2", "Project3"))

        }
    }

}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data) {
            Text(text = it)
        }

    }
}

@Composable
private fun CreateInfo(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.padding(5.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Hello $name!",
            modifier,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = "Android Composer", modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "@gmassara",
            modifier = Modifier.padding(2.dp),
            style = MaterialTheme.typography.titleMedium,
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ORJetPackTheme {
        CreateBizCard()
    }
}