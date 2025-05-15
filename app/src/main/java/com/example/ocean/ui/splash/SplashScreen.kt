package com.example.ocean.ui.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * 인트로/스플래시 화면 구현
 * @param onNavigateToHome 인트로 화면이 끝나고 홈 화면으로 이동하기 위한 콜백
 */
@Composable
fun SplashScreen(
    onNavigateToHome: () -> Unit
) {
    // 애니메이션 상태 관리
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1500),
        label = "splash_alpha_animation"
    )
    
    // 스플래시 화면이 표시된 후 홈 화면으로 이동
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000) // 2초 동안 스플래시 화면 표시
        onNavigateToHome()
    }
    
    // 스플래시 화면 UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaAnim.value),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 로고 (텍스트 버전)
            Text(
                text = "OCEAN",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 4.sp
            )
            
            // 부제목
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Explore the depths",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                letterSpacing = 1.sp
            )
        }
    }
}
