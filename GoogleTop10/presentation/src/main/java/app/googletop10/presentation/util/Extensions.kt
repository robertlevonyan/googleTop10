package app.googletop10.presentation.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import app.googletop10.presentation.R

fun AppCompatActivity.replace(target: Fragment) =
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.frContainer, target)
        .commit()

infix fun <Binding : ViewDataBinding> ViewGroup.inflate(@LayoutRes layoutRes: Int): Binding =
    DataBindingUtil.inflate(LayoutInflater.from(context), layoutRes, this, false)

