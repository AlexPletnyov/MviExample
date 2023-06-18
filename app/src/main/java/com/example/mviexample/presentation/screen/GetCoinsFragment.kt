package com.example.mviexample.presentation.screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.mviexample.databinding.FragmentGetCoinsBinding
import com.example.mviexample.presentation.MviExampleApplication
import com.example.mviexample.presentation.common.AppLogger
import com.example.mviexample.presentation.common.UiStatus
import com.example.mviexample.presentation.common.ViewModelFactory
import com.example.mviexample.presentation.mvi.MviView
import javax.inject.Inject

class GetCoinsFragment : Fragment(), MviView<TopCoinsState, TopCoinsSideEffect> {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var logger: AppLogger

    private val component by lazy {
        (requireActivity().application as MviExampleApplication).component
    }

    private val viewModel by activityViewModels<TopCoinsViewModel> {
        viewModelFactory
    }

    private var _binding: FragmentGetCoinsBinding? = null
    private val binding: FragmentGetCoinsBinding
        get() = _binding ?: throw RuntimeException("GetCoinsFragment == null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGetCoinsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bind(viewLifecycleOwner.lifecycleScope, this)
        setClickListeners()
    }

    private fun setClickListeners() {
        with(binding) {
            btnGetCoinInfo.setOnClickListener {
                viewModel.event(TopCoinsEvent.GetTopCoins)
            }
        }
    }

    override fun render(state: TopCoinsState) {
        with(binding) {
            when (state.status) {
                is UiStatus.Success -> tvCoinList.text = state.coinNameList.toString()
                is UiStatus.Error -> tvCoinList.text = state.status.errorDescription
                is UiStatus.Loading -> tvCoinList.text = "Loading..."
                else -> {}
            }
            btnGetCoinInfo.isEnabled = state.status !is UiStatus.Loading
        }
    }

    override fun sideEffect(effect: TopCoinsSideEffect) {
        when (effect) {
            is TopCoinsSideEffect.ShowToast -> {
                Toast.makeText(activity, effect.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}