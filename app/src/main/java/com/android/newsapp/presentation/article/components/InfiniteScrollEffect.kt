package com.android.newsapp.presentation.article.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@OptIn(FlowPreview::class)
@Composable
fun InfiniteScrollEffect(
    listState: LazyListState,
    buffer: Int,
    onLoadMore: () -> Unit
) {
    LaunchedEffect(listState) {
        snapshotFlow {
            val layoutInfo = listState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisibleIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
            lastVisibleIndex >= totalItems - buffer
        }
            .distinctUntilChanged()
            .filter { it }
            .debounce(300)
            .collect { onLoadMore() }
    }
}