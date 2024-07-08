package com.ghostdev.travelwell.data.models

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    val page: Int,
    val size: Int,
    val total: Int,
    val debug: String?,
    @SerializedName("previous_page")
    val previousPage: String?,
    @SerializedName("next_page")
    val nextPage: String?,
    val items: List<Product>
)

data class Product(
    val name: String,
    val description: String?,
    @SerializedName("unique_id")
    val uniqueId: String,
    @SerializedName("url_slug")
    val urlSlug: String,
    @SerializedName("is_available")
    val isAvailable: Boolean,
    @SerializedName("is_service")
    val isService: Boolean,
    @SerializedName("previous_url_slugs")
    val previousUrlSlugs: List<String>?,
    val unavailable: Boolean,
    @SerializedName("unavailable_start")
    val unavailableStart: String?,
    @SerializedName("unavailable_end")
    val unavailableEnd: String?,
    val id: String,
    @SerializedName("parent_product_id")
    val parentProductId: String?,
    val parent: String?,
    @SerializedName("organization_id")
    val organizationId: String,
    @SerializedName("product_image")
    val productImage: List<String>,
    val categories: List<String>,
    @SerializedName("date_created")
    val dateCreated: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("user_id")
    val userId: String,
    val photos: List<Photo>,
    @SerializedName("current_price")
    val currentPrice: List<Map<String, List<Any>>>,
    @SerializedName("is_deleted")
    val isDeleted: Boolean,
    @SerializedName("available_quantity")
    val availableQuantity: Double,
    val sellingPrice: Double?,
    val discountedPrice: Double?,
    val buyingPrice: Double?,
    @SerializedName("extra_infos")
    val extraInfos: Any?
)

data class Photo(
    @SerializedName("model_name")
    val modelName: String,
    @SerializedName("model_id")
    val modelId: String,
    @SerializedName("organization_id")
    val organizationId: String,
    val filename: String,
    val url: String,
    @SerializedName("is_featured")
    val isFeatured: Boolean,
    @SerializedName("save_as_jpg")
    val saveAsJpg: Boolean,
    @SerializedName("is_public")
    val isPublic: Boolean,
    @SerializedName("file_rename")
    val fileRename: Boolean,
    val position: Int
)
