package com.gallery.view.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallery.view.domain.model.AnimeData
import com.gallery.view.domain.usecases.GetAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getAnimeUseCase: GetAnimeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchAnimeList()
    }

     private fun fetchAnimeList() {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true) }

            getAnimeUseCase(true).collect { result ->
                result.onSuccess { list ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            animeList = list,
                            error = null,
                            apiSource = ApiSource.GET_ANIME_LIST
                        )
                    }
                }
                result.onFailure {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            animeList = emptyList(),
                            error = it.error
                        )
                    }
                }
            }
        }
    }
}


data class UiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val apiSource: ApiSource? = null,
    val animeList: List<AnimeData> = emptyList(),
)

enum class ApiSource {
    GET_ANIME_LIST,
}
