package com.example.ocean.domain.model

/**
 * 샘플 데이터 모델
 */
data class OceanItem(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String? = null
)
