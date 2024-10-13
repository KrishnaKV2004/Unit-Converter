package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

val samsungOne = FontFamily(
    Font(R.font.samsung_one_normal, FontWeight.Normal),
    Font(R.font.samsung_one_bold, FontWeight.Bold)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var inputExpanded by remember { mutableStateOf(false)}
    var outputExpanded by remember { mutableStateOf(false)}
    val inpConversionFactor = remember { mutableStateOf(1.00)}
    val outConversionFactor = remember { mutableStateOf(1.00)}

    fun convertUnits()
    {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * inpConversionFactor.value * 100.0/outConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //  All UI Elements Stacked Below Each Other

        Spacer(modifier = Modifier.height(120.dp))

        Text (
            "Unit Converter",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(40.dp)
        )

        Spacer (modifier = Modifier.height(100.dp))

        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            },
            label = {Text("Input Value")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = MaterialTheme.shapes.extraLarge
        )

        Spacer (modifier = Modifier.height(20.dp))

        Row (
            modifier = Modifier.padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            //  All UI Elements Stacked Next To Each Other

            Box {
                //  Input Button

                Button(onClick = {
                    inputExpanded = true
                },
                    modifier = Modifier
                        .width(140.dp)
                        .height(45.dp)
                ) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = inputExpanded, onDismissRequest = {
                    inputExpanded = false
                }) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Centimeters"
                            inpConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Meters"
                            inpConversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Feet"
                            inpConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Millimeters"
                            inpConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Inches")},
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Inches"
                            inpConversionFactor.value = 0.0254
                            convertUnits()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(20.dp))

            Box {
                //  Output Button

                Button(onClick = {
                    outputExpanded = true
                },
                    modifier = Modifier
                        .width(140.dp)
                        .height(45.dp)
                ) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = outputExpanded, onDismissRequest = {
                    outputExpanded = false
                }) {
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Meters"
                            outConversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Centimeters"
                            outConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Feet"
                            outConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Inches")},
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Inches"
                            outConversionFactor.value = 0.0254
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Millimeters"
                            outConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Result : $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter ()
}