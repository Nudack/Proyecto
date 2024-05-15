package com.example.hospedafcil.ui.app.ui.viviendasScreens.componentes

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.example.hospedafcil.R
import com.example.hospedafcil.data.vivienda.Vivienda
import kotlinx.coroutines.job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddViviendaAlertDialog(
    openDialog: Boolean,
    closeDialog: () -> Unit,
    addVivienda: (vivienda: Vivienda) -> Unit
){
    if(openDialog){
        var nombre by remember { mutableStateOf("") }
        var descripcion by remember { mutableStateOf("") }
        val focusRequester = FocusRequester()
        val context = LocalContext.current
        var bitmap by remember { mutableStateOf<Bitmap?>(null) }
        var imageUri by remember { mutableStateOf<Uri?>(null) }
        val tiposVivienda = listOf("Casa", "Apartamento", "Habitación")
        var selectedTypeIndex by remember { mutableIntStateOf(0) }

        val imageCropLauncher = rememberLauncherForActivityResult(contract = CropImageContract()) { result ->
            if(result.isSuccessful){
                imageUri = result.uriContent
            }
            else {
                val exception = result.error
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
            onDismissRequest = { closeDialog() },
            title = {
                Text(text = "Nueva Vivienda")
            },
            text = {
                Column {
                    TextField(
                        value = nombre,
                        onValueChange = {nombre = it},
                        placeholder = {
                            Text(text = "Nombre") },
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                    LaunchedEffect(Unit) {
                        coroutineContext.job.invokeOnCompletion {
                            focusRequester.requestFocus()
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    SingleChoiceSegmentedButtonRow {
                        tiposVivienda.forEachIndexed { index, label ->
                            SegmentedButton(
                                shape = SegmentedButtonDefaults.itemShape(index = index, count = tiposVivienda.size),
                                onClick = { selectedTypeIndex = index },
                                selected = index == selectedTypeIndex
                            ) {
                                Text(label)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = descripcion,
                        onValueChange = {descripcion = it},
                        placeholder ={
                            Text(text = "Descripción")
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (bitmap != null){
                        Image(
                            bitmap = bitmap?.asImageBitmap()!!,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .background(Color.Blue)
                                .size(400.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,)
                                .clickable {
                                    val cropOption = CropImageContractOptions(
                                        CropImage.CancelledResult.uriContent,
                                        CropImageOptions()
                                    )
                                    imageCropLauncher.launch(cropOption)
                                }
                        )
                    }
                    else {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = null,
                            modifier = Modifier
                                .background(Color.LightGray)
                                .size(400.dp)
                                .padding(20.dp)
                                .clickable {
                                    val cropOption = CropImageContractOptions(
                                        CropImage.CancelledResult.uriContent,
                                        CropImageOptions()
                                    )
                                    imageCropLauncher.launch(cropOption)
                                }
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    closeDialog()
                    val vivienda = Vivienda(0, nombre, tiposVivienda[selectedTypeIndex], descripcion, bitmap)
                    addVivienda(vivienda)
                }) {
                    Text(text = "Añadir")
                }
            },
            dismissButton = {
                TextButton(onClick = { closeDialog() }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
}