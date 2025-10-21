/**
 * Reusable [Button]s.
 */

package com.example.eldercare.common.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



/*@Composable
fun BasicTextButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit) {
    TextButton(onClick = action, modifier = modifier) { Text(text = stringResource(text)) }
}*/

@Composable
fun BasicButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = modifier,
        colors =
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(text = stringResource(text), fontSize = 16.sp)
    }
}

@Composable
fun DialogConfirmButton(@StringRes text: Int, action: () -> Unit) {
    Button(
        onClick = action,
        colors =
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(text = stringResource(text))
    }
}

@Composable
fun DialogCancelButton(@StringRes text: Int, action: () -> Unit) {
    Button(
        onClick = action,
        colors =
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(text = stringResource(text))
    }
}

@Composable
fun ButtonComponent(value: String, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = Modifier
            //.fillMaxWidth()
            .padding(5.dp)
            .heightIn(55.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),

        ) {
        Text(
            text = value,
            modifier = Modifier
                .fillMaxWidth(),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = MaterialTheme.colorScheme.background
            ), textAlign = TextAlign.Center
        )
    }
}

/*@Composable
fun CircularButton(onClick :()->Unit, color:Color){
    OutlinedButton(onClick =onClick,
        modifier =Modifier
            .size(100.dp),
        shape = CircleShape,
        border = BorderStroke(5.dp, MaterialTheme.colorScheme.primary),
        contentPadding = PaddingValues(0.dp),
        //colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.secondary,
            containerColor = color
        )
        )
    {
        Icon(painter = painterResource(id = R.drawable.add), contentDescription = "")
    }
}
*/

