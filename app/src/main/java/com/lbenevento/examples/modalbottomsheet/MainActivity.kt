package com.lbenevento.examples.modalbottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.*
import com.lbenevento.examples.modalbottomsheet.ui.theme.ModalBottomSheetTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ModalBottomSheetTheme {
                ProvideWindowInsets {
                    Content()
                }
            }
        }

    }
}

@ExperimentalMaterialApi
@Composable
fun Content() {

    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )


    ModalBottomSheetLayout(
        sheetContent = { SheetContent(Modifier.navigationBarsWithImePadding()) },
        sheetState = sheetState,
        sheetBackgroundColor = Color.Black
    ) {
        Scaffold(
            topBar = { Toolbar() }
        ) {
            MainContent(
                onClick = { coroutineScope.launch { sheetState.show() } },
                modifier = Modifier.padding(it)
            )
        }
    }
}


@Composable
fun Toolbar() {
    TopAppBar(
        modifier = Modifier.border(BorderStroke(1.dp, Color.Red))
    ) {
        Text("ModalBottomSheet")
    }
}

@Composable
fun SheetContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Red))
    ) {
        Text(
            text = "The StatusBar looks not that terrible anymore!",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4
        )
    }
}

@Composable
fun MainContent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .border(BorderStroke(2.dp, Color.Green))
    ) {
        Button(
            onClick = onClick
        ) {
            Text(
                text = "SHOW SHEET",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4.copy(color = Color.White)
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ModalBottomSheetTheme {
        Content()
    }
}