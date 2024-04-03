package com.example.hospedafcil.ui.theme.casa.ui

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hospedafcil.R
import com.example.hospedafcil.ui.theme.casa.viewModel.AppViewModelProvider
import com.example.hospedafcil.ui.theme.casa.viewModel.CasaDetails
import com.example.hospedafcil.ui.theme.casa.viewModel.CasaUiState
import com.example.hospedafcil.ui.theme.casa.viewModel.CasaViewModel
import kotlinx.coroutines.launch
import java.io.IOException

@Composable
fun CasaEntryScreen(
    viewModel: CasaViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val cououtineScope = rememberCoroutineScope()
    CasaEntryBody(
        casaUiState = viewModel.CasaUiState,
        onCasaValueChange = viewModel::updateUiState,
        onSaveClick = {
            cououtineScope.launch {
                viewModel.saveCasa()
            }
        },
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    )
}

@Composable
fun CasaEntryBody(
    casaUiState: CasaUiState,
    onCasaValueChange: (CasaDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column {
        CasaInputForm(
            casaDetails = casaUiState.casaDetails,
            onValueChange = onCasaValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { onSaveClick() },
            enabled = casaUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
            ) {
            Text(text = "Guardar")
        }
    }
}

@Composable
fun CasaInputForm(
    casaDetails: CasaDetails,
    modifier: Modifier = Modifier,
    onValueChange: (CasaDetails) -> Unit = {},
    enabled: Boolean = true
){
    val context = LocalContext.current
    val img: Bitmap = BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_menu_report_image)
    val bitmap = remember{ mutableStateOf(img) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) {
        if (it != null) {
            bitmap.value = it
        }
    }

    val launchImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri ->
            try {
                if(Build.VERSION.SDK_INT < 28){
                    bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, imageUri)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }
            } catch (e: IOException) {
                // Maneja cualquier error de lectura de la imagen aquí
                e.printStackTrace()
            }
        }
    }

    Column (modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = casaDetails.nombre,
            onValueChange = {onValueChange(casaDetails.copy(nombre = it))}
        )
        OutlinedTextField(
            value = casaDetails.descripcion,
            onValueChange = {onValueChange(casaDetails.copy(descripcion = it))}
        )
        Image(
            bitmap = bitmap.value.asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(text = "Añadir foto")

        IconButton(onClick = {
            launcher.launch()
        }) {
            Icon(painter = painterResource(id = R.drawable.baseline_photo_camera_24), contentDescription = null)
        }

        IconButton(onClick = { launchImage.launch("image/*") }) {
            Icon(painter = painterResource(id = R.drawable.baseline_image_search_24), contentDescription = null)
        }
    }
}