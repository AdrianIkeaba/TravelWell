package com.ghostdev.travelwell.ui.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ghostdev.travelwell.BuildConfig
import com.ghostdev.travelwell.data.models.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListComponent(
    productListLogic: ProductListLogic = ProductListLogic()
) {
    val productListResponse by productListLogic.products
    val isLoading by productListLogic.isLoading

    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Travel Well",
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.LightGray
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // Content area
        if (isLoading) {
            // Show progress indicator while loading
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(productListResponse!!.items.size) { index ->
                    // Ensure that index is within bounds
                    if (index < productListResponse!!.items.size) {
                        ProductItem(product = productListResponse!!.items[index])
                    } else {
                        Log.e("ProductListComponent", "Index out of bounds: $index")
                    }
                }
            }
        }
    }
}


@Composable
fun ProductItem(
    product: Product
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        )
    ) {
        Box(
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Display product image
                AsyncImage(
                    modifier = Modifier.size(150.dp),
                    model = "${BuildConfig.IMAGE_BASE_URL}${product.photos[0].url}",
                    contentDescription = "Product Image"
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Product details column
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // Product name
                    Text(
                        text = product.name ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Product price
                    product.currentPrice.getOrNull(0)?.let { price ->
                        val ngnPrice = price["NGN"]?.getOrNull(0)
                        if (ngnPrice != null) {
                            Text(
                                text = "NGN $ngnPrice",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                        } else {
                            Text(
                                text = "Price not available",
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }
                    } ?: run {
                        Text(
                            text = "Price not available",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Quantity label
                    Text(
                        text = "Quantity: ${product.availableQuantity}",
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
