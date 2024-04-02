package ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TableScreen(
    modifier: Modifier = Modifier,
    
) {
    val componentViewModel: ComponentViewModel = getViewModel(Unit, viewModelFactory { ComponentViewModel() })
    
    LazyColumn(
        modifier = modifier
    ) {
        items(rowItemData.size) { index ->
            when (rowItemData[index].rowName) {
                "text" -> {
                    RowCell(
                        text = rowItemData[index].rowName,
                        value = componentViewModel.buttonName,
                        onValueChange = {componentViewModel.buttonName = it}
                    )
                }
                "icon" -> {
                    RowCell(
                        text = rowItemData[index].rowName,
                        icon = true,
                        checked = componentViewModel.iconButton.value,
                        onCheckedChange = { componentViewModel.iconButton.value = it })
                }

                else -> {
                    RowCell(
                        text = rowItemData[index].rowName,
                        value = rowItemData[index].rowTextField,
                        onValueChange = {})
                }
            }
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

@Composable
fun RowCell(
    text: String,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    icon: Boolean = false,
    checked: Boolean = true,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    if (icon) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Row(modifier = Modifier
                .weight(0.3f),
                ){
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(16.dp)
                //                    .weight(1f),
                )
            }
            Row(modifier = Modifier
                .weight(0.9f)){
                Switch(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 30.dp, 0.dp)
                        .fillMaxWidth(),
                    checked = checked, onCheckedChange = onCheckedChange
                )
            }
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            ) {
            Row(modifier = Modifier
                .weight(0.3f),
                ){
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(16.dp)
                //                   .weight(1f),
                )
            }
                Row(
                    modifier = Modifier
                        .weight(0.9f)
                ){
                    TextField(value = value, singleLine = true, onValueChange = onValueChange,
                              modifier = Modifier.fillMaxWidth()
                                  .padding(80.dp, 0.dp, 16.dp, 0.dp))
                }
        }
    }
}