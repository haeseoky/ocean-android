package com.example.ocean.domain.model

/**
 * 대한민국 주요 도시 데이터 모델
 */
data class CityInfo(
    val id: String,
    val name: String,
    val xCoordinate: Float, // 지도 이미지 상의 x 좌표 (0f ~ 1f 비율)
    val yCoordinate: Float, // 지도 이미지 상의 y 좌표 (0f ~ 1f 비율)
    val famousFood: String,
    val description: String
)
