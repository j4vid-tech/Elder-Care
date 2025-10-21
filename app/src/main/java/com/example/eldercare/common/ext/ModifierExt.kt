/**
 * Useful extensions to the Modifier class.
 */

package com.example.eldercare.common.ext

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.textButton(): Modifier {
  return this.fillMaxWidth().padding(16.dp, 8.dp, 16.dp, 0.dp)
}

fun Modifier.basicButton(): Modifier {
  return this.fillMaxWidth().padding(16.dp, 8.dp)
}

fun Modifier.fieldModifier(): Modifier {
  return this.fillMaxWidth().padding(16.dp, 4.dp)
}
