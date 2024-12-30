package com.example.numberpuzzel

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberpuzzel.ui.theme.NumberPuzzelTheme

var win_player = mutableStateOf<String>("")

//var data_list = mutableStateListOf<String>("3", "", "5", "8", "2", "4", "1", "7", "6")
var data_list = mutableStateListOf<String>("1", "2", "3", "4", "5", "6", "7", "", "8")

class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumberPuzzelTheme {
                Scaffold(topBar = {
                    CenterAlignedTopAppBar(actions = {
                        Image(painter = painterResource(R.drawable.restart),
                            contentDescription = null,
                            modifier = Modifier

                                .clickable {
                                    win_player.value = " "
                                    clickable.value =true
                                    data_list.shuffle()
                                })

                    },
                        title = { Text(text = "Number Puzzel", color =  Color(0xFF8F1DA2), fontSize = 40.sp)
                         },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray)

                    )
                })

                { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFA1662F))
                            .padding(paddingValues = innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                        ) { }
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .border(5.dp, Color(0xFFA1662F))
                        ) {
                            P(scope = this, a = 0)
                            P(scope = this, a = 1)
                            P(scope = this, a = 2)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .border(5.dp, Color(0xFFA1662F))
                        ) {
                            P(scope = this, a = 3)
                            P(scope = this, a = 4)
                            P(scope = this, a = 5)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .border(5.dp, Color(0xFFA1662F))
                        ) {
                            P(scope = this, a = 6)
                            P(scope = this, a = 7)
                            P(scope = this, a = 8)
                        }
                        Column(
                            modifier = Modifier

                                .weight(1f)
                        ) {


                            Text(text = win_player.value, fontSize = 60.sp, color = Color.Yellow,
                                fontFamily = FontFamily.Monospace)
                        }
                    }
                }
            }
        }
    }
}

var temp = "0"
var n = 0
var clickable = mutableStateOf(true)
@Composable
fun P(scope: RowScope, color: Color = Color.Black, a: Int = 0) {
    scope.apply {
        Box(modifier = Modifier
            .clickable(enabled = clickable.value) {
                try {

                    when (a) {
                        0 -> {
                            swap(0, 1)
                            swap(0, 3)
                        }

                        1 -> {
                            swap(1, 0)
                            swap(1, 2)
                            swap(1, 4)
                        }

                        2 -> {
                            swap(2, 1)
                            swap(2, 5)
                        }

                        3 -> {
                            swap(3, 0)
                            swap(3, 4)
                            swap(3, 6)
                        }

                        4 -> {
                            swap(4, 1)
                            swap(4, 3)
                            swap(4, 5)
                            swap(4, 7)
                        }

                        5 -> {
                            swap(5, 2)
                            swap(5, 4)
                            swap(5, 8)
                        }

                        6 -> {
                            swap(6, 3)
                            swap(6, 7)

                        }

                        7 -> {
                            swap(7, 8)
                            swap(7, 4)
                            swap(7, 6)
                        }

                        8 -> {
                            swap(8, 7)
                            swap(8, 5)
                        }


                    }
                    for (i in 0 until data_list.size-1) {
                        if (data_list[i] != "${i + 1}") throw Exception("Not Win")

                    }
                    win_player.value = "Win"
                    clickable.value = false
                    
                } catch (e: Exception) {
                    print(e.message)
                    Log.d("========", "onCreate: Not Win")
                }
            }
            .fillMaxSize()
            .weight(1f)
            .background(Color(0xFF623B07))
            .border(5.dp, Color(0xFFA1662F)),
            contentAlignment = Alignment.Center

        ) {
            Text(text = data_list[a], fontSize = 90.sp, color = Color(0xFFA1662F))
        }
    }
}

fun swap(p: Int, p1: Int) {

    if (data_list[p1].isEmpty()) {
        data_list[p1] = data_list[p]
        data_list[p] = ""
    }
}



