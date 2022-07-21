package com.example.keeppasswordv2.screens

import android.content.Context
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.keeppasswordv2.database.User
import com.example.keeppasswordv2.navigation.AppScreens
import com.example.keeppasswordv2.views.MainViewModel
// import com.example.keeppasswordv2.ui.theme.m1


@Composable
fun FourthScreen(navController: NavHostController, viewModel: MainViewModel){

    // Register Screen

    Scaffold(topBar = {
        TopAppBar {
            Text("Add things")
        }
    },
    floatingActionButton = {FloatButton("asd", navController)}
    ) {
        FourthBodyContent(navController, viewModel)
    }

}


@Composable
fun FloatButton(msg2Send: String, navController: NavHostController) {

    FloatingActionButton(
        onClick = { /*TODO*/
            navController.navigate(route = AppScreens.ThirdScreen.route + "/$msg2Send")
        },
        backgroundColor = MaterialTheme.colors.onBackground,
        contentColor = Color.White,
    ){
        Icon(Icons.Filled.Add, contentDescription = "button_add")

    }

}


@Composable
fun FourthBodyContent(navController: NavHostController, viewModel: MainViewModel){
    Column(modifier = Modifier.background(Color.Cyan)) {
        Text(text = "Hola")
    }
}



@Composable
fun PassKeeperLogo() {
    val sweepAngle = -155f // -10f
    val sweepAngle2 = 150f // 15f
    val animationDuration = 1000
    val animationDuration2 = 1000

    val plusAnimationDuration = 300
    val animationDelay = 100
    val animationDelay2 = 100

    var animationPlayed by remember {
        mutableStateOf(false)
    }
    var animationPlayed2 by remember {
        mutableStateOf(false)
    }
    var plusAnimationPlayed by remember {
        mutableStateOf(false)
    }
    var plusAnimationPlayed2 by remember {
        mutableStateOf(false)
    }

    val Background = Color(0xFF111D52)
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

    val currentPercent = animateFloatAsState(
        targetValue = if (animationPlayed) sweepAngle else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay,
            easing = FastOutLinearInEasing
        ),
        finishedListener = {
            plusAnimationPlayed = true
        }
    )

    val currentPercent2 = animateFloatAsState(
        targetValue = if (animationPlayed2) sweepAngle2 else 0f,
        animationSpec = tween(
            durationMillis = animationDuration2,
            delayMillis = animationDelay2,
            easing = FastOutLinearInEasing
        ),
        finishedListener = {
            plusAnimationPlayed2 = true
        }
    )


    LaunchedEffect(key1 = true) {
        animationPlayed = true
        animationPlayed2 = true
    }

        Box(modifier = Modifier
            .size(200.dp)
            .background(Color.Transparent)
            .drawBehind {
                drawArc(
                    brush = brush,
                    startAngle = -10f,
                    sweepAngle = currentPercent.value,
                    useCenter = false,
                    style = Stroke(width = 10f, cap = StrokeCap.Round)
                )
            }) { }

        Box(modifier = Modifier
            .size(200.dp)
            .background(Color.Transparent)
            .drawBehind {
                drawArc(
                    brush = brush,
                    startAngle = 15f,
                    sweepAngle = currentPercent2.value,
                    useCenter = false,
                    style = Stroke(width = 10f, cap = StrokeCap.Round)
                )
            }) { }

        Row {
            Text(modifier = Modifier
                .align(Alignment.CenterVertically),

                text = "PassKeeper",
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                // color = Color(0xFFFF7C65),
                color = Color(0x770D3144)

            )
        }

}


@Composable
fun ShowLogIn(context: Context,
              padding: List<Int> = listOf(20, 20, 20, 20),
              cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
              colorBox: Color = Color.White): List<String>{


    var userName by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }


    //             .paddingFromBaseline(top = padding[2].dp, bottom = padding[3].dp)
    // Color(0xFFCE93D8),
    //                Color(0xFF9575CD)
    //                MaterialTheme.colors.primary,
    //                MaterialTheme.colors.primaryVariant

    Column(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
            colors = listOf(
                MaterialTheme.colors.primary,
                MaterialTheme.colors.primaryVariant
                )
            )
        )
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
            .padding(
                start = padding[0].dp,
                end = padding[1].dp,
                top = padding[2].dp,
                bottom = padding[3].dp
            )
        ) {

            /*
            Text(
                "PK",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colors.surface,
                modifier = Modifier
                    .rotate(-45f)
                    .align(Alignment.CenterHorizontally)
            )

            Text(modifier = Modifier
                    .align(Alignment.CenterHorizontally),

                text = "PassKeeper",
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                // color = Color(0xFFFF7C65),
                color = Color(0x770D3144)

            )

             */
            PassKeeperLogo()
            Spacer(modifier = Modifier.height(8.dp))


        }
        
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = padding[0].dp,
                end = padding[1].dp,
                top = padding[2].dp,
                bottom = padding[3].dp
            )
            .shadow(15.dp, cornerShape)
            .background(color = colorBox)
            .border(
                width = 3.dp,
                color = Color.Transparent,
                shape = cornerShape
            )

        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // text to write a user
            OutlinedTextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                value = userName,
                onValueChange = { userName = it },
                shape = cornerShape,
                leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle,
                    contentDescription = "account")},
                label = { Text(text = "Enter your username") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                trailingIcon = {
                    if (userName.isNotEmpty()){
                        IconButton(onClick = { userName = ""}) {
                            Icon(imageVector = Icons.Default.Close,
                                contentDescription = "close")

                        }
                    }
                }
                // placeholder = { Text(text = "user name") }

            )

            // text to write a password

            OutlinedTextField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                value = userPassword,
                shape = cornerShape,
                onValueChange = { userPassword = it },
                leadingIcon = { Icon(imageVector = Icons.Default.Lock,
                    contentDescription = "password" )},
                label = { Text(text = "Enter your password") },
                visualTransformation = if (showPassword) VisualTransformation.None
                                        else PasswordVisualTransformation(),

                trailingIcon = {
                    val image: ImageVector = if (showPassword) Icons.Filled.Visibility
                                else Icons.Filled.VisibilityOff

                    val description: String = if (showPassword) "Hide password"
                                        else "Show password"

                    IconButton(onClick = { showPassword = !showPassword}) {
                        Icon(imageVector = image,
                            contentDescription = description)

                    }

                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            
            Button(modifier = Modifier
                .align(Alignment.CenterHorizontally),
                shape = cornerShape,
                onClick = { /*TODO*/ }

            ) {
                Text(text = "LOGIN")
                // Text2Button("LOGIN", Color.Transparent)

            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(modifier = Modifier
                .align(Alignment.CenterHorizontally),
                shape = cornerShape,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    disabledBackgroundColor = Color.Transparent
                ),
                elevation = null,
                onClick = { /*TODO*/ }

            ) {
                Text(text = "SIGNUP")
                // Text2Button("SIGNUP", Color.White)
            }

            Spacer(modifier = Modifier.height(8.dp))


        }
    }

    return listOf()
}

@Composable
fun Text2Button(text: String, colorBack: Color){

    Text(modifier = Modifier
        .background(color = colorBack),

        text = text)

}


@Preview(showBackground = true)
@Composable
fun PreviewMy(){
    ShowLogIn(context = LocalContext.current)
}


































