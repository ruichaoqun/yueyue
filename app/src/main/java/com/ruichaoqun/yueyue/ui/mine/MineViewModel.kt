package com.ruichaoqun.yueyue.ui.mine

import androidx.lifecycle.ViewModel
import com.ruichaoqun.yueyue.core.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MineViewModel @Inject constructor(val userRepository: UserRepository): ViewModel() {
    val userinfo = userRepository.getUserInfo()

    fun init() {

    }
}