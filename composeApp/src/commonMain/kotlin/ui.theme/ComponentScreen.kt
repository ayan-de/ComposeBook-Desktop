package ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComponentScreen(

) {
    val componentViewModel: ComponentViewModel = getViewModel(Unit, viewModelFactory { ComponentViewModel() })

    val pagerState = rememberPagerState {
        tabItemData.size
    }
    LaunchedEffect(componentViewModel.selectedTabIndex) {
        pagerState.animateScrollToPage(componentViewModel.selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            componentViewModel.selectedTabIndex = pagerState.currentPage
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Component(
                    //text = componentViewModel.buttonName,
                    text = "Primary",
                    icon = componentViewModel.iconButton.value
                )
            }
        }
        ScrollableTabRow(
            modifier = Modifier,
            selectedTabIndex = componentViewModel.selectedTabIndex
        ) {
            tabItemData.forEachIndexed { index, tabItem ->
                Tab(
                    selected = index == componentViewModel.selectedTabIndex,
                    onClick = { componentViewModel.selectedTabIndex = index },
                    text = {
                        Text(text = tabItem.title)
                    },
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (componentViewModel.selectedTabIndex) {
                    0 -> TableScreen()
                    1 -> Text(text = tabItemData[index].title)
                    2 -> Text(text = tabItemData[index].title)
                }
            }
        }
    }
}

@Composable
fun Component(text: String, icon: Boolean) {
    //Text(text = "Here Comes the composable ")
    ComponentList(
        onClick = {},
        color = ButtonDefaults.buttonColors(Color.Blue),
        shape = CutCornerShape(10),
        text = text,
        icon = icon
    )
}

