package org.sopt.dosopttemplate.ui.follower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.model.Follower
import org.sopt.dosopttemplate.domain.repository.FollowerRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FollowerViewModel @Inject constructor(val followerRepository: FollowerRepository) :
    ViewModel() {

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
