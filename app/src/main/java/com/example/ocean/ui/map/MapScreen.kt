package com.example.ocean.ui.map

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ocean.R
import com.example.ocean.domain.model.CityInfo
import kotlin.math.roundToInt

/**
 * 대한민국 지도 화면
 * 
 * 지도 위에 도시 위치를 표시하고, 클릭 시 해당 도시의 유명 음식 정보를 말풍선으로 보여줍니다.
 */
@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    cities: List<CityInfo> = emptyList()
) {
    // 선택된 도시 정보
    var selectedCity by remember { mutableStateOf<CityInfo?>(null) }
    
    // 화면 크기 구하기
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 한국 지도 이미지
        Image(
            painter = painterResource(id = R.drawable.korea_map_image),
            contentDescription = "대한민국 지도",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
        
        // 도시 마커 표시
        cities.forEach { city ->
            CityMarker(
                city = city,
                screenWidth = screenWidth,
                screenHeight = screenHeight,
                onClick = {
                    selectedCity = if (selectedCity?.id == city.id) null else city
                },
                isSelected = selectedCity?.id == city.id
            )
        }
        
        // 선택된 도시가 있으면 말풍선 표시
        selectedCity?.let { city ->
            EnhancedSpeechBubble(
                city = city,
                screenWidth = screenWidth,
                screenHeight = screenHeight
            )
        }
    }
}

/**
 * 도시 마커 컴포넌트
 */
@Composable
private fun CityMarker(
    city: CityInfo,
    screenWidth: androidx.compose.ui.unit.Dp,
    screenHeight: androidx.compose.ui.unit.Dp,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    val markerSize = 24.dp
    val density = LocalDensity.current
    
    val xPos = (screenWidth.value * city.xCoordinate).roundToInt()
    val yPos = (screenHeight.value * city.yCoordinate).roundToInt()
    
    // 마커 크기 애니메이션
    val animatedSize by animateDpAsState(
        targetValue = if (isSelected) markerSize * 1.3f else markerSize,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "MarkerSizeAnimation"
    )
    
    Box(
        modifier = Modifier
            .offset { IntOffset(xPos - (markerSize.value / 2).roundToInt(), yPos - (markerSize.value / 2).roundToInt()) }
            .size(animatedSize)
            .clip(CircleShape)
            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)
            .border(1.dp, Color.White, CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = city.name.take(1),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

/**
 * 향상된 말풍선 컴포넌트
 */
@Composable
private fun EnhancedSpeechBubble(
    city: CityInfo,
    screenWidth: androidx.compose.ui.unit.Dp,
    screenHeight: androidx.compose.ui.unit.Dp
) {
    val xPos = (screenWidth.value * city.xCoordinate).roundToInt()
    val yPos = (screenHeight.value * city.yCoordinate).roundToInt() - 85
    
    // 화면 경계를 벗어나지 않도록 조정
    val bubbleWidth = 200.dp
    val bubbleWidthPx = with(LocalDensity.current) { bubbleWidth.toPx() }
    val adjustedX = xPos.coerceIn(20, screenWidth.value.toInt() - (bubbleWidthPx / 2).roundToInt())
    val adjustedY = yPos.coerceIn(20, screenHeight.value.toInt() - 150)
    
    Box(
        modifier = Modifier
            .offset { IntOffset(adjustedX - (bubbleWidth.value / 2).roundToInt(), adjustedY) }
    ) {
        // 말풍선 애니메이션
        AnimatedVisibility(
            visible = true,
            enter = fadeIn() + scaleIn(initialScale = 0.8f),
            exit = fadeOut()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 말풍선 카드
                Card(
                    modifier = Modifier
                        .width(bubbleWidth)
                        .shadow(4.dp, RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = city.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = city.famousFood,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(
                            text = city.description,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
                
                // 말풍선 꼬리 부분
                Box(
                    modifier = Modifier
                        .size(16.dp, 8.dp)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 50f,
                                bottomEnd = 50f
                            )
                        )
                        .background(Color.White)
                )
            }
        }
    }
}
