package com.example.hospedafcil.ui.theme.casa.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hospedafcil.R
import com.example.hospedafcil.data.casa.Casa
import com.example.hospedafcil.ui.theme.casa.viewModel.AppViewModelProvider
import com.example.hospedafcil.ui.theme.casa.viewModel.CasaDetailsUiState
import com.example.hospedafcil.ui.theme.casa.viewModel.CasaDetailsViewModel
import com.example.hospedafcil.ui.theme.casa.viewModel.toCasa
import kotlinx.coroutines.launch

interface NavigationDestination {
    val route: String
    val titleRes: Int
}
object CasaDetailsDestination: NavigationDestination {
    override val route = "item_details"
    override val titleRes = R.string.item_detail_title
    const val itemIdArg = "itemid"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@Composable
fun CasaDetailsScreen(
    navigateToEditCasa: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CasaDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold (topBar = {
        CasaTopAppBar(
            title = stringResource(id = CasaDetailsDestination.titleRes),
            canNavigateBack = true,
            navigateUp = navigateBack
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { navigateToEditCasa(uiState.value.casaDetails.id) },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(id = R.string.edit_item_title))
        }
    },
        modifier = modifier
    ) {innerPadding ->
        CasaDetailsBody(
            casaDetailsUiState = uiState.value,
            onDelete = {
                coroutineScope.launch { 
                    viewModel.deleteCasa()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        )

    }
}

@Composable
private fun CasaDetailsBody(
    casaDetailsUiState: CasaDetailsUiState,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
   Column (
       modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
       verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
       ) {
       var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
       CasaDetails(
           casa = casaDetailsUiState.casaDetails.toCasa(),
           modifier = Modifier.fillMaxWidth()
       )
       OutlinedButton(
           onClick = { deleteConfirmationRequired = true },
           shape = MaterialTheme.shapes.small,
           modifier = Modifier.fillMaxWidth()
       ) {
           Text(stringResource(id = R.string.delete))
       }
       if (deleteConfirmationRequired) {
           DeleteConfirmationDialog(
               onDeleteConfirm = {
                   deleteConfirmationRequired = false
                   onDelete()
               },
               onDeleteCancel = {deleteConfirmationRequired = false},
               modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
           )
       }
   }
}

@Composable
fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = stringResource(id = R.string.attention))},
        text = { Text(text = stringResource(id = R.string.delete_question))},
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = stringResource(id = R.string.no))
            }
        },
        confirmButton = { TextButton(onClick = onDeleteConfirm) {
            Text(text = stringResource(id = R.string.yes))
        } })
}

@Composable
fun CasaDetails(casa: Casa, modifier: Modifier = Modifier){
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ){
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            CasaDetailsRow(
                labelResID = R.string.item,
                casaDetail = Casa.nombre,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_medium
                    )
                )
            )
            CasaDetailsRow(
                labelResID = R.string.description,
                casaDetail = Casa.descripcion,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_medium
                    )
                )
            )
            CasaDetailsRow(
                labelResID = R.string.item,
                casaDetail = Casa.imagen,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_medium
                    )
                )
            )
        }
    }
}

@Composable
fun CasaDetailsRow(
    @StringRes labelResID: Int, casaDetail: String, modifier: Modifier = Modifier
){
    Row(modifier = modifier) {
        Text(text = stringResource(labelResID))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = casaDetail, fontWeight = FontWeight.Bold)
    }
}