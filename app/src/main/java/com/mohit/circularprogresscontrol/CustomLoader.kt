package com.mohit.circularprogresscontrol

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainComponent() {
    var isPlaying by remember { mutableStateOf(false) }

    val darktheme = isSystemInDarkTheme()

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        CircularProgress(isPlaying)
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(10.dp)
        ){
            Button(onClick = {isPlaying= true}, modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = if(darktheme) Color.White else Color.Black),
                enabled = !isPlaying
            ) {
                Text(text = "Play", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick = { isPlaying = false}, modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = if(darktheme) Color.White else Color.Black) ,
                enabled = isPlaying
            ) {
                Text(text = "Pause", fontSize = 18.sp)
            }
        }
    }
}


@Composable
fun CircularProgress(isPlaying : Boolean,speed:Int = 1000,size:Int = 100,strokeWidth:Int = 10){

    val infiniteTransition = rememberInfiniteTransition(label = "forCircularProgress")
    var lastRotation by remember { mutableFloatStateOf(0f) }

    val rotation by infiniteTransition.animateFloat(
        label = "rotationValue",
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = speed,
                easing = FastOutLinearInEasing
            )
        )
    )

    LaunchedEffect(rotation) {
        if(isPlaying){
            lastRotation = rotation
        }
    }

    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Canvas(modifier = Modifier.size(size.dp)){
            drawCircle(
                color = Color.LightGray,
                style = Stroke(width = strokeWidth.dp.toPx(), cap = StrokeCap.Round)
            )
            rotate(if(isPlaying) rotation else lastRotation){
                drawArc(
                    color = Color.DarkGray,
                    startAngle = 0f,
                    sweepAngle = 120f,
                    useCenter = false,
                    style = Stroke(width = strokeWidth.dp.toPx(), cap = StrokeCap.Round)
                )
            }
        }
    }
}