package com.example.pregnancyvitalstracker.components

import android.provider.CalendarContract
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pregnancyvitalstracker.ui.theme.black
import com.example.pregnancyvitalstracker.ui.theme.darkPurple
import com.example.pregnancyvitalstracker.ui.theme.fontSemiBold
import com.example.pregnancyvitalstracker.ui.theme.purple
import com.example.pregnancyvitalstracker.ui.theme.white

@Composable
fun AddVitalsDialog(
    firstText: MutableState<String>,
    secondText: MutableState<String>,
    weightValue: MutableState<String>,
    babyKickValue: MutableState<String>,
    showDialog: MutableState<Boolean>,
    onSubmit: (String, String, String, String) -> Unit
) {
    AlertDialog(
        containerColor = white,
        shape = RoundedCornerShape(8.dp),
        onDismissRequest = { showDialog.value = false },
        title = {
            Text(
                "Add Vitals",
                style = fontSemiBold.copy(fontSize = 16.sp, color = darkPurple),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        },
        text = {
            Column() {
                Row(modifier = Modifier.fillMaxWidth()) {
                    CommonTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        text = firstText,
                        label = "Sys BP",
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    CommonTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        text = secondText,
                        label = "Dia BP",
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                CommonTextField(
                    modifier = Modifier,
                    text = weightValue,
                    label = "Weight ( in kg )",
                )
                Spacer(modifier = Modifier.height(8.dp))
                CommonTextField(
                    modifier = Modifier,
                    text = babyKickValue,
                    label = "Baby Kicks",
                )
            }
        },
        confirmButton = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    modifier = Modifier
                        .width(150.dp)
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = purple,
                    ),
                    shape = RoundedCornerShape(4.dp),
                    onClick = {
                        onSubmit(
                            firstText.value,
                            secondText.value,
                            weightValue.value,
                            babyKickValue.value
                        )
                        showDialog.value = false
                    }
                ) {
                    Text("Submit")
                }
            }
        },
    )
}