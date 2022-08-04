package com.auralanalytics.android.challenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.auralanalytics.android.challenge.R
import com.auralanalytics.android.challenge.databinding.FragmentListBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding

    private var listingAdapter: ListingAdapter? = null
    private val viewModel: ListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listingAdapter = ListingAdapter { post ->
            findNavController().navigate(R.id.action_listingFragment_to_postFragment)
        }.apply {
            addLoadStateListener {
                viewModel.send(ListViewModel.Action.FromAdapter(it))
            }
        }

        binding.apply {
            toolbar.title = getString(R.string.app_name)
            listingRecyclerView.layoutManager = LinearLayoutManager(context)
            listingRecyclerView.adapter = listingAdapter
        }

        // pull data from the view model when it's available
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.thingList.collectLatest { data ->
                listingAdapter?.submitData(data)
            }
        }

        // setup pull to refresh
        binding.swipeRefresh.setOnRefreshListener {
            listingAdapter?.refresh()
        }

        // react to changes in the state from the view model
        viewModel.state.observe(viewLifecycleOwner) { handleStateChange(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        listingAdapter = null
    }

    private fun handleStateChange(newState: ListViewModel.State) {
        val isRefreshing: Boolean

        when (newState) {
            ListViewModel.State.Initial -> {
                isRefreshing = true
            }
            ListViewModel.State.Loading -> {
                isRefreshing = true
            }
            is ListViewModel.State.Loaded -> {
                isRefreshing = false
            }
            is ListViewModel.State.Error -> {
                isRefreshing = false
            }
        }

        binding.swipeRefresh.isRefreshing = isRefreshing
    }
}