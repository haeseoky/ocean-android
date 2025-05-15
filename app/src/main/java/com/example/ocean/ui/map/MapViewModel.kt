package com.example.ocean.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ocean.data.repository.CityRepository
import com.example.ocean.domain.model.CityInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * 지도 화면을 위한 ViewModel
 */
class MapViewModel : ViewModel() {
    
    // 실제 프로젝트에서는 의존성 주입을 사용하는 것이 좋습니다.
    private val repository = CityRepository()
    
    // UI 상태
    private val _uiState = MutableStateFlow<MapUiState>(MapUiState.Loading)
    val uiState: StateFlow<MapUiState> = _uiState.asStateFlow()
    
    // 선택된 도시 ID
    private val _selectedCityId = MutableStateFlow<String?>(null)
    val selectedCityId: StateFlow<String?> = _selectedCityId.asStateFlow()
    
    init {
        loadCities()
    }
    
    /**
     * 도시 정보 목록을 로드합니다.
     */
    fun loadCities() {
        viewModelScope.launch {
            _uiState.value = MapUiState.Loading
            
            repository.getCityInfoList()
                .catch { e -> 
                    _uiState.value = MapUiState.Error(e.message ?: "Unknown error occurred")
                }
                .collect { cities ->
                    _uiState.value = if (cities.isEmpty()) {
                        MapUiState.Empty
                    } else {
                        MapUiState.Success(cities)
                    }
                }
        }
    }
    
    /**
     * 도시를 선택합니다.
     */
    fun selectCity(cityId: String?) {
        _selectedCityId.value = cityId
    }
}

/**
 * 지도 화면의 UI 상태를 나타내는 Sealed 클래스
 */
sealed class MapUiState {
    object Loading : MapUiState()
    data class Success(val cities: List<CityInfo>) : MapUiState()
    object Empty : MapUiState()
    data class Error(val message: String) : MapUiState()
}
