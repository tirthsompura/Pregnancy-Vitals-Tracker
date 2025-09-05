package com.example.pregnancyvitalstracker.components

import android.provider.CalendarContract
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pregnancyvitalstracker.ui.theme.black
import com.example.pregnancyvitalstracker.ui.theme.fontRegular
import com.example.pregnancyvitalstracker.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(modifier: Modifier, text: MutableState<String>, label: String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = black,
                shape = RoundedCornerShape(2.dp)
            )
    ) {
        TextField(
            modifier = Modifier
                .height(48.dp)
                .background(white)
                .padding(0.dp),
            value = text.value,
            onValueChange = { text.value = it },
            placeholder = {
                Text(
                    text = label,
                    style = fontRegular.copy(fontSize = 12.sp, color = black.copy(alpha = 0.5f)),
                )
            },
            textStyle = fontRegular.copy(fontSize = 12.sp, color = black),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                errorContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.Gray,
                errorTextColor = Color.Red,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(2.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
        )
    }
}