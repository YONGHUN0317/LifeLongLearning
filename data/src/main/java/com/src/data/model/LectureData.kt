package com.src.data.model

import com.google.gson.annotations.SerializedName

data class LectureData(
    @SerializedName("lctreNm") val _lectureName: String,
    @SerializedName("instrctrNm") val _instructorName: String,
    @SerializedName("edcStartDay") val _educationStartDay: String,
    @SerializedName("edcEndDay") val _educationEndDay: String,
    @SerializedName("edcStartTime") val _educationStartTime: String,
    @SerializedName("edcColseTime") val _educationCloseTime: String,
    @SerializedName("lctreCo") val _lectureContent: String,
    @SerializedName("edcTrgetType") val _educationTargetType: String,
    @SerializedName("edcMthType") val _educationMethodType: String,
    @SerializedName("operDay") val _operatingDay: String,
    @SerializedName("edcPlace") val _educationPlace: String,
    @SerializedName("psncpa") val _personCapacity: Int,
    @SerializedName("lctreCost") val _lectureCost: Int,
    @SerializedName("edcRdnmadr") val _educationRoadNameAddress: String,
    @SerializedName("operInstitutionNm") val _operatingInstitutionName: String,
    @SerializedName("operPhoneNumber") val _operatingPhoneNumber: String,
    @SerializedName("rceptStartDate") val _receiptStartDate: String,
    @SerializedName("rceptEndDate") val _receiptEndDate: String,
    @SerializedName("rceptMthType") val _receiptMethodType: String,
    @SerializedName("slctnMthType") val _selectionMethodType: String,
    @SerializedName("homepageUrl") val _homepageUrl: String,
    @SerializedName("oadtCtLctreYn") val _onlineAdaptLecture: String,
    @SerializedName("pntBankAckestYn") val _pointBankAccepted: String,
    @SerializedName("lrnAcnutAckestYn") val _learningAccountAccepted: String,
    @SerializedName("referenceDate") val _referenceDate: String,
    @SerializedName("insttCode") val insttCode: String
)