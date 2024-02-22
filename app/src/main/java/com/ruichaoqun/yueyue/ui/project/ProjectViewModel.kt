package com.ruichaoqun.yueyue.ui.project

import androidx.lifecycle.ViewModel
import com.ruichaoqun.yueyue.core.repository.project.ProjectDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(val projectDataRepository: ProjectDataRepository):ViewModel() {

}