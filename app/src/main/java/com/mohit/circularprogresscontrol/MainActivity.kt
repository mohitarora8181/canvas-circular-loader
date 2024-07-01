package com.mohit.circularprogresscontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mohit.circularprogresscontrol.ui.theme.CircularProgressControlTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CircularProgressControlTheme {

                var progress by remember{
                    mutableFloatStateOf(0f)
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                    ){
                        Box(modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()){
                            CircularProgressIndicator(
                                progress = progress,
                                trackColor = Color.LightGray ,
                                modifier = Modifier
                                    .size(80.dp)
                                    .align(alignment = Alignment.Center),
                                color = Color.Black)
                            Text(
                                text = (progress*100).toInt().toString()+"%",
                                modifier = Modifier.align(alignment = Alignment.Center)
                            )
                        }
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                        ){
                            Slider(
                                value = progress,
                                onValueChange = {progress = it},
                                colors = SliderDefaults.colors(
                                    thumbColor = Color.Black,
                                    activeTrackColor = Color.Black,
                                    inactiveTickColor = Color.DarkGray
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
}