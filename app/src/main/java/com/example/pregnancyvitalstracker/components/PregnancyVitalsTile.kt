package com.example.pregnancyvitalstracker.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pregnancyvitalstracker.R
import com.example.pregnancyvitalstracker.ui.theme.containerLightPurple
import com.example.pregnancyvitalstracker.ui.theme.darkPurple
import com.example.pregnancyvitalstracker.ui.theme.fontLight
import com.example.pregnancyvitalstracker.ui.theme.fontSemiBold
import com.example.pregnancyvitalstracker.ui.theme.purple
import com.example.pregnancyvitalstracker.ui.theme.white

@Composable
fun PregnancyVitalsTile(
    bpmValue: String,
    mmHgValue: String,
    kgValue: String,
    kicksValue: String,
    time: String,
) {
    Column(
        modifier = Modifier
            .padding(bottom = 15.dp)
            .clip(shape = RoundedCornerShape(4.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(containerLightPurple)
                .padding(15.dp)
        ) {
            Column {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_heart_rate),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "$bpmValue bpm",
                            style = fontSemiBold.copy(
                                fontSize = 10.sp,
                                color = darkPurple
                            )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_blood_pressure),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "$mmHgValue mmHg",
                            style = fontSemiBold.copy(
                                fontSize = 10.sp,
                                color = darkPurple
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(11.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_scale),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "$kgValue kg",
                            style = fontSemiBold.copy(
                                fontSize = 10.sp,
                                color = darkPurple
                            )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_new_born),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "$kicksValue kicks",
                            style = fontSemiBold.copy(
                                fontSize = 10.sp,
                                color = darkPurple
                            )
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(purple)
                .padding(horizontal = 9.dp, vertical = 6.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = time,
                style = fontLight.copy(fontSize = 12.sp, color = white)
            )
        }
    }
}
