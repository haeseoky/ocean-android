package com.example.ocean.data.repository

import com.example.ocean.domain.model.OceanItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * OceanRepository 인터페이스 구현체
 */
class OceanRepositoryImpl : OceanRepository {
    
    override fun getItems(): Flow<List<OceanItem>> = flow {
        // 실제 앱에서는 네트워크 또는 로컬 데이터베이스에서 데이터를 가져와야 합니다.
        // 현재는 샘플 데이터를 사용합니다.
        val items = listOf(
            OceanItem(
                id = "seoul",
                title = "서울",
                description = "비빔밥 - 다양한 재료를 넣어 비벼 먹는 전통 음식",
                imageUrl = "https://i.imgur.com/123456.jpg"
            ),
            OceanItem(
                id = "busan",
                title = "부산",
                description = "돼지국밥 - 뼈 육수에 돼지고기를 넣은 국밥",
                imageUrl = "https://i.imgur.com/234567.jpg"
            ),
            OceanItem(
                id = "incheon",
                title = "인천",
                description = "중화요리 - 중국 이민자들이 발전시킨 다양한 중화요리",
                imageUrl = "https://i.imgur.com/345678.jpg"
            ),
            OceanItem(
                id = "daegu",
                title = "대구",
                description = "납작만두 - 얇은 피에 속을 넣어 빚은 대구식 만두",
                imageUrl = "https://i.imgur.com/456789.jpg"
            ),
            OceanItem(
                id = "gwangju",
                title = "광주",
                description = "송정떡갈비 - 소갈비살을 다져 양념한 후 구운 음식",
                imageUrl = "https://i.imgur.com/567890.jpg"
            ),
            OceanItem(
                id = "daejeon",
                title = "대전",
                description = "성심당 빵 - 유명 베이커리의 대표 튀김소보로와 다양한 빵",
                imageUrl = "https://i.imgur.com/678901.jpg"
            ),
            OceanItem(
                id = "ulsan",
                title = "울산",
                description = "언양불고기 - 얇게 썬 소고기를 양념해 숯불에 구운 음식",
                imageUrl = "https://i.imgur.com/789012.jpg"
            ),
            OceanItem(
                id = "jeju",
                title = "제주",
                description = "흑돼지구이 - 제주 특산물인 흑돼지를 구운 음식",
                imageUrl = "https://i.imgur.com/890123.jpg"
            )
        )
        
        emit(items)
    }
}
