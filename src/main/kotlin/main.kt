import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun main() = Window(
    title = "Color & Scale animation preview"
) {
    var sizeState by remember { mutableStateOf(200.dp) }
    val size by animateDpAsState(
        targetValue = sizeState,
        spring(
            Spring.DampingRatioLowBouncy
        )
    )

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            tween(2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.fillMaxWidth().background(Color.Blue)) {
            Button(onClick = {
                sizeState += 50.dp
            }) {
                Text("+50dp")
            }
            Button(onClick = {
                sizeState -= 50.dp
            }) {
                Text("-50dp")
            }
        }
        Spacer(Modifier.weight(1f))
        Box(Modifier.size(size).background(color)) {
            Box(Modifier.align(Alignment.Center).size(size/4).background(Color.White)) {
                Box(Modifier.align(Alignment.CenterEnd).size(size/8).background(Color.Black))
            }
        }
    }
}