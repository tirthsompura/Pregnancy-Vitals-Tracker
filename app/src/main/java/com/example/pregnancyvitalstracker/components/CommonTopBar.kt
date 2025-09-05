package com.example.pregnancyvitalstracker.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pregnancyvitalstracker.ui.theme.darkPurple
import com.example.pregnancyvitalstracker.ui.theme.fontSemiBold
import com.example.pregnancyvitalstracker.ui.theme.lightPurple

@Composable
fun CommonTopBar(modifier: Modifier, title: String) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
            .background(lightPurple)
            .padding(start = 10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = fontSemiBold.copy(color = darkPurple, fontSize = 16.sp)
        )
    }
}