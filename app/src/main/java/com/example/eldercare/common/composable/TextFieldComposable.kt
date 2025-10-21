/**
 * Reusable TextFields.
 */

package com.example.eldercare.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.eldercare.R
import com.example.eldercare.R.string as AppText


@Composable
fun BasicField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier,
    @StringRes text: Int
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onNewValue(it) },
        modifier = modifier,
        placeholder = { Text(stringResource(text)) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = value,
        textStyle = MaterialTheme.typography.labelSmall,
        onValueChange = { onNewValue(it) },
        modifier = modifier
            .padding(5.dp),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.Transparent,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary
        ),
        label = { Text(stringResource(AppText.email)) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.email),
                contentDescription = ""
            )
        }
    )
}

@Composable
fun PasswordField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier)
{
    PasswordField(value, onNewValue, modifier, AppText.password)
}

@Composable
fun RepeatPasswordField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier)
{
    PasswordField(value, onNewValue, modifier, AppText.repeat_password)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier,
    @StringRes placeholder: Int,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onNewValue(it) },
        modifier = modifier
            .padding(5.dp),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.Transparent,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary
        ),
        placeholder = { Text(text = stringResource(placeholder)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.lock),
                contentDescription = ""
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    value: String,
    labelValue: String,
    onNewValue: (String) -> Unit,
    painterResource: Painter,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value,
        { onNewValue(it) },
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.Transparent,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary
        ),
        keyboardOptions = KeyboardOptions.Default,
        leadingIcon = {
            Icon(
                painter = painterResource,
                contentDescription = ""
            )
        }
    )
}

