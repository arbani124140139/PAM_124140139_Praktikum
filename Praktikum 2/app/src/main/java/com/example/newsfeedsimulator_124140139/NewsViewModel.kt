package com.example.newsfeedsimulator_124140139

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.async

class NewsViewModel : ViewModel() {

    // Data berita simulasi
    private val newsList = listOf(

        News(
            "AI semakin berkembang di Indonesia",
            "Teknologi",
            "Teknologi AI terus berkembang dan digunakan dalam berbagai bidang."
        ),

        News(
            "Tim nasional memenangkan pertandingan",
            "Olahraga",
            "Tim nasional berhasil meraih kemenangan dalam pertandingan."
        ),
    )

    // STATFLOW jumlah berita dibaca
    private val _readCount =
        MutableStateFlow(0)

    val readCount:
            StateFlow<Int> =
        _readCount.asStateFlow()

    // STATEFLOW daftar berita
    private val _news =
        MutableStateFlow<List<News>>(emptyList())

    val news:
            StateFlow<List<News>> =
        _news.asStateFlow()

    init {

        startNewsFlow()

    }

    // FLOW berita masuk setiap 2 detik
    private fun startNewsFlow() {
        viewModelScope.launch {
            newsFlow()
                .collect { item ->
                    _news.value =
                        _news.value + item
                }
        }
    }

    // Membuat Flow
    private fun newsFlow():
            Flow<News> = flow {
        var index = 0
        while (true) {
            emit(newsList[index])
            index++
            if (index >= newsList.size) {
                index = 0
            }
            delay(2000)
        }
    }

    // Filter + Transform
    fun getNewsByCategory(
        category: String
    ): List<News> {
        return _news.value
            .filter {
                category == "Semua" ||
                        it.category == category
            }
            .map {
                it.copy(
                    title = "📰 ${it.title}"
                )
            }
    }

    // Tambah jumlah berita dibaca
    fun addRead() {
        _readCount.value++
    }

    // Coroutine async ambil detail berita
    fun getDetail(
        news: News,
        result: (String) -> Unit
    ) {
        viewModelScope.launch {
            val detail = async {
                delay(1000)
                news.content
            }
            result(
                detail.await()
            )
        }
    }
}