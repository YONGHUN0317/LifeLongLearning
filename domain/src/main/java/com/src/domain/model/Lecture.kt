package com.src.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lecture(
    val lctreNm: String,
    val instrctrNm: String,
    val edcStartDay: String,
    val edcEndDay: String,
    val edcStartTime: String,
    val edcColseTime: String,
    val lctreCo: String,
    val edcTrgetType: String,
    val edcMthType: String,
    val operDay: String,
    val edcPlace: String,
    val psncpa: String,
    val lctreCost: String,
    val edcRdnmadr: String,
    val operInstitutionNm: String,
    val operPhoneNumber: String,
    val rceptStartDate: String,
    val rceptEndDate: String,
    val rceptMthType: String,
    val slctnMthType: String,
    val homepageUrl: String,
    val oadtCtLctreYn: String,
    val pntBankAckestYn: String,
    val lrnAcnutAckestYn: String,
    val referenceDate: String,
    val insttCode: String
) : Parcelable
