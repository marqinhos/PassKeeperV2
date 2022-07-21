package com.example.keeppasswordv2.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keeppasswordv2.database.SaveData
import com.example.keeppasswordv2.navigation.AppScreens
import com.example.keeppasswordv2.views.MainViewModel

@Composable
fun ThirdScreen(navController: NavController, text: String?,
                 viewModel: MainViewModel
){

    // This is the same that if (text == null) msg2Browser = "" else text
    val fk :String = text ?: ""

    //  val allUserNames by viewModel.allUsers.observeAsState(listOf())
    // val allDataByFK by viewModel.getAllData(fk).observeAsState(listOf())


    Scaffold(
        backgroundColor = Color.Transparent,
        topBar = {
            TopAppBar (
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.primaryVariant

                            )
                        )
                    )
            ) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back third",
                    modifier = Modifier.clickable {
                        navController.navigate(route = AppScreens.SecondScreen.route + "/$fk")
                    }
                )
                Spacer(Modifier.width(15.dp))
                val GradientColor1 = Color(0xFF0E1956)
                val GradientColor2 = Color(0xFF092474)
                val GradientColor3 = Color(0xFF0170B6)
                val GradientColor4 = Color(0xFF19FAFF)
                val GradientColor5 = Color(0xFFF06292)
                val brush = Brush.linearGradient(
                    0f to GradientColor1,
                    0.2f to GradientColor2,
                    0.35f to GradientColor3,
                    0.45f to GradientColor4,
                    0.95f to GradientColor5,
                )


                Text(text = "PassKeeper",
                    color = Color(0xFFFFFFFF),
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .drawBehind {
                            drawArc(
                                brush = brush,
                                startAngle = 42f,
                                sweepAngle = 100f,
                                useCenter = false,
                                style = (Stroke(width = 3f, cap = StrokeCap.Round))
                            );
                            drawArc(
                                brush = brush,
                                startAngle = -42f,
                                sweepAngle = -100f,
                                useCenter = false,
                                style = (Stroke(width = 3f, cap = StrokeCap.Round))
                            )
                        }

                )
            }
        }) {
        ThirdBodyContent(navController, fk, viewModel)
    }
}

@Composable
fun ThirdBodyContent(navController: NavController, fk: String, viewModel: MainViewModel,
                     cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp)

){

    // Val to read
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // val (showDialog, setShowDialog) =  remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        // Text to write Description
        OutlinedTextField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            value = description,
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            onValueChange = { description = it },
            shape = cornerShape,
            leadingIcon = { Icon(imageVector = Icons.Default.Description,
                contentDescription = "description" )
            },
            label = { Text(text = "Enter Description of Account") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            // placeholder = { Text(text = "user name") }
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Text to write email
        OutlinedTextField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            value = email,
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            onValueChange = { email = it },
            shape = cornerShape,
            leadingIcon = { Icon(imageVector = Icons.Default.Email,
                contentDescription = "email" )
            },
            label = { Text(text = "Enter e-mail of Account") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            // placeholder = { Text(text = "user name") }
        )
        Spacer(modifier = Modifier.height(20.dp))

        // text to write a username
        OutlinedTextField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            value = username,
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            onValueChange = { username = it },
            shape = cornerShape,
            leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle,
                contentDescription = "username" )
            },
            label = { Text(text = "Enter username of Account") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            // placeholder = { Text(text = "user name") }
        )
        Spacer(modifier = Modifier.height(20.dp))

        // text to write a password
        OutlinedTextField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            value = password,
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            onValueChange = { password = it },
            shape = cornerShape,
            leadingIcon = { Icon(imageVector = Icons.Default.Lock,
                contentDescription = "password" )
            },
            label = { Text(text = "Enter password of Account") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Row with buttons to Add account
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            val context = LocalContext.current

            Button(
                shape = cornerShape,
                onClick = { /*TODO*/

                if (description.isNotEmpty() &&
                    email.isNotEmpty() &&
                    username.isNotEmpty() &&
                    password.isNotEmpty()

                ){
                    viewModel.insertData(SaveData(email, username, password, description, fk))
                    // navController.popBackStack()
                    Toast.makeText(context, "Save Data OK", Toast.LENGTH_SHORT).show()
                    navController.navigate(route = AppScreens.SecondScreen.route + "/$fk")

                }else{
                    Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                    // setShowDialog(true)
                    password = ""
                    email = ""
                    username = ""

                }

            }) {
                Text(text = "Add Account")
            }
            // DialogDemo(showDialog, setShowDialog, username, "")

        }

    }

}

