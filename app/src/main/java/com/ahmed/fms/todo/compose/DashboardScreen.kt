package com.ahmed.fms.todo.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DashboardScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
           text = "CheckList Dashboard",
           fontSize = 39.sp,
           fontStyle = FontStyle.Italic,
           fontFamily = FontFamily.Cursive,
           fontWeight = FontWeight.ExtraBold,
           modifier = Modifier.padding(top = 8.dp)
       )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 32.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
            ) {
            Spacer(modifier = Modifier
                .background(Color.LightGray)
                .weight(1f)
                .height(2.dp)
            )
            Text(
                text = "X",
                modifier = Modifier.weight(.5f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Gray
                )
            Spacer(modifier = Modifier
                .background(Color.LightGray)
                .weight(1f)
                .height(2.dp)
            )
        }
        LazyColumn {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}