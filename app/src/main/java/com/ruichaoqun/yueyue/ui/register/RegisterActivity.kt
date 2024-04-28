package com.ruichaoqun.yueyue.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.ruichaoqun.yueyue.databinding.ActivityRegisterBinding
import com.ruichaoqun.yueyue.ui.login.afterTextChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private val TAG: String = "RegisterActivity"
    private lateinit var mBinding: ActivityRegisterBinding
    val mViewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initView()
        initModel()
    }


    private fun initView() {
        mBinding.username.addTextChangedListener {
            mViewModel.registerDataChanged(
                mBinding.username.text.toString(),
                mBinding.password.text.toString(),
                mBinding.repassword.text.toString()
            )
        }

        mBinding.password.addTextChangedListener {
            mViewModel.registerDataChanged(
                mBinding.username.text.toString(),
                mBinding.password.text.toString(),
                mBinding.repassword.text.toString()
            )
        }

        mBinding.repassword.addTextChangedListener {
            mViewModel.registerDataChanged(
                mBinding.username.text.toString(),
                mBinding.password.text.toString(),
                mBinding.repassword.text.toString()
            )
        }

        mBinding.register.setOnClickListener {
            mViewModel.register(mBinding.username.text.toString(),
                mBinding.password.text.toString(),
                mBinding.repassword.text.toString())
        }


    }

    private fun initModel() {
        lifecycleScope.launch {
            mViewModel.registerUiState.collectLatest {state->
                Log.e(TAG,state.toString())
                if (state.userNameError != null) {
                    mBinding.username.error = getString(state.userNameError)
                }
                if (state.passWordError != null) {
                    mBinding.password.error = getString(state.passWordError)
                }
                if (state.rePassWordError != null) {
                    mBinding.repassword.error = getString(state.rePassWordError)
                }
                mBinding.register.isEnabled = state.isDataValid
            }
        }

        lifecycleScope.launch {
            mViewModel.registerRequestUiState.collectLatest {state->
                mBinding.loading.isVisible = state.isLoading
                state.errorMsg?.let {
                    Toast.makeText(this@RegisterActivity,it,Toast.LENGTH_SHORT)
                }
            }
        }

    }

}