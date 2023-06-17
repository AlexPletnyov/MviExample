package com.example.mviexample.domain

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mviexample.databinding.FragmentGetCoinsBinding
import com.example.mviexample.domain.mvi.action.CoinAction
import com.example.mviexample.presentation.AppLogger
import com.example.mviexample.presentation.MainViewModel
import com.example.mviexample.presentation.MviExampleApplication
import com.example.mviexample.presentation.ViewModelFactory
import javax.inject.Inject

class GetCoinsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var logger: AppLogger

    private val component by lazy {
        (requireActivity().application as MviExampleApplication).component
    }

    private val viewModel by activityViewModels<MainViewModel> {
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

        viewModel.onAction(CoinAction.GetTopCoinAction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}