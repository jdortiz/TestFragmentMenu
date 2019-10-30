package com.canonicalexamples.testfragmentmenu.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _saveButtonEnabled = MutableLiveData<Boolean>(false)
    val saveButtonEnable: LiveData<Boolean>
        get() = _saveButtonEnabled

    fun nameChanged(name: String) {
        _saveButtonEnabled.value = name.isNotEmpty()
    }
}
