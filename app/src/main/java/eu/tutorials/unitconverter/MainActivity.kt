package eu.tutorials.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tutorials.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
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
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var iExpanded by remember { mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false)}
    val ConversionFactor = remember { mutableStateOf(1.00)}
    val oConversionFactor = remember { mutableStateOf(1.00)}

    fun ConvertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * ConversionFactor.value *100.0 / oConversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //here all the UI elements will be stacked below each other
        Text("Unit Converter",style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue , onValueChange = {
            inputValue = it
           //here goes what should happen , when the value of our OutlinedTextField changes
        }, label = {Text("Enter Value")})
        Row {
            //here all the UI elements will be stacked next to each other

            Box{
                //Input Box
                Button(onClick = {  iExpanded = true}) {
                    //Input Button
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded =  iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text("centimeter") }, onClick = { iExpanded = false
                         inputUnit = "centimeter"
                         ConversionFactor.value = 0.01
                         ConvertUnits()
                    })
                    DropdownMenuItem(text = { Text("meter") }, onClick = { iExpanded = false
                        inputUnit = "meter"
                        ConversionFactor.value = 1.0
                        ConvertUnits()
                    })
                    DropdownMenuItem(text = { Text("feet") }, onClick = { iExpanded = false
                        inputUnit = "feet"
                        ConversionFactor.value = 0.3048
                        ConvertUnits()
                    })
                    DropdownMenuItem(text = { Text("millimeter") }, onClick = { iExpanded = false
                        inputUnit = "millimeter"
                        ConversionFactor.value = 0.001
                        ConvertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                //Output Box
                Button(onClick = {  oExpanded = true }) {
                    //Output button
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded =  oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text("centimeter") }, onClick = { oExpanded = false
                        outputUnit = "centimeter"
                        oConversionFactor.value = 0.01
                        ConvertUnits() })
                    DropdownMenuItem(text = { Text("meter") }, onClick = { oExpanded = false
                        outputUnit = "meter"
                        oConversionFactor.value = 1.0
                        ConvertUnits()})
                    DropdownMenuItem(text = { Text("feet") }, onClick = { oExpanded = false
                        outputUnit = "feet"
                        oConversionFactor.value = 0.3048
                        ConvertUnits()  })
                    DropdownMenuItem(text = { Text("millimeter") }, onClick = {oExpanded = false
                        outputUnit = "millimeter"
                        oConversionFactor.value = 0.001
                        ConvertUnits()})
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result : ${outputValue}  ${outputUnit}" , style = MaterialTheme.typography.headlineMedium)
    }
}




@Preview(showBackground = true)@Composable

fun UnitConverterPreview() {
  UnitConverter()
}