package com.src.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "lectureInfo")
data class LectureInfo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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
