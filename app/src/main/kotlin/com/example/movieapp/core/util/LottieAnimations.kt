package com.example.movieapp.core.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnimation(lottieId: Int, modifier: Modifier = Modifier) {

    val composition: LottieComposition? by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieId))

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
    }
}