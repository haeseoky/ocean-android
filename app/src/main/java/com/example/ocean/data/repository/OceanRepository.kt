package com.example.ocean.data.repository

import com.example.ocean.domain.model.OceanItem
import kotlinx.coroutines.flow.Flow

/**
 * 앱 데이터를 제공하는 Repository 인터페이스
 */
interface OceanRepository {
    /**
     * 도시 아이템 목록을 가져옵니다.
     */
    fun getItems(): Flow<List<OceanItem>>
}
