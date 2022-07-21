package com.example.keeppasswordv2.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.keeppasswordv2.database.SaveData
import com.example.keeppasswordv2.navigation.AppScreens
import com.example.keeppasswordv2.views.MainViewModel


@Composable
fun SecondScreen(navController: NavHostController, text: String?,
                 viewModel: MainViewModel){

    // This is the same that if (text == null) msg2Browser = "" else text
    val msg2Browser :String = text ?: ""

    // val allUserNames by viewModel.allUsers.observeAsState(listOf())

    val allDataByFK by viewModel.getAllData(msg2Browser).observeAsState(listOf())


    Scaffold(
        backgroundColor = Color.Transparent,

        topBar = {
            TopAppBar(
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

                Icon(imageVector = Icons.Filled.Logout,
                    contentDescription = "Back on second",
                    modifier = Modifier.clickable {
                        navController.navigate(route = AppScreens.FirstScreen.route)}
                )
                val padding: List<Int> = listOf(20, 20, 50, 20)

                // LogoPassKeeper(padding = padding, size = 80, style = MaterialTheme.typography.h5)
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
        },
        floatingActionButton = {MyFloatingActionButton(msg2Browser, navController)}

    ) {

        SecondBodyContent(allDataByFK, viewModel)
    }
}


@Composable
fun MyFloatingActionButton(msg2Send: String, navController: NavHostController) {

    FloatingActionButton(
        onClick = { /*TODO*/
            navController.navigate(route = AppScreens.ThirdScreen.route + "/$msg2Send")
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
    ){
        Icon(Icons.Filled.Add, contentDescription = "button_add")

    }

}


@Composable
fun SecondBodyContent(allDataByFK: List<SaveData>, viewModel: MainViewModel){
    val listColor2Box:  List<Color> = listOf(Color.Cyan, Color.Yellow, Color.Red, Color.Blue)

    // TrySwipe2Delete(allDataByFK, viewModel)
    try2(allDataByFK, viewModel)

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun try2(list: List<SaveData>, viewModel: MainViewModel){

    LazyColumn{
        itemsIndexed(items=list, key = { _, listItem ->listItem.hashCode()
        }){
                index, item ->
            val state = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissValue.DismissedToStart){
                        // list.remove(item)
                        viewModel.deleteById(item.id)

                    }
                    true
                }
            )

            SwipeToDismiss(state = state, background = {

                val color = when(state.dismissDirection){
                    DismissDirection.StartToEnd -> Color.Transparent
                    DismissDirection.EndToStart -> Color(0xFF9575CD)
                    null -> Color.Magenta
                }

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color = color)
                    .padding(10.dp)) {
                    Icon(imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }

            },
                dismissContent = {DataRow2Show(item, Color(0xFF9A71E2))},

                directions = setOf(DismissDirection.EndToStart)

            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrySwipe2Delete(notesList: List<SaveData>, viewModel: MainViewModel){
    LazyColumn (
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.Transparent)

    ){

        items(notesList) { item->
            val dismissState = rememberDismissState()

            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                // DELETE ELEMENT INTO LIST
                viewModel.deleteById(item.id)
                // notesList.remove(item)
            }
            SwipeToDismiss(
                state = dismissState,
                modifier = Modifier
                    .padding(vertical = Dp(1f))
                    .background(Color.Transparent),
                directions = setOf(
                    DismissDirection.EndToStart
                ),
                dismissThresholds = { direction ->
                    FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                },
                background = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.Default -> Color.White
                            else -> Color.LightGray
                        }
                    )
                    val alignment = Alignment.CenterEnd
                    val icon = Icons.Default.Delete

                    val scale by animateFloatAsState(
                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                    )

                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                            .background(color)
                            .padding(horizontal = Dp(20f)),
                        contentAlignment = alignment
                    ) {
                        Icon(
                            icon,
                            contentDescription = "Delete Icon",
                            modifier = Modifier.scale(scale)
                        )
                    }
                },
                dismissContent = {
                    DataRow2Show(item, Color.Magenta)
                })
            // Divider(Modifier.fillMaxWidth(), Color.DarkGray)
        }
    }
}




@Composable
fun DataRow2Show(data: SaveData, color2Back:  Color){

    Row(

        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(8.dp)
            .background(Color.Transparent)
    ){
        ShowText(data, color2Back)
    }

}

@Composable
fun ShowText(data: SaveData, color2Back:  Color){

    var expanded by remember{ mutableStateOf(false) }

    // var msg2Show: String = "/n${data.saveDescription} " + "/n${data.saveEmail} " + "/n${data.saveUsername} " + "/n${data.savePassword}"
    val listColor: List<Color> = listOf(MaterialTheme.colors.primaryVariant, Color(0xFFFFFFFF))
    val listTypography: List<TextStyle> = listOf(MaterialTheme.typography.h6, MaterialTheme.typography.body1)

    //     .clip(CircleShape)
    //         .shadow(15.dp, CircleShape)

    Column(modifier = Modifier
        .clickable {
            expanded = !expanded
        }
        .shadow(15.dp, RoundedCornerShape(8.dp))
        .background(color = color2Back)
        .border(
            width = 3.dp,
            color = Color.Transparent,
            shape = RoundedCornerShape(5.dp)
        )
    ) {

        Spacer(modifier = Modifier.height(8.dp))
        MyText(
            data.saveDescription,
            listColor,
            listTypography,
            title = "Description : "
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (expanded) {
            // Spacer(modifier = Modifier.height(8.dp))
            MyText(
                text = data.saveEmail,
                color = listColor,
                style = listTypography,
                title = "E-mail : "
            )

            Spacer(modifier = Modifier.height(8.dp))
            MyText(
                text = data.saveUsername,
                color = listColor,
                style = listTypography,
                title = "Username : "
            )

            Spacer(modifier = Modifier.height(8.dp))
            MyText(
                text = data.savePassword,
                color = listColor,
                style = listTypography,
                title = "Password : "
            )
            Spacer(modifier = Modifier.height(8.dp))


        }

    }

}

@Composable
fun MyText(text: String, color: List<Color>, style: List<TextStyle>,
           title: String = "", lines: Int = Int.MAX_VALUE){

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp)
    ){
        Text(title, color = color[0], style = style[0], maxLines = lines)
        Text(text, color = color[1], style = style[1], maxLines = lines)
    }

}



// Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(end = 8.dp)
//                .background(color = listColor2Box[setColor])
//        ) {




