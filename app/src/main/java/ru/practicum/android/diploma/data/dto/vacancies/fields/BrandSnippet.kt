package ru.practicum.android.diploma.data.dto.vacancies.fields


import com.google.gson.annotations.SerializedName

data class BrandSnippet(
    @SerializedName("background")
    val background: ru.practicum.android.diploma.data.dto.vacancies.fields.Background,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("logo_scalable")
    val logoScalable: LogoScalable,
    @SerializedName("logo_xs")
    val logoXs: String,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("picture_scalable")
    val pictureScalable: PictureScalable,
    @SerializedName("picture_xs")
    val pictureXs: String
)
