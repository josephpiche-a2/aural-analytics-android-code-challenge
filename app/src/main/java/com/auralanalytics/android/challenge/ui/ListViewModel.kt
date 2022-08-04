package com.auralanalytics.android.challenge.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.auralanalytics.android.challenge.api.ListingDataSource
import com.auralanalytics.android.challenge.api.RedditService
import com.auralanalytics.android.challenge.models.Thing
import kotlinx.coroutines.flow.Flow

class ListViewModel(
    private val redditApi: RedditService,
    private val subreddit: String,
): ViewModel() {

    val state = MutableLiveData<State>(State.Initial)

    // this provides a flow data stream of Pages of `Thing`s from the `ListingDataSource`
    val thingList: Flow<PagingData<Thing>> = Pager(PagingConfig(pageSize = 20)) {
        ListingDataSource(redditApi, subreddit)
    }.flow.cachedIn(viewModelScope)

    fun send(action: Action) {
        when (action) {
            Action.Resume -> state.postValue(State.Loading)
            is Action.FromAdapter -> handleCombinedState(action)
        }
    }

    private fun handleCombinedState(action: Action.FromAdapter) {
        for (loadState in listOf(
            action.loadState.refresh,
            action.loadState.append,
            action.loadState.prepend
        )) {
            if (loadState is LoadState.Error) {
                state.postValue(State.Error(loadState.error))
                return
            }
        }

        if (action.loadState.refresh is LoadState.Loading) {
            state.postValue(State.Loading)
            return
        }

        state.postValue(State.Loaded)
    }

    sealed class Action {
        object Resume : Action()
        data class FromAdapter(
            val loadState: CombinedLoadStates
        ): Action()
    }

    sealed class State {
        object Initial : State()
        object Loading : State()
        object Loaded : State()
        data class Error(
            val error: Throwable
        ) : State()
    }
}