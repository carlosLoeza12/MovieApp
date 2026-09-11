package com.example.movieapp.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.movieapp.R

@Composable
fun MovieAlertDialog(
    title: String,
    text: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    confirmText: String = stringResource(R.string.movies_screen_dialog_confirm_button),
    isCancelable: Boolean = true
) {

    AlertDialog(
        onDismissRequest = {

            if (isCancelable) onDismiss()
        },
        title = {

            MovieText(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 2
            )
        },
        text = {

            MovieText(
                text = text,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3
            )
        },
        confirmButton = {

            TextButton(onClick = onConfirm) {

                MovieText(
                    text = confirmText,
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}