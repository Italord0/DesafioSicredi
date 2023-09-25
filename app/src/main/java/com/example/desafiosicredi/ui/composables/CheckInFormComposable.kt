package com.example.desafiosicredi.ui.composables

import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.desafiosicredi.R

@Composable
fun CheckInFormComposable(
    modifier: Modifier = Modifier,
    onFormCompleted: (name: String, email: String) -> Unit
) {

    var nameText by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }

    var isNameValid by remember { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(16.dp)
    ) {

        Text(text = stringResource(id = R.string.checkin), fontSize = 32.sp)

        OutlinedTextField(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            value = nameText,
            onValueChange = {
                if (it.length <= 100) {
                    nameText = it
                    isNameValid = it.isNotBlank()
                }
            },
            label = { Text(stringResource(id = R.string.name)) },
            isError = !isNameValid,
            maxLines = 1
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            value = emailText,
            onValueChange = {
                if (it.length <= 100) {
                    emailText = it
                    isEmailValid = EMAIL_ADDRESS.matcher(it).matches()
                }
            },
            label = { Text(stringResource(id = R.string.email)) },
            isError = !isEmailValid,
            maxLines = 1
        )

        Button(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            onClick = {
                onFormCompleted(nameText, emailText)
            },
            enabled = isNameValid && isEmailValid
        ) {
            Text(text = stringResource(id = R.string.do_checkin))
        }
    }
}

@Preview
@Composable
private fun CheckInFormComposablePreview() {
    CheckInFormComposable(
        modifier = Modifier.background(Color.White)
    ) { _, _ ->

    }
}
