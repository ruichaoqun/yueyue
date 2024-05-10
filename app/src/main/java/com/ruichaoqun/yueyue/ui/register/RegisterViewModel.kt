package com.ruichaoqun.yueyue.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruichaoqun.yueyue.R
import com.ruichaoqun.yueyue.core.repository.Result
import com.ruichaoqun.yueyue.core.repository.asResult
import com.ruichaoqun.yueyue.core.repository.user.UserRepository
import com.ruichaoqun.yueyue.ui.navigation.NavigationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> = _registerUiState

    private val registerEvent: MutableSharedFlow<RegisterEvent> = MutableSharedFlow()

    val registerRequestUiState: Flow<RegisterRequestUiState> =
        registerEvent.flatMapConcat { event ->
            userRepository.register(event.username, event.password, event.repassword).asResult()
                .map { result ->
                    when (result) {
                        is Result.Error -> RegisterRequestUiState(isLoading = false, errorMsg = result.errorMsg)
                        Result.Loading -> RegisterRequestUiState(isLoading = true)
                        is Result.Success -> {
                            userRepository.saveUserInfo(result.data)
                            RegisterRequestUiState(isLoading = false)
                        }
                    }
                }
        }


    fun registerDataChanged(username: String, password: String, repassword: String) {
        _registerUiState.update {
            if (!isUserNameValid(username)) {
                RegisterUiState(userNameError = R.string.invalid_username)
            } else if (!isPasswordValid(password)) {
                RegisterUiState(passWordError = R.string.invalid_password)
            } else if (!isRePasswordValid(password, repassword)) {
                RegisterUiState(rePassWordError = R.string.invalid_repassword)
            } else {
                RegisterUiState(isDataValid = true)
            }
        }
    }

    private fun isRePasswordValid(password: String, repassword: String): Boolean =
        password.contentEquals(repassword)

    private fun isUserNameValid(username: String): Boolean =
        username.isNotBlank() and (username.length > 6)

    private fun isPasswordValid(password: String): Boolean =
        password.isNotBlank() and (password.length > 6)

    fun register(username: String, password: String, repassword: String) {
        viewModelScope.launch {
            registerEvent.emit(RegisterEvent(username, password, repassword))
        }
    }
}

data class RegisterUiState(
    val userNameError: Int? = null,
    val passWordError: Int? = null,
    val rePassWordError: Int? = null,
    val isDataValid: Boolean = false,
    )

data class RegisterRequestUiState(
    val isLoading: Boolean = false,
    val registerSuccess: String? = null,
    val errorMsg: String? = null,
)

data class RegisterEvent(val username: String, val password: String, val repassword: String)