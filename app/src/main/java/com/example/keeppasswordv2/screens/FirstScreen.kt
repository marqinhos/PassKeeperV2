package com.example.keeppasswordv2.screens

import android.app.Application
import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.keeppasswordv2.database.User
import com.example.keeppasswordv2.navigation.AppScreens
import com.example.keeppasswordv2.views.MainViewModel


@Composable
fun FirstScreen(navController: NavHostController, viewModel: MainViewModel){

    val allUsersName by viewModel.allUsers.observeAsState(listOf())
    // val searchResult by viewModel.searchResults.observeAsState(listOf())


    Scaffold{
        ShowLogIn(navController, viewModel, allUsersName)
    }
}

//@Preview
@Composable
fun Preview (){
    val navController = rememberNavController()

    FirstScreen(navController, MainViewModel(LocalContext.current.applicationContext as Application))
}


/*
@Composable
fun BodyContent(navController: NavHostController, viewModel: MainViewModel, allUserName: List<String>){

    var userName by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    // val onProductTextChange = { text : String -> userName = text }
    // val onQuantityTextChange = { text : String -> userPassword = text }

    // val (showDialog, setShowDialog) =  remember { mutableStateOf(false) }
    val (showDialog2, setShowDialog2) =  remember { mutableStateOf(false) }

    val allInfoByUsername by viewModel.getPasswordByName(userName).observeAsState(listOf())
    val cornerShape: RoundedCornerShape = RoundedCornerShape(20.dp)
    val padding: List<Int> = listOf(30, 30, 200, 10)
    val colorBox: Color = Color.White

    // val passwordByName: List<String> = viewModel.getPasswordByName(userName)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(0.9f)
            .paddingFromBaseline(top = padding[2].dp, bottom = padding[3].dp)
            .padding(start = padding[0].dp, end = padding[1].dp)
            .shadow(15.dp, cornerShape)
            .background(color = colorBox)
            .border(
                width = 3.dp,
                color = Color.Transparent,
                shape = cornerShape
            )
    ) {

        // text to write a user
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text(text = "Enter your username") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            // placeholder = { Text(text = "user name") }
        )

        // text to write a password
        OutlinedTextField(
            value = userPassword,
            onValueChange = { userPassword = it },
            label = { Text(text = "Enter your password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        // Row with buttons to Add account
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            val context = LocalContext.current

            Button(onClick = { /*TODO*/
                // var checkIsInList = false

                if (userName.isNotEmpty() && userPassword.isNotEmpty()){
                    if (userName in allUserName) {

                        val pass = allInfoByUsername[0].userPassword
                        // if fields not empty and username already exits, them check that password match
                        if (userPassword == pass) {
                            // Log In match
                            val msg2send = "/$userName"
                            navController.navigate(route = AppScreens.SecondScreen.route + msg2send)
                            Toast.makeText(context, "Log In OK", Toast.LENGTH_SHORT).show()

                        } else {
                            // If not match the password
                            // setShowDialog(true)
                            Toast.makeText(context, "Password Fail", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(context, "Username not exits", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    // If empty fields
                    Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                    //setShowDialog(true)
                }

            }) {
                Text(text = "Log In")
            }
            // DialogDemo(showDialog, setShowDialog, userName, "")

            Button(onClick = { /*TODO*/

                setShowDialog2(true)
                // navController.navigate(route = AppScreens.FourthScreen.route)

            }) {
                Text(text = "Sign In")

            }
            SignIn(showDialog2, setShowDialog2, viewModel, context, allUserName)



        }

    }

}
*/

@Composable
fun LogoPassKeeper(padding: List<Int>, size: Int = 200,
                   style: TextStyle = MaterialTheme.typography.h3

){
    val sweepAngle = -155f // 10f
    val sweepAngle2 = 150f // 10f
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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = padding[2].dp, bottom = padding[3].dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        // Logo Zone
        Box(modifier = Modifier
            .size(size.dp)
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
            .size(size.dp)
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
                style = style,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                // color = Color(0xFFFF7C65),
                //color = Color(0x770D3144)
                color = Color(0xFFFFFFFF)


            )
        }
        Spacer(modifier = Modifier.height(8.dp))


    }
}



