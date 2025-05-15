package com.example.ocean.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ocean.ui.home.HomeScreen
import com.example.ocean.ui.home.HomeViewModel
import com.example.ocean.ui.map.MapScreen
import com.example.ocean.ui.map.MapUiState
import com.example.ocean.ui.map.MapViewModel

/**
 * 앱 네비게이션 경로
 */
sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Map : NavRoutes("map")
}

/**
 * 앱 네비게이션 컴포넌트
 */
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavRoutes.Map.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(NavRoutes.Home.route) {
            val homeViewModel: HomeViewModel = viewModel()
            HomeScreen(
                onItemClick = { item ->
                    navController.navigate(NavRoutes.Map.route)
                }
            )
        }
        
        composable(NavRoutes.Map.route) {
            val mapViewModel: MapViewModel = viewModel()
            val uiState by mapViewModel.uiState.collectAsState()
            
            when (val state = uiState) {
                is MapUiState.Loading -> {
                    // 로딩 표시
                }
                is MapUiState.Success -> {
                    MapScreen(cities = state.cities)
                }
                is MapUiState.Empty -> {
                    // 빈 상태 표시
                }
                is MapUiState.Error -> {
                    // 에러 표시
                }
            }
        }
    }
}
