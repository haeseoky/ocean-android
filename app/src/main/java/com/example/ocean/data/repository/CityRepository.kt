package com.example.ocean.data.repository

import com.example.ocean.domain.model.CityInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * 도시 정보를 제공하는 Repository
 */
class CityRepository {
    
    /**
     * 대한민국 주요 도시 정보 목록을 가져옵니다.
     */
    fun getCityInfoList(): Flow<List<CityInfo>> = flow {
        val cityInfoList = listOf(
            CityInfo(
                id = "seoul",
                name = "서울",
                xCoordinate = 0.19f,
                yCoordinate = 0.27f,
                famousFood = "비빔밥",
                description = "다양한 재료를 넣어 비벼 먹는 전통 음식"
            ),
            CityInfo(
                id = "busan",
                name = "부산",
                xCoordinate = 0.63f,
                yCoordinate = 0.52f,
                famousFood = "돼지국밥",
                description = "뼈 육수에 돼지고기를 넣은 국밥"
            ),
            CityInfo(
                id = "incheon",
                name = "인천",
                xCoordinate = 0.15f,
                yCoordinate = 0.29f,
                famousFood = "중화요리",
                description = "중국 이민자들이 발전시킨 다양한 중화요리"
            ),
            CityInfo(
                id = "daegu",
                name = "대구",
                xCoordinate = 0.56f,
                yCoordinate = 0.43f,
                famousFood = "납작만두",
                description = "얇은 피에 속을 넣어 빚은 대구식 만두"
            ),
            CityInfo(
                id = "gwangju",
                name = "광주",
                xCoordinate = 0.35f,
                yCoordinate = 0.47f,
                famousFood = "송정떡갈비",
                description = "소갈비살을 다져 양념한 후 구운 음식"
            ),
            CityInfo(
                id = "daejeon",
                name = "대전",
                xCoordinate = 0.39f,
                yCoordinate = 0.38f,
                famousFood = "성심당 빵",
                description = "유명 베이커리의 대표 튀김소보로와 다양한 빵"
            ),
            CityInfo(
                id = "ulsan",
                name = "울산",
                xCoordinate = 0.66f,
                yCoordinate = 0.49f,
                famousFood = "언양불고기",
                description = "얇게 썬 소고기를 양념해 숯불에 구운 음식"
            ),
            CityInfo(
                id = "jeju",
                name = "제주",
                xCoordinate = 0.45f,
                yCoordinate = 0.63f,
                famousFood = "흑돼지구이",
                description = "제주 특산물인 흑돼지를 구운 음식"
            ),
            CityInfo(
                id = "gangneung",
                name = "강릉",
                xCoordinate = 0.67f,
                yCoordinate = 0.27f,
                famousFood = "초당두부",
                description = "바닷물로 만들어 고소한 맛이 일품인 강릉 특산 두부요리"
            ),
            CityInfo(
                id = "jeonju",
                name = "전주",
                xCoordinate = 0.38f,
                yCoordinate = 0.41f,
                famousFood = "전주비빔밥",
                description = "전통방식으로 만든 육회와 신선한 나물이 들어간 정통 비빔밥"
            )
        )
        
        emit(cityInfoList)
    }
    
    /**
     * 특정 도시 정보를 가져옵니다.
     */
    fun getCityInfo(id: String): Flow<CityInfo?> = flow {
        val cityInfo = getCityInfoList().collect { cityList ->
            val city = cityList.find { it.id == id }
            emit(city)
        }
    }
}
