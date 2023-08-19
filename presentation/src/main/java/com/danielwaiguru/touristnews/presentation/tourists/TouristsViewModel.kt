package com.danielwaiguru.touristnews.presentation.tourists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.danielwaiguru.touristnews.domain.models.Tourist
import com.danielwaiguru.touristnews.domain.repositories.TouristsRepository
import com.danielwaiguru.touristnews.domain.use_cases.GetTouristsUseCase
import com.danielwaiguru.touristnews.domain.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TouristsViewModel @Inject constructor(
    getTouristsUseCase: GetTouristsUseCase,
    private val touristsRepository: TouristsRepository
) : ViewModel() {
    val touristsUIState = getTouristsUseCase()
        .cachedIn(viewModelScope)
    private val _touristUIState: MutableStateFlow<TouristUIState> = MutableStateFlow(TouristUIState())
    val touristUIState: StateFlow<TouristUIState> = _touristUIState.asStateFlow()

    fun getTouristById(touristId: Int) {
        viewModelScope.launch {
            val result = touristsRepository.getTouristById(touristId)
            _touristUIState.update { currentState ->
                when (result) {
                    is ResultWrapper.Error -> currentState.copy(
                        isLoading = false,
                        errorMessage = result.errorMessage
                    )
                    ResultWrapper.Loading -> currentState.copy(
                        isLoading = true,
                        errorMessage = null
                    )
                    is ResultWrapper.Success -> currentState.copy(
                        isLoading = false,
                        errorMessage = null,
                        tourist = result.value
                    )
                }
            }
        }
    }
}

data class TouristUIState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val tourist: Tourist? = null
)