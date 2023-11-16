package org.sopt.dosopttemplate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.follower.FollowerAdapter
import org.sopt.dosopttemplate.follower.FollowerViewModel
import org.sopt.dosopttemplate.response.ResponseFollowerDto

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩에러" }

    private val viewModel by viewModels<FollowerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadFollowerData()
        viewModel.followerData.observe(viewLifecycleOwner, Observer {data->
            setAdapter(data)
            Log.d("뷰모델", data.toString())
        })
    }

    private fun setAdapter(followerList: List<ResponseFollowerDto.FollowerData>?){
        Log.e("어댑터 연결 성공",followerList.toString())
        val followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
        followerAdapter.submitList(followerList)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

