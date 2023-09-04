package com.src.domain.model

import com.google.gson.annotations.SerializedName

data class LifeLongRes(
    @SerializedName("response") val response: LifeLongResponseBody
)

data class LifeLongResponseBody(
    @SerializedName("header") val header: LifeLongHeader,
    @SerializedName("body") val body: LifeLongBody
)

data class LifeLongHeader(
    @SerializedName("resultCode") val resultCode: String,
    @SerializedName("resultMsg") val resultMsg: String,
    @SerializedName("type") val type: String
)

data class LifeLongBody(
    @SerializedName("items") val items: List<LifeLongItem>
)

data class LifeLongItem(
    @SerializedName("lctreNm") val lctreNm: String,
    @SerializedName("instrctrNm") val instrctrNm: String,
    @SerializedName("edcStartDay") val edcStartDay: String,
    @SerializedName("edcEndDay") val edcEndDay: String,
    @SerializedName("edcStartTime") val edcStartTime: String,
    @SerializedName("edcColseTime") val edcColseTime: String,
    @SerializedName("lctreCo") val lctreCo: String,
    @SerializedName("edcTrgetType") val edcTrgetType: String,
    @SerializedName("edcMthType") val edcMthType: String,
    @SerializedName("operDay") val operDay: String,
    @SerializedName("edcPlace") val edcPlace: String,
    @SerializedName("psncpa") val psncpa: String,
    @SerializedName("lctreCost") val lctreCost: String,
    @SerializedName("edcRdnmadr") val edcRdnmadr: String,
    @SerializedName("operInstitutionNm") val operInstitutionNm: String,
    @SerializedName("operPhoneNumber") val operPhoneNumber: String,
    @SerializedName("rceptStartDate") val rceptStartDate: String,
    @SerializedName("rceptEndDate") val rceptEndDate: String,
    @SerializedName("rceptMthType") val rceptMthType: String,
    @SerializedName("slctnMthType") val slctnMthType: String,
    @SerializedName("homepageUrl") val homepageUrl: String,
    @SerializedName("oadtCtLctreYn") val oadtCtLctreYn: String,
    @SerializedName("pntBankAckestYn") val pntBankAckestYn: String,
    @SerializedName("lrnAcnutAckestYn") val lrnAcnutAckestYn: String,
    @SerializedName("referenceDate") val referenceDate: String,
    @SerializedName("insttCode") val insttCode: String
)
