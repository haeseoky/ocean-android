package com.example.ocean.ui.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ocean.R
import kotlinx.coroutines.delay

/**
 * 인트로/스플래시 화면 구현
 * @param onNavigateToMain 인트로 화면이 끝나고 메인 화면으로 이동하기 위한 콜백
 */
@Composable
fun SplashScreen(
    onNavigateToMain: () -> Unit
) {
    // 애니메이션 상태 관리
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1500),
        label = "splash_alpha_animation"
    )
    
    // 스플래시 화면이 표시된 후 메인 화면으로 이동
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000) // 3초 동안 스플래시 화면 표시
        onNavigateToMain()
    }
    
    // 스플래시 화면 UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3E7DFF)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaAnim.value),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 로고 (아이콘, 텍스트 버전)
            Icon(
                painter = painterResource(id = R.drawable.soso_factory_icon),
                contentDescription = "Soso Factory Logo",
                modifier = Modifier.size(120.dp),
                tint = Color.Unspecified
            )
            
            // 앱 이름
            Text(
                text = "Soso Factory",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            
            // 부제목
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "한국 도시 음식 지도",
                color = Color.White,
                fontSize = 16.sp,
                letterSpacing = 1.sp
            )
        }
    }
}
