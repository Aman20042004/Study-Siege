package com.example.studysiege.ui.startTaskScreen.start_task_screen_components.WheelPicker


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs

private const val REPEAT_COUNT = 200

private fun createWheelItems(range: IntRange): List<Int> {
    return List(REPEAT_COUNT) {
        range.toList()
    }.flatten()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WheelPicker(
    value: Int,
    range: IntRange,
    onValueChange: (Int) -> Unit
) {
    val values = remember(range) {
        createWheelItems(range)
    }

    val density = LocalDensity.current
    val valueIndex = remember(value, range) { range.indexOf(value).coerceAtLeast(0) }
    val middle = values.size / 2 - (values.size / 2) % range.count()
    val targetIndex = middle + valueIndex

    // FIX 1: Perfectly center the default value on initial load
    // Box 180.dp, Item 50.dp -> To center it, offset calculation is exactly 35.dp for N-2 item.
    val (initialIndex, initialOffset) = remember(targetIndex, density) {
        if (targetIndex >= 2) {
            targetIndex - 2 to with(density) { 35.dp.toPx().toInt() }
        } else {
            targetIndex to 0
        }
    }

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = initialIndex,
        initialFirstVisibleItemScrollOffset = initialOffset
    )

    val flingBehavior = rememberSnapFlingBehavior(
        lazyListState = listState
    )

    // FIX 2: Find the exact item that is passing through the center of the viewport
    val centralItemIndex by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            if (visibleItems.isEmpty()) return@derivedStateOf -1

            val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2

            val centralItem = visibleItems.minByOrNull { item ->
                abs(item.offset + (item.size / 2) - viewportCenter)
            }
            centralItem?.index ?: -1
        }
    }

    var selectedIndex by remember { mutableIntStateOf(targetIndex) }

    // Update selected index and callback when central item changes
    LaunchedEffect(centralItemIndex) {
        if (centralItemIndex in values.indices) {
            selectedIndex = centralItemIndex
            val newValue = values[centralItemIndex]

            if (newValue != value) {
                onValueChange(newValue)
            }
        }
    }

    Box(
        modifier = Modifier
            .width(80.dp)
            .height(180.dp)
    ) {
        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(vertical = 65.dp)
        ) {
            itemsIndexed(values) { index, item ->
                val selected = index == selectedIndex

                val scale by animateFloatAsState(
                    targetValue = if (selected) 1.25f else 1f,
                    label = "scale"
                )

                val alpha by animateFloatAsState(
                    targetValue = if (selected) 1f else 0.35f,
                    label = "alpha"
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 8.dp)
                        .scale(scale)
                        .alpha(alpha)
                        .background(
                            if (selected) Color(0x221EFF88) else Color.Transparent,
                            RoundedCornerShape(12.dp)
                        )
                        .border(
                            if (selected) 1.5.dp else 0.dp,
                            Color(0xFF00FF88),
                            RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.toString().padStart(2, '0'),
                        color = if (selected) Color(0xFF00FF88) else Color.Gray,
                        fontSize = if (selected) 30.sp else 20.sp,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        ) {
            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFF8A5CFF)
            )
            Spacer(modifier = Modifier.height(48.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFF8A5CFF)
            )
        }
    }
}