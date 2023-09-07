package com.src.data.mapper

import com.src.data.model.LectureInfo


fun mapLectureInfoToViewData(lectureInfo: List<LectureInfo>): List<com.src.domain.model.LifeLongItem> {
    return lectureInfo.map {
        com.src.domain.model.LifeLongItem(
            it.lctreNm,
            it.instrctrNm,
            it.edcStartDay,
            it.edcEndDay,
            it.edcStartTime,
            it.edcColseTime,
            it.lctreCo,
            it.edcTrgetType,
            it.edcMthType,
            it.operDay,
            it.edcPlace,
            it.psncpa,
            it.lctreCost,
            it.edcRdnmadr,
            it.operInstitutionNm,
            it.operPhoneNumber,
            it.rceptStartDate,
            it.rceptEndDate,
            it.rceptMthType,
            it.slctnMthType,
            it.homepageUrl,
            it.oadtCtLctreYn,
            it.pntBankAckestYn,
            it.lrnAcnutAckestYn,
            it.referenceDate,
            it.insttCode
        )
    }
}

