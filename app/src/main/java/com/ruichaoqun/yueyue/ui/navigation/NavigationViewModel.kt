package com.ruichaoqun.yueyue.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruichaoqun.yueyue.core.model.NavigationBean
import com.ruichaoqun.yueyue.core.repository.Result
import com.ruichaoqun.yueyue.core.repository.asResult
import com.ruichaoqun.yueyue.core.repository.navigation.NavigationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(val navigationRepository: NavigationRepository):ViewModel() {

    val navigationUiState:StateFlow<NavigationUiState> = navigationUiState()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NavigationUiState.Loading
        )

    private fun navigationUiState() =
        navigationRepository.getNavigationList()
            .asResult()
            .map {
                when(it){
                    is Result.Error -> NavigationUiState.Error
                    Result.Loading -> NavigationUiState.Loading
                    is Result.Success -> NavigationUiState.Success(it.data)
                }
            }
}

sealed interface NavigationUiState{
    object Loading:NavigationUiState
    object Error:NavigationUiState
    data class Success(val list:List<NavigationBean>):NavigationUiState
}