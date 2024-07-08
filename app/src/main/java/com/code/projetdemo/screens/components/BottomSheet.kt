package com.code.projetdemo.screens.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.code.projetdemo.R
import com.code.projetdemo.model.FruitItem
import com.code.projetdemo.ui.theme.greenColor
import com.code.projetdemo.utils.convertDateToSpecificStringFormat


@Composable
fun FruitDetail(
    fruitItem: FruitItem,
    onCloseClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onArchiveClick: () -> Unit
) {


    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = stringResource(id = R.string.fruit_manger),
                style = TextStyle(
                    fontSize = 24.sp,
                    color = greenColor,
                    fontWeight = FontWeight.Bold
                )
            )
            IconButton(onClick = { onCloseClicked.invoke() }) {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "", tint = Color.Black
                )
            }
        }

        Text(
            text = fruitItem.detail,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Black)
        )

        Spacer(modifier = Modifier.height(6.dp))


        SwipeBox(
            onDelete = {
                onDeleteClicked.invoke()
            },
            onArchive = {

                onArchiveClick.invoke()

            },
            modifier = Modifier
                .minimumInteractiveComponentSize()
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Card(
                    modifier = Modifier
                        .wrapContentWidth()
                        .size(220.dp)
                        .padding(8.dp)
                        .align(Alignment.Center),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    )
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(fruitItem.imageResource),
                        contentDescription = fruitItem.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }


        Spacer(modifier = Modifier.width(8.dp))

        val buyAt =
            stringResource(id = R.string.acheteLe) + " " + fruitItem.expirationDate.convertDateToSpecificStringFormat(
                "dd mm yyyy"
            ) + " à la Veile"
        Text(text = buyAt, color = Color.Gray)


        Spacer(modifier = Modifier.height(8.dp))

        SwitchComponent()

        Spacer(modifier = Modifier.height(45.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButon(
                color = Color.Red,
                icon = Icons.Default.Delete,
                buttonCLicked = {},
            )

            IconButon(
                color = greenColor,
                icon = ImageVector.vectorResource(id = R.drawable.fork_and_knife_svgrepo_com),
                buttonCLicked = {})


        }

        Spacer(modifier = Modifier.height(22.dp))

    }
}

@Composable
fun SwitchComponent() {
    var checked by remember { mutableStateOf(true) }

    Row(modifier = Modifier.fillMaxWidth(0.8f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(stringResource(id = R.string.indiqueQuantite))
        Spacer(modifier = Modifier.width(8.dp))
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
    }
}

@Composable
fun IconButon(color: Color, icon: ImageVector, buttonCLicked: () -> Unit) {
    IconButton(modifier =
    Modifier
        .background(color = color, shape = CircleShape)
        .size(80.dp)
        .padding(10.dp), onClick = { buttonCLicked.invoke() }) {
        Icon(icon, contentDescription = "", tint = Color.White)

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SwipeBox(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    onArchive: () -> Unit,
    content: @Composable () -> Unit
) {
    val swipeState = rememberSwipeToDismissBoxState()

    when (swipeState.dismissDirection) {
        SwipeToDismissBoxValue.EndToStart -> {
        }

        SwipeToDismissBoxValue.StartToEnd -> {
        }

        SwipeToDismissBoxValue.Settled -> {
        }
    }

    SwipeToDismissBox(
        modifier = modifier.animateContentSize(),
        state = swipeState,
        backgroundContent = {
            Box(modifier = Modifier.background(color = Color.White)) {
            }
        }
    ) {
        content()
    }

    when (swipeState.currentValue) {
        SwipeToDismissBoxValue.EndToStart -> {
            onDelete()
        }

        SwipeToDismissBoxValue.StartToEnd -> {
            LaunchedEffect(swipeState) {
                onArchive()
                swipeState.snapTo(SwipeToDismissBoxValue.Settled)
            }
        }

        SwipeToDismissBoxValue.Settled -> {
        }
    }
}

