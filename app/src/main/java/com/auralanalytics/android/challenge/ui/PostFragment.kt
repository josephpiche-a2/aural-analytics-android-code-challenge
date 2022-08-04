package com.auralanalytics.android.challenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.auralanalytics.android.challenge.databinding.FragmentPostBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostFragment : Fragment() {
    private lateinit var binding: FragmentPostBinding

    private val viewModel: PostViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }
}