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
                xCoordinate = 0.47f,
                yCoordinate = 0.15f,
                famousFood = "비빔밥",
                description = "다양한 재료를 넣어 비벼 먹는 전통 음식"
            ),
            CityInfo(
                id = "busan",
                name = "부산",
                xCoordinate = 0.7f,
                yCoordinate = 0.68f,
                famousFood = "돼지국밥",
                description = "뼈 육수에 돼지고기를 넣은 국밥"
            ),
            CityInfo(
                id = "incheon",
                name = "인천",
                xCoordinate = 0.42f,
                yCoordinate = 0.15f,
                famousFood = "중화요리",
                description = "중국 이민자들이 발전시킨 다양한 중화요리"
            ),
            CityInfo(
                id = "daegu",
                name = "대구",
                xCoordinate = 0.67f,
                yCoordinate = 0.58f,
                famousFood = "납작만두",
                description = "얇은 피에 속을 넣어 빚은 대구식 만두"
            ),
            CityInfo(
                id = "gwangju",
                name = "광주",
                xCoordinate = 0.41f,
                yCoordinate = 0.64f,
                famousFood = "송정떡갈비",
                description = "소갈비살을 다져 양념한 후 구운 음식"
            ),
            CityInfo(
                id = "daejeon",
                name = "대전",
                xCoordinate = 0.52f,
                yCoordinate = 0.52f,
                famousFood = "성심당 빵",
                description = "유명 베이커리의 대표 튀김소보로와 다양한 빵"
            ),
            CityInfo(
                id = "ulsan",
                name = "울산",
                xCoordinate = 0.73f,
                yCoordinate = 0.63f,
                famousFood = "언양불고기",
                description = "얇게 썬 소고기를 양념해 숯불에 구운 음식"
            ),
            CityInfo(
                id = "jeju",
                name = "제주",
                xCoordinate = 0.53f,
                yCoordinate = 0.8f,
                famousFood = "흑돼지구이",
                description = "제주 특산물인 흑돼지를 구운 음식"
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
