package com.softsquared.gridge_test.android.instagram_challenge.base_component

import android.content.Context
import android.content.DialogInterface
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.softsquared.gridge_test.android.instagram_challenge.R
import com.softsquared.gridge_test.android.instagram_challenge.custom_view.ViewLoadingDialog
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseActivity<B : ViewBinding> (@LayoutRes val layoutRes : Int) : AppCompatActivity() {
    protected lateinit var binding : B
    protected abstract val viewModel : BaseViewModel?

    private val loadingDialog : ViewLoadingDialog by lazy { ViewLoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preLoad()

        binding = DataBindingUtil.setContentView(this, layoutRes)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel?.isShowLoadingDialog?.collect { showDialog ->
                        if (showDialog) {
                            loadingDialog.show()
                        } else {
                            loadingDialog.dismiss()
                        }
                    }
                }
                launch {
                    viewModel?.networkErrorMessage?.collect { errorMessage ->
                        showSimpleToastMessage(errorMessage)
                    }
                }
            } // repeatOnLifecycle
        } // lifecycleScope.launch
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        currentFocus?.let { view ->
            val rect = Rect()
            view.getGlobalVisibleRect(rect)
            ev?.let { motionEvent ->
                if (!rect.contains(motionEvent.x.toInt(), motionEvent.y.toInt())) {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                    view.clearFocus()
                }
            }
        }

        return super.dispatchTouchEvent(ev)
    }

    open fun setButton(){}

    open fun setRecyclerView(){}

    open fun preLoad() {}

    fun showSimpleToastMessage(message : String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun create2ButtonDialog(title : String, message : String, positiveWord : String, negativeWord : String, positiveCallback : () -> Unit, negativeCallback : () -> Unit){
        val builder = AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
        builder.setTitle(title).setMessage(message)
        .setPositiveButton(positiveWord) { _, _ ->
            positiveCallback()
        }.setNegativeButton(negativeWord) { _, _ ->
                negativeCallback()
        }
        builder.create().show()
    }
}