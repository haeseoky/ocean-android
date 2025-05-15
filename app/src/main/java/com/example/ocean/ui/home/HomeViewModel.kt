package com.example.ocean.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ocean.data.repository.OceanRepository
import com.example.ocean.data.repository.OceanRepositoryImpl
import com.example.ocean.domain.model.OceanItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * 홈 화면을 위한 ViewModel
 */
class HomeViewModel : ViewModel() {
    
    // 실제 프로젝트에서는 의존성 주입을 사용하는 것이 좋습니다.
    private val repository: OceanRepository = OceanRepositoryImpl()
    
    // UI 상태
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    init {
        loadItems()
    }
    
    /**
     * 아이템 목록을 로드합니다.
     */
    fun loadItems() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            
            repository.getItems()
                .catch { e -> 
                    _uiState.value = HomeUiState.Error(e.message ?: "Unknown error occurred")
                }
                .collect { items ->
                    _uiState.value = if (items.isEmpty()) {
                        HomeUiState.Empty
                    } else {
                        HomeUiState.Success(items)
                    }
                }
        }
    }
}

/**
 * 홈 화면의 UI 상태를 나타내는 Sealed 클래스
 */
sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val items: List<OceanItem>) : HomeUiState()
    object Empty : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
