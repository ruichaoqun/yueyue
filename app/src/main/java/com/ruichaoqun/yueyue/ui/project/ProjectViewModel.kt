package com.ruichaoqun.yueyue.ui.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruichaoqun.yueyue.core.model.ProjectItemBean
import com.ruichaoqun.yueyue.core.repository.Result
import com.ruichaoqun.yueyue.core.repository.asResult
import com.ruichaoqun.yueyue.core.repository.project.ProjectDataRepository
import com.ruichaoqun.yueyue.ui.home.HomeBannerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(private val projectDataRepository: ProjectDataRepository):ViewModel() {

    val projectUiState:StateFlow<ProjectUiState> = projectUiState()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProjectUiState.Loading
        )

    private fun projectUiState(): Flow<ProjectUiState> {
        return projectDataRepository.getProject().asResult()
            .map {
                when(it) {
                    is Result.Error -> ProjectUiState.Error
                    Result.Loading -> ProjectUiState.Loading
                    is Result.Success -> ProjectUiState.Success(it.data)
                }
            }
    }
}

sealed interface ProjectUiState{
    object Loading:ProjectUiState

    object Error:ProjectUiState
    data class Success(val projects:List<ProjectItemBean>):ProjectUiState
}