package com.example.pickupanddrop.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pickupanddrop.R

@Composable
fun ClickableCard(
    image: Painter,
    heading: String,
    subHeading: String,
    coupon: String,
) {
    ElevatedCard(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            Row {
                Image(
                    painter = image,
                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .wrapContentHeight(align = Alignment.CenterVertically)
//                        .fillMaxHeight()
//                        .background(color = Color.Gray)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = heading,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
//                        color = Color.Black
                    )

                    Row {
                        Text(
                            text = subHeading,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
//                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = coupon,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = colorResource(id = R.color.lightBlue),
                        )
                    }
                }
            }

        }

    }
}

@Composable
fun PickUpLocationCard(
    modifier: Modifier = Modifier,
    franchiseName: String,
    franchiseLocation: String,
    ownerName: String,
    ownerPhoneNo: String
) {

    val labelFontWeight = MaterialTheme.typography.labelMedium.fontWeight

    ElevatedCard(
        modifier = modifier.border(1.dp, Color.Gray.copy(0.5f), RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),

        ) {
        Column() {
            // pick up location and owner details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray.copy(0.1f))
                    .padding(8.dp)
            ) {
                Row {
                    Text(
                        text = "PICKUP LOCATION",
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                        fontWeight = labelFontWeight,
                        color = Color.Gray
                    )
                    Text(text = " *", color = Color.Red.copy(0.8f))
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row {
                    Text(
                        text = "$franchiseName, ",
                        fontWeight = MaterialTheme.typography.labelMedium.fontWeight
                    )
                    Text(
                        text = franchiseLocation,
                        fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                        fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // owner name, phone no, and edit details option
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Filled.Call,
                        contentDescription = "phone no of owner",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    // owner name and phone no
                    Text(
                        text = "$ownerName ($ownerPhoneNo)",
                        color = Color.Gray,
                        fontWeight = labelFontWeight,
                        fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    // edit details
                    Text(
                        modifier = Modifier.clickable { },
                        text = "Edit Details",
                        fontWeight = FontWeight(800),
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        color = Color.Blue.copy(0.6f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))


            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray.copy(0.5f))
            ) {}

            val checkedStateForOTP = rememberSaveable {
                mutableStateOf(false)
            }
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier.size(16.dp),
                    checked = checkedStateForOTP.value, onCheckedChange = {
                        checkedStateForOTP.value = it
                    })
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "OTP for pickup",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = labelFontWeight,
                    fontStyle = MaterialTheme.typography.labelMedium.fontStyle
                )
            }
        }

    }
}

@Composable
fun AdditionalDetails(
    modifier: Modifier = Modifier,
    lengthState: MutableState<String>,
    widthState: MutableState<String>,
    heightState: MutableState<String>
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer.copy(0.3f))
                .padding(16.dp)
        ) {

            var item by remember { mutableStateOf("") }
            var dimen by remember { mutableStateOf("") }

            Text(
                text = "Additional Details",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontStyle = MaterialTheme.typography.headlineSmall.fontStyle
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = item,
                onValueChange = { item = it },
                label = { Text("Enter item detail") },
                maxLines = 4,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "add item detail"
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

//            TextField(
//                value = dimen,
//                onValueChange = { dimen = it },
//                label = { Text("Enter dimensions") },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Filled.Menu,
//                        contentDescription = "add item detail"
//                    )
//                }
//            )

            Text(
                text = "Enter Dimensions (cm)",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.weight(0.1f))
                OutlinedTextField(
                    value = lengthState.value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 4) {
                            lengthState.value = newValue
                        }
                    },
                    label = { Text("Length") },
                    modifier = Modifier
                        .weight(1.5f),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.weight(0.1f))
                OutlinedTextField(
                    value = widthState.value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 4) {
                            widthState.value = newValue
                        }
                    },
                    label = { Text("Width") },
                    modifier = Modifier
                        .weight(1.5f),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.weight(0.1f))
                OutlinedTextField(
                    value = heightState.value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 4) {
                            heightState.value = newValue
                        }
                    },
                    label = { Text("Height") },
                    modifier = Modifier
                        .weight(1.5f),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.weight(0.1f))
            }


        }
    }
}

@Preview
@Composable
fun AdditionalDetailsPreview() {
    AdditionalDetails(
        lengthState = remember { mutableStateOf("10") },
        widthState = remember { mutableStateOf("10") },
        heightState = remember { mutableStateOf("10") }
    )
}

@Preview
@Composable
fun PickUpLocationCardPreview() {
    PickUpLocationCard(
        franchiseName = "Petuk Ji G.Noida Franchise",
        franchiseLocation = "IT waala digital success D, 296 Greater Noida Wandra",
        ownerName = "Vinay",
        ownerPhoneNo = "7970783256"
    )
}

@Preview
@Composable
fun ClickableCardPreview() {
    ClickableCard(
        image = painterResource(id = R.drawable.gift),
        heading = "Welcome Offer! Get 30% off on your first 5 deliveries",
        subHeading = "Use coupon : ",
        coupon = "WELCOME30"
    )
}