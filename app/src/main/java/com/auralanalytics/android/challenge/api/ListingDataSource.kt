package com.auralanalytics.android.challenge.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.auralanalytics.android.challenge.models.Thing

/**
 * Concrete data source which extends the abstract `PagingSource` to provide
 * results from the `getListing` api call for the appropriate subreddit.
 */
class ListingDataSource(
    private val redditService: RedditService,
    private val subreddit: String,
) : PagingSource<String, Thing>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Thing> {
        return try {
            val response = redditService.getListing(
                subreddit = subreddit,
                after = params.key
            )

            val listing = response.data
            val things: List<Thing> = listing.children.map { it.data }

            LoadResult.Page(
                data = things,
                prevKey = listing.before,
                nextKey = listing.after
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Thing>): String? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
        }
    }
}