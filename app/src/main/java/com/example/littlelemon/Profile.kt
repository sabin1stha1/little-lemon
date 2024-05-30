package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun Profile(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(USER_PROFILE, Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString(FIRST_NAME, "N/A")
    val lastName = sharedPreferences.getString(LAST_NAME, "N/A")
    val email = sharedPreferences.getString(EMAIL, "N/A")


    Column(
        modifier = Modifier.fillMaxSize().
        padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Profile Logo",
            modifier = Modifier.padding(top = 16.dp)
                    .height(60.dp)
                .width(220.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = " Personal information  ",
            style = typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text("First Name: $firstName", modifier = Modifier.padding(top = 8.dp),
            style = typography.bodyMedium)
        Text("Last Name: $lastName", modifier = Modifier.padding(top = 8.dp),
            style = typography.bodyMedium)
        Text("Email: $email", modifier = Modifier.padding(top = 8.dp),
            style = typography.bodyMedium)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = LittleLemonColor.yellow
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 16.dp),
                border = BorderStroke(2.dp, LittleLemonColor.pink),
                shape = RoundedCornerShape(20),
                onClick = {
                    sharedPreferences.edit().clear().apply()

                    navController.navigate(OnboardingDestination.route) {
                        popUpTo(OnboardingDestination.route) { inclusive = true }
                    }
                },
            ) {
                Text(
                    "Log out",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val navController = rememberNavController()
    Profile(navController)
}
