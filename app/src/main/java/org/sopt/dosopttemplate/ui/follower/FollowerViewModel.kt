package org.sopt.dosopttemplate.ui.follower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.model.Follower
import org.sopt.dosopttemplate.domain.repository.FollowerRepository
import timber.log.Timber


class FollowerViewModel(val followerRepository: FollowerRepository) : ViewModel() {

    private val _followerList = MutableLiveData<List<Follower>>(listOf())
    val followerList: LiveData<List<Follower>> get() = _followerList

    fun loadFollowerData() {
        viewModelScope.launch {
            kotlin.runCatching {
                followerRepository.loadFollowerData()
                    .onSuccess { data ->
                        _followerList.value = data
                    }
                    .onFailure {
                        Timber.d("서버 통신 실패")
                    }
            }
        }
    }
}
