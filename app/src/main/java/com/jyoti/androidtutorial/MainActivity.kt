package com.jyoti.androidtutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.jyoti.androidtutorial.home.presentation.HomeScreen
import com.jyoti.androidtutorial.home.presentation.HomeViewModel
import com.jyoti.androidtutorial.ui.theme.AndroidTutorialTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://5c3cad68-1819-4e97-bdf3-6cc57a521e18.mock.pstmn.io")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()


        setContent {
            AndroidTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen(homeViewModel)
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    AndroidTutorialTheme {
//        HomeScreen()
//    }
//}