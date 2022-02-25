package com.example.githubclient

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T: ViewBinding> Fragment.viewBinding() = ViewBindingDelegate(this, T::class.java)

class ViewBindingDelegate<T : ViewBinding>(private val fragment: Fragment, bindingClass: Class<T>) :
    ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {

    private var binding: T? = null
    private val bindMethod = bindingClass.getMethod("bind", View::class.java)

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        if (binding != null) {
            return binding!!
        }
        thisRef.lifecycle.addObserver(this)

        val lifeCycle = this.fragment.viewLifecycleOwner.lifecycle
        if(!lifeCycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Wrong view state: ${lifeCycle.currentState}")
        }
        binding = bindMethod(null, thisRef.requireView()) as T
        return binding!!
    }

    override fun onCreate(owner: LifecycleOwner) {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) {
            it.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    binding = null
                }
            })
        }
    }

}