package com.example.ocean.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ocean.domain.model.OceanItem

/**
 * 대한민국 도시 음식 정보 화면
 * 
 * 이 화면은 대한민국의 주요 도시들과 그 도시들의 유명한 음식에 대한 정보를 목록으로 보여줍니다.
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onItemClick: (OceanItem) -> Unit = {}
) {
    // 샘플 데이터
    val items = listOf(
        OceanItem(
            id = "seoul",
            title = "서울",
            description = "비빔밥 - 다양한 재료를 넣어 비벼 먹는 전통 음식"
        ),
        OceanItem(
            id = "busan",
            title = "부산",
            description = "돼지국밥 - 뼈 육수에 돼지고기를 넣은 국밥"
        ),
        OceanItem(
            id = "incheon",
            title = "인천",
            description = "중화요리 - 중국 이민자들이 발전시킨 다양한 중화요리"
        ),
        OceanItem(
            id = "daegu",
            title = "대구",
            description = "납작만두 - 얇은 피에 속을 넣어 빚은 대구식 만두"
        ),
        OceanItem(
            id = "gwangju",
            title = "광주",
            description = "송정떡갈비 - 소갈비살을 다져 양념한 후 구운 음식"
        ),
        OceanItem(
            id = "daejeon",
            title = "대전",
            description = "성심당 빵 - 유명 베이커리의 대표 튀김소보로와 다양한 빵"
        ),
        OceanItem(
            id = "ulsan",
            title = "울산",
            description = "언양불고기 - 얇게 썬 소고기를 양념해 숯불에 구운 음식"
        ),
        OceanItem(
            id = "jeju",
            title = "제주",
            description = "흑돼지구이 - 제주 특산물인 흑돼지를 구운 음식"
        )
    )
    
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ItemsList(
            items = items,
            onItemClick = onItemClick
        )
    }
}

@Composable
private fun ItemsList(
    items: List<OceanItem>,
    onItemClick: (OceanItem) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            OceanItemCard(
                item = item,
                onClick = { onItemClick(item) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OceanItemCard(
    item: OceanItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
