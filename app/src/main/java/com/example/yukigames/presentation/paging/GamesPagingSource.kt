package com.example.yukigames.presentation.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.yukigames.data.remote.GamesAPI
import com.example.yukigames.data.remote.dto.toGameList
import com.example.yukigames.domain.model.Game

class GamesPagingSource(
    private val api: GamesAPI,
    private val page: String,
    private val dates: String,
    private val ordering: String
) : PagingSource<Int, Game>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = api.getRecentGames(page = nextPageNumber.toString(), dates = dates, ordering = ordering)

            LoadResult.Page(
                data = response.toGameList(),
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (response.results.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            Log.e("GamesPagingSource", "Error in load: ${e.message}", e)
            LoadResult.Error(e)
        }
    }

    //getRefreshKey, en son sayfanın sayfa numarasını döndürür.
    //Eğer sayfa içindeki veri seti değişirse, bu değer, yeni verilerin çekileceği sayfa numarasını belirlemek için kullanılabilir.
    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
