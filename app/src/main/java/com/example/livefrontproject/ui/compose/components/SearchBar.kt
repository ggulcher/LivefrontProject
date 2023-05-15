package com.example.livefrontproject.ui.compose.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.livefrontproject.R
import com.example.livefrontproject.ui.viewmodel.NewsViewModel

/**
 * Custom search bar for capturing [query] and passing it to [viewModel]
 *
 * Actual search function is tied to button press to avoid excessive network calls
 */
@Composable
fun SearchBar(
    hint: String,
    query: String,
    modifier: Modifier = Modifier,
    state: MutableState<TextFieldValue>,
    viewModel: NewsViewModel
) {

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Box(
            modifier = Modifier
                .border(
                    border = ButtonDefaults.outlinedButtonBorder,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(8.dp)
                .weight(.8f)
        ) {
            BasicTextField(
                value = state.value,
                onValueChange = {
                    state.value = it
                },
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isHintDisplayed = !it.isFocused
                    }
            )

            if (isHintDisplayed) {
                Text(
                    text = hint,
                    color = Color.Gray,
                    modifier = Modifier
                )
            }
        }

        Button(
            modifier = Modifier.weight(.2f),
            onClick = {
                if (query.isNotEmpty()) {
                    viewModel.getSearchedItems(query)
                }
            }
        ) {
            Icon(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = Color.White
            )
        }
    }
}
