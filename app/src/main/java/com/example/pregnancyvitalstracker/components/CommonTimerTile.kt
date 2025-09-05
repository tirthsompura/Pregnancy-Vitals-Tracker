package com.example.pregnancyvitalstracker.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pregnancyvitalstracker.ui.theme.darkPurple
import com.example.pregnancyvitalstracker.ui.theme.darkRed
import com.example.pregnancyvitalstracker.ui.theme.fontLight
import com.example.pregnancyvitalstracker.ui.theme.fontSemiBold
import com.example.pregnancyvitalstracker.ui.theme.green
import com.example.pregnancyvitalstracker.ui.theme.purple
import com.example.pregnancyvitalstracker.ui.theme.white

@Composable
fun CommonTimerTile(
    isTimerRunning: Boolean,
    currentTime: String,
    onStart: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(purple)
            .padding(horizontal = 11.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Timmer - ${currentTime.ifEmpty { "00:00:00" }}",
            style = fontLight.copy(color = white, fontSize = 12.sp)
        )
        Button(
            modifier = Modifier.height(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (!isTimerRunning) green else darkRed,
            ),
            shape = RoundedCornerShape(6.dp),
            onClick = {
                onStart()
            }
        ) {
            Text(
                if (!isTimerRunning) "Start" else "Stop", style = fontSemiBold.copy(
                    fontSize = 10.sp,
                    color = white
                )
            )
        }
    }
}