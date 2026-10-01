package com.example.newsfeedsimulator_124140139


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsFeedApp()
        }
    }
}


@Composable
fun NewsFeedApp() {

    val newsViewModel: NewsViewModel =
        viewModel()

    // Ambil data dari StateFlow

    val newsList by
    newsViewModel.news.collectAsState()

    val readCount by
    newsViewModel.readCount.collectAsState()

    // kategori aktif

    var selectedCategory by remember {

        mutableStateOf("Semua")

    }

    // detail berita

    var detail by remember {

        mutableStateOf("")

    }

    Column(

        modifier = Modifier

            .fillMaxSize()

            .padding(16.dp)

    ) {

        Text(

            text = "NEWS FEED SIMULATOR",

            style =
                MaterialTheme.typography.headlineSmall

        )

        Spacer(

            modifier =
                Modifier.height(12.dp)

        )

        // FILTER KATEGORI

        Row(

            horizontalArrangement =
                Arrangement.spacedBy(5.dp)

        ) {

            listOf(

                "Semua",
                "Teknologi",
                "Olahraga"

            ).forEach { category ->

                Button(

                    onClick = {

                        selectedCategory = category

                    }

                ) {
                    Text(category)
                }
            }
        }
        Spacer(

            modifier =
                Modifier.height(10.dp)

        )
        Text(

            text =
                "Berita dibaca : $readCount"

        )
        Spacer(

            modifier =
                Modifier.height(10.dp)

        )

        val filteredNews = newsList

            .filter {

                selectedCategory == "Semua" ||
                        it.category == selectedCategory

            }

            .map {

                it.copy(

                    title = "📰 ${it.title}"

                )

            }

        LazyColumn(

            verticalArrangement =
                Arrangement.spacedBy(8.dp)

        ) {

            items(filteredNews) { news ->

                Card(

                    modifier =
                        Modifier

                            .fillMaxWidth()

                            .clickable {
                                // StateFlow update
                                newsViewModel.addRead()
                                // Coroutine async
                                newsViewModel.getDetail(news) {
                                    detail = it
                                }
                            }
                ) {
                    Column(

                        modifier =
                            Modifier.padding(16.dp)

                    ) {

                        Text(

                            text =
                                news.title,

                            style =
                                MaterialTheme.typography.titleMedium

                        )

                        Spacer(

                            Modifier.height(5.dp)

                        )

                        Text(

                            text =
                                "Kategori : ${news.category}"

                        )

                    }
                }
            }
        }
        Spacer(

            Modifier.height(10.dp)

        )
        if(detail.isNotEmpty()) {

            HorizontalDivider()

            Spacer(

                Modifier.height(8.dp)

            )

            Text(

                text =
                    "Detail Berita",

                style =
                    MaterialTheme.typography.titleMedium

            )

            Text(

                text =
                    detail

            )
        }
    }
}