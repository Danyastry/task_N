package com.example.testnitrix.presentation.player.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testnitrix.ui.theme.Background
import com.example.testnitrix.ui.theme.montserrat_bold

@Composable
fun ControlButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(10.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Background,
            containerColor = Background
        )
    ) {
        Text(
            label,
            fontSize = 16.sp,
            fontFamily = montserrat_bold,
            color = Color.White
        )
    }
}