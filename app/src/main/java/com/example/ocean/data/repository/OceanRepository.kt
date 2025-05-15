package com.example.ocean.data.repository

import com.example.ocean.domain.model.OceanItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * 샘플 데이터 레포지토리 인터페이스
 */
interface OceanRepository {
    fun getItems(): Flow<List<OceanItem>>
    suspend fun getItemById(id: String): OceanItem?
}

/**
 * 샘플 데이터 레포지토리 구현체
 */
class OceanRepositoryImpl : OceanRepository {
    
    // 샘플 데이터
    private val dummyItems = listOf(
        OceanItem(
            id = "1",
            title = "Coral Reef",
            description = "Coral reefs are diverse underwater ecosystems."
        ),
        OceanItem(
            id = "2",
            title = "Deep Sea",
            description = "The deep sea contains the deepest parts of the world's oceans."
        ),
        OceanItem(
            id = "3",
            title = "Ocean Current",
            description = "Ocean currents are continuous, directed movements of seawater."
        )
    )
    
    override fun getItems(): Flow<List<OceanItem>> = flow {
        // 실제로는 API 호출이나 DB 쿼리가 여기에 들어갈 수 있습니다.
        emit(dummyItems)
    }
    
    override suspend fun getItemById(id: String): OceanItem? {
        // 실제로는 API 호출이나 DB 쿼리가 여기에 들어갈 수 있습니다.
        return dummyItems.find { it.id == id }
    }
}
