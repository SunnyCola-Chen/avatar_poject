package com.example.avatar_api.model

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class AvatarViewModel : ViewModel() {
    private val _avatarLiveData = MutableLiveData<MutableMap<AvatarNodeType, AvatarUIData>>()
    val avatarLiveData: LiveData<MutableMap<AvatarNodeType, AvatarUIData>> = _avatarLiveData

    init {
        _avatarLiveData.value = LinkedHashMap()
    }

    fun updateNodeData(data: MutableMap<AvatarNodeType, AvatarUIData>) {
        _avatarLiveData.value = data
    }

    fun observe(lifecycleOwner: LifecycleOwner, lifecycleObserver: Observer<MutableMap<AvatarNodeType, AvatarUIData>>) {
        avatarLiveData.observe(lifecycleOwner, lifecycleObserver)
    }


    fun clearAllNodeData() {
        _avatarLiveData.value = LinkedHashMap()
    }
} 