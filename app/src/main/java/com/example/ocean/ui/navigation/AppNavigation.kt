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
import com.example.ocean.ui.splash.SplashScreen

/**
 * 앱 네비게이션 경로
 */
sealed class NavRoutes(val route: String) {
    object Splash : NavRoutes("splash")
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
    startDestination: String = NavRoutes.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(NavRoutes.Splash.route) {
            SplashScreen(
                onNavigateToMain = {
                    // 스플래시 화면 이후 지도 화면으로 이동
                    navController.navigate(NavRoutes.Map.route) {
                        // 스플래시 화면은 백 스택에서 제거 (뒤로가기 시 스플래시로 가지 않도록)
                        popUpTo(NavRoutes.Splash.route) { inclusive = true }
                    }
                }
            )
        }

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
