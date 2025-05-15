package com.example.ocean

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ocean.ui.navigation.AppNavigation
import com.example.ocean.ui.navigation.NavRoutes
import com.example.ocean.ui.theme.OceanTheme

/**
 * 내비게이션 아이템 데이터 클래스
 */
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OceanTheme {
                OceanApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OceanApp() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    
    // 현재 선택된 하단 네비게이션 아이템
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    // 내비게이션 아이템
    val items = listOf(
        NavigationItem(
            title = "지도",
            icon = Icons.Filled.Place,  // Map 대신 Place 아이콘 사용
            route = NavRoutes.Map.route
        ),
        NavigationItem(
            title = "목록",
            icon = Icons.Filled.Home,
            route = NavRoutes.Home.route
        )
    )
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("대한민국 도시 음식 지도") },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                    
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route) {
                                // 백 스택에 쌓이지 않도록 설정
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // 중복 네비게이션 방지
                                launchSingleTop = true
                                // 상태 저장
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        AppNavigation(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OceanAppPreview() {
    OceanTheme {
        OceanApp()
    }
}
