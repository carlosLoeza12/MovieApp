package com.example.movieapp.presentation.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.core.components.MovieText
import com.example.movieapp.core.ui.theme.MovieAppTheme
import com.example.movieapp.core.util.AppDimensions
import com.example.movieapp.core.util.AppDimensions.DimensionX16
import com.example.movieapp.core.util.AppDimensions.DimensionX24
import com.example.movieapp.core.util.AppDimensions.DimensionX8

@Composable
fun LoginScreen(navigationToMovies: () -> Unit){

    LoginContent(navigationToMovies)
}

@Composable
fun LoginContent(navigationToMovies : () -> Unit, isLoading: Boolean = false) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(all = DimensionX16),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(0.5f))

        MovieText(
            text = stringResource(R.string.login_title),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(DimensionX8))

        MovieText(
            text = stringResource(R.string.login_subtitle),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.weight(0.5f))

        Image(
            modifier = Modifier.size(250.dp),
            painter = painterResource(R.drawable.logo_app),
            contentDescription = stringResource(R.string.app_icon_description)
        )

        Spacer(modifier = Modifier.weight(0.5f))

        OutlinedButton(
            onClick = { navigationToMovies() },
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = MaterialTheme.shapes.small,
        ) {

            Image(
                painter = painterResource(R.drawable.logo_google),
                contentDescription = stringResource(R.string.google_icon_description),
                modifier = Modifier.size(DimensionX24)
            )

            Spacer(modifier = Modifier.width(DimensionX16))

            MovieText(
                text = stringResource(R.string.login_button_google),
                modifier = Modifier,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(DimensionX16))

        MovieText(
            text = stringResource(R.string.login_terms_privacy),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@Preview
@Composable
fun LoginScreenPreview() {

    MovieAppTheme {

        LoginContent({})
    }
}