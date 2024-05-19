package com.example.hospedafcil.ui.viviendasScreens.componentes

import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.example.hospedafcil.R
import com.example.hospedafcil.data.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateViviendaScreen(
    viewModel: AppViewModel,
    viviendaId: Int,
    navigateBack: () -> Unit
) {

    //###############################Falla recoger Viendas#########################################

    LaunchedEffect(key1 = Unit) {
        viewModel.getVivienda(viviendaId)
    }
    val vivienda = viewModel.vivienda

    val context = LocalContext.current
    var bitmap by remember { mutableStateOf(viewModel.vivienda.imagen) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val tiposVivienda = listOf("Casa", "Apartamento", "Habitación")
    var selectedTypeIndex by remember { mutableIntStateOf(0) }

    val imageCropLauncher = rememberLauncherForActivityResult(contract = CropImageContract()) { result ->
        if(result.isSuccessful){
            imageUri = result.uriContent
        }
    }

    if (imageUri != null){
        bitmap = if(Build.VERSION.SDK_INT < 28){
            val inputStream = context.contentResolver.openInputStream(imageUri!!)
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream?.close()

            val scaledOptions = BitmapFactory.Options()
            scaledOptions.inSampleSize = 2
            context.contentResolver.openInputStream(imageUri!!)?.use { input ->
                BitmapFactory.decodeStream(input, null, scaledOptions)
            }
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, imageUri!!)
            ImageDecoder.decodeBitmap(source)
        }
    }

    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = { navigateBack() },
        title = {
            Text(text = "Actualizar Vivenda")
        },
        text = {
               Column(
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                   TextField(
                       value = vivienda.nombre,
                       onValueChange = { nombre ->
                           viewModel.updateViviendaNombre(nombre)
                       },
                       placeholder = {
                           Text(text = "Nombre")
                       },
                       singleLine = true,
                       maxLines = 1
                   )

                   Spacer(modifier = Modifier.height(8.dp))

                   SingleChoiceSegmentedButtonRow {
                       tiposVivienda.forEachIndexed { index, label ->
                           SegmentedButton(
                               shape = SegmentedButtonDefaults.itemShape(index = index, count = tiposVivienda.size),
                               onClick = { selectedTypeIndex = index ; viewModel.updateViviendaTipo(tiposVivienda[index]) },
                               selected = index == selectedTypeIndex
                           ) {
                               Text(label, fontSize = 13.sp)
                           }
                       }
                   }

                   Spacer(modifier = Modifier.height(8.dp))

                   TextField(
                       value = vivienda.descripcion,
                       onValueChange = { descripcion ->
                           viewModel.updateViviendaDescripcion(descripcion)
                       },
                       placeholder = {
                           Text(text = "Descripción")
                       },
                       singleLine = true,
                       maxLines = 1
                   )

                   Spacer(modifier = Modifier.height(16.dp))

                   if(vivienda.imagen != null) {
                       Image(
                           bitmap = vivienda.imagen.asImageBitmap(),
                           contentDescription = null,
                           modifier = Modifier
                               .background(Color.LightGray)
                               .size(250.dp)
                               .padding(8.dp)
                               .clickable {
                                   val cropOption = CropImageContractOptions(
                                       CropImage.CancelledResult.uriContent,
                                       CropImageOptions()
                                   )
                                   imageCropLauncher.launch(cropOption)
                               }
                       )
                       viewModel.updateViviendaImagen(bitmap)
                   }
                   else{
                       Image(
                           imageVector = ImageVector.vectorResource(id = R.drawable.baseline_image_search_24),
                           contentDescription = null,
                           modifier = Modifier
                               .background(Color.LightGray)
                               .size(250.dp)
                               .padding(8.dp)
                               .clickable {
                                   val cropOption = CropImageContractOptions(
                                       CropImage.CancelledResult.uriContent,
                                       CropImageOptions()
                                   )
                                   imageCropLauncher.launch(cropOption)
                               }
                       )
                       viewModel.updateViviendaImagen(bitmap)
                   }
               }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    viewModel.updateVivienda(vivienda)
                    navigateBack()
                }){
                Text(text = "Actualizar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { navigateBack() }){
                Text(text = "Cancelar")
            }
        }
    )
}