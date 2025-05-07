package com.example.myapplication.avatar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.avatar.model.AvatarNodeType
import com.example.myapplication.avatar.model.AvatarUIData

class AvatarViewModel : ViewModel() {
    private val _avatarLiveData = MutableLiveData<LinkedHashMap<AvatarNodeType, AvatarUIData>>()
    val avatarLiveData: LiveData<LinkedHashMap<AvatarNodeType, AvatarUIData>> = _avatarLiveData

    init {
        _avatarLiveData.value = LinkedHashMap()
    }

    fun updateNodeData(nodeType: AvatarNodeType, data: AvatarUIData) {
        val currentMap = _avatarLiveData.value ?: LinkedHashMap()
        currentMap[nodeType] = data
        _avatarLiveData.value = currentMap
    }

    fun removeNodeData(nodeType: AvatarNodeType) {
        val currentMap = _avatarLiveData.value ?: return
        currentMap.remove(nodeType)
        _avatarLiveData.value = currentMap
    }

    fun clearAllNodeData() {
        _avatarLiveData.value = LinkedHashMap()
    }
} 