private fun CheckAccount(userName: String, userPassword: String, allInfoByUsername: List<User>,
                         context: Context, navController: NavHostController,
                         allUserName: List<String>
){
    if (userName.isNotEmpty() && userPassword.isNotEmpty()){
        if (userName in allUserName) {

            val pass = allInfoByUsername[0].userPassword
            // if fields not empty and username already exits, them check that password match
            if (userPassword == pass) {
                // Log In match
                val msg2send = "/$userName"
                navController.navigate(route = AppScreens.SecondScreen.route + msg2send)
                Toast.makeText(context, "Log In OK", Toast.LENGTH_SHORT).show()

            } else {
                // If not match the password
                // setShowDialog(true)
                Toast.makeText(context, "Password Fail", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, "Username not exits", Toast.LENGTH_SHORT).show()
        }

    }else{
        // If empty fields
        Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
        //setShowDialog(true)
    }
}

@Composable
fun ShowLogIn(navController: NavHostController,
              viewModel: MainViewModel,
              allUserName: List<String>,
              padding: List<Int> = listOf(20, 20, 50, 20),
              cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
              colorBox: Color = Color.White

){


    var userName by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    val (showDialog2, setShowDialog2) =  remember { mutableStateOf(false) }
    val allInfoByUsername by viewModel.getPasswordByName(userName).observeAsState(listOf())
    val context = LocalContext.current


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

        LogoPassKeeper(padding)

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
            Spacer(modifier = Modifier.height(20.dp))

            // text to write a user
            OutlinedTextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                value = userName,
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                onValueChange = { userName = it },
                shape = cornerShape,
                leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle,
                    contentDescription = "account")
                },
                label = { Text(text = "Enter your username") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                trailingIcon = {
                    if (userName.isNotEmpty()){
                        IconButton(onClick = {
                            userName = ""
                            userPassword = ""
                        }) {
                            Icon(imageVector = Icons.Default.Close,
                                contentDescription = "close")

                        }
                    }
                }


            )

            // text to write a password

            OutlinedTextField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                value = userPassword,
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                shape = cornerShape,
                onValueChange = { userPassword = it },
                leadingIcon = { Icon(imageVector = Icons.Default.Lock,
                    contentDescription = "password" )
                },
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

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.weight(1f))

                //Spacer(modifier = Modifier.width(25.dp))
                Button(modifier = Modifier
                    .align(Alignment.CenterVertically),
                    shape = cornerShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        disabledBackgroundColor = Color.Transparent
                    ),
                    elevation = null,
                    onClick = { /*TODO*/
                        setShowDialog2(true)

                    }

                ) {
                    Text(text = "SIGNUP")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(modifier = Modifier.align(Alignment.CenterVertically),
                    shape = cornerShape,
                    onClick = { /*TODO*/
                        CheckAccount(userName, userPassword, allInfoByUsername,
                            context, navController, allUserName)
                    }

                ) {
                    Text(text = "LOGIN")

                }
                Spacer(modifier = Modifier.weight(0.35f))



            }

            Spacer(modifier = Modifier.height(20.dp))
            SignIn(showDialog2, setShowDialog2, viewModel, context, allUserName)


        }
    }


}


@Composable
fun SignIn(showDialog: Boolean, setShowDialog: (Boolean) -> Unit,
           viewModel: MainViewModel, context: Context, allUserName: List<String>,
           cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp),
           colorBox: Color = Color.White
) {

    if (showDialog) {

        var userName by remember { mutableStateOf("") }
        var userPassword by remember { mutableStateOf("") }

        AlertDialog(
            modifier = Modifier
                .shadow(15.dp, cornerShape)
                .background(color = colorBox)
                .border(
                    width = 3.dp,
                    color = Color.Transparent,
                    shape = cornerShape
                ),
            onDismissRequest = {},
            title = { Text("Add User", modifier = Modifier.padding(bottom = 8.dp)) },
            confirmButton = {

                Button(
                    modifier = Modifier.padding(end = 15.dp, bottom = 10.dp),
                    shape = cornerShape,
                    onClick = {
                        // Change the state to close the dialog

                        if (userName.isNotEmpty() && userPassword.isNotEmpty()){
                            // Save Data
                            if(userName !in allUserName){
                                viewModel.insertUser(User(userName, userPassword))
                                setShowDialog(false)
                                Toast.makeText(context, "Sign In OK", Toast.LENGTH_SHORT).show()


                            }else{
                                Toast.makeText(context, "Username already exits", Toast.LENGTH_SHORT).show()

                            }


                        }else{
                            Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                        }

                    },
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    modifier = Modifier.padding( bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        disabledBackgroundColor = Color.Transparent
                    ),
                    elevation = null,
                    onClick = {
                        // Change the state to close the dialog
                        setShowDialog(false)
                    },
                ) {
                    Text("Dismiss")
                }
            },
            text = {

                var data2Register: List<String> = Text2TakeData()
                userName = data2Register[0]
                userPassword = data2Register[1]
            },
        )
    }
}

@Composable
fun Text2TakeData(cornerShape: RoundedCornerShape = RoundedCornerShape(10.dp)): List<String>{
    var userName by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        OutlinedTextField(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            value = userName,
            onValueChange = { userName = it },
            shape = cornerShape,
            leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle,
                contentDescription = "account")
            },
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


        )

        // text to write a password

        OutlinedTextField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            value = userPassword,
            shape = cornerShape,
            onValueChange = { userPassword = it },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock,
                contentDescription = "password" )
            },
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
    }
    return listOf(userName, userPassword)
}



