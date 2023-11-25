package org.sopt.dosopttemplate.follower

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.ServicePool
import org.sopt.dosopttemplate.response.ResponseFollowerDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FollowerViewModel : ViewModel() {

    private var _followerData = MutableLiveData<List<ResponseFollowerDto.FollowerData>?>()
    var followerData = _followerData

    //서버에서 ui 관련 정보 받아오기
    fun loadFollowerData() {
        ServicePool.followerService.follower().enqueue(object : Callback<ResponseFollowerDto> {
            override fun onResponse(
                call: Call<ResponseFollowerDto>,
                response: Response<ResponseFollowerDto>
            ) {
                if (response.isSuccessful) {
                    Log.e("서버 통신 성공", response.body()?.data.toString())
                    val followerList: List<ResponseFollowerDto.FollowerData>? =
                        response.body()?.data
                    _followerData.value = followerList
                }
            }

            override fun onFailure(call: Call<ResponseFollowerDto>, t: Throwable) {
                Log.e("서버 통신 실패", t.message.toString())
            }
        })
    }
}