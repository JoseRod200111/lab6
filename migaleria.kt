implementation "androidx.compose.ui:ui:1.0.0"
implementation "androidx.compose.material:material:1.0.0"
implementation "androidx.activity:activity-compose:1.3.1"
implementation "io.coil-kt:coil-compose:1.5.0"
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.util.CoilUtils
import okhttp3.OkHttpClient

class GalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryScreen()
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun GalleryScreen() {
    var isLoggedOut by remember { mutableStateOf(false) }

    if (isLoggedOut) {

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Mi Galería") },
                actions = {
                    IconButton(onClick = { isLoggedOut = true }) {
                        Icon(imageVector = Icons.Default.Logout, contentDescription = null)
                    }
                }
            )
        },
        content = {
            GalleryContent()
        }
    )
}

@Composable
fun GalleryContent() {
    val imageList = listOf(
        "screenshot1.png",
        "screenshot2.png",
        "error.png",
        "image246.png",
    )

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(imageList) { imagePath ->
            GalleryItem(imagePath, context)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun GalleryItem(imagePath: String, context: android.content.Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.clickable {}
        ) {
            val imagePainter = rememberImagePainter(
                data = imageFileUri(context, imagePath),
                builder = {

                    crossfade(true)
                    placeholder(image246.png)
                    error(error_image.png)
                }
            )

            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
            )

            Text(
                text = "Descripción de la imagen",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
private fun imageFileUri(context: android.content.Context, imagePath: String): String {
    val fileUri = context.filesDir.resolve(imagePath)
    return fileUri.toString()
}