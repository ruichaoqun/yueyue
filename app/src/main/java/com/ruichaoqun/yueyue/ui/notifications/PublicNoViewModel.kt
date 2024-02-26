package com.ruichaoqun.yueyue.ui.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruichaoqun.yueyue.core.model.PublicNo
import com.ruichaoqun.yueyue.core.repository.Result
import com.ruichaoqun.yueyue.core.repository.asResult
import com.ruichaoqun.yueyue.core.repository.publickno.PublicNoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PublicNoViewModel @Inject constructor(val publicNoRepository: PublicNoRepository) : ViewModel() {

    public val publicNoUiState:StateFlow<PublicNoUiState> = publicNoUiState()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PublicNoUiState.Loading
        )

    private fun publicNoUiState():Flow<PublicNoUiState>{
        return publicNoRepository.getPublicNo()
            .asResult()
            .map {
                when(it){
                    is Result.Error -> PublicNoUiState.Error
                    Result.Loading -> PublicNoUiState.Loading
                    is Result.Success -> PublicNoUiState.Success(it.data)
                }
            }
    }

}

sealed interface PublicNoUiState{
    object Loading:PublicNoUiState
    object Error:PublicNoUiState
    data class Success(val list:List<PublicNo>):PublicNoUiState
}

