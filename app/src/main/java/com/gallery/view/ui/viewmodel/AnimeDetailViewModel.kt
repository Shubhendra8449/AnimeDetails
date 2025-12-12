package com.gallery.view.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.gallery.view.domain.model.AnimeData
import com.gallery.view.domain.usecases.GetAnimeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    private val getAnimeUseCase: GetAnimeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiDetailState())
    val uiState = _uiState.asStateFlow()

    fun fetchAnimeList(animeId: Int) {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true) }

            getAnimeUseCase(false).collect { result ->

                result.onSuccess { list ->

                    val anime = list.find { it.mal_id == animeId }

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            animeData = anime,
                            error = null
                        )
                    }
                }

                result.onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            animeData = null,
                            error = error.message
                        )
                    }
                }
            }
        }
    }
}

data class UiDetailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val animeData: AnimeData? = null,
)


