package com.src.data.mapper

import com.src.data.model.LectureData
import com.src.domain.model.LectureEntity

object Mapper {

    fun mapLectureDataToEntity(lectureData: LectureData): LectureEntity {
        return LectureEntity(
            lctreNm = lectureData._lectureName,
            instrctrNm = lectureData._instructorName,
            edcStartDay = lectureData._educationStartDay,
            edcEndDay = lectureData._educationEndDay,
            edcStartTime = lectureData._educationStartTime,
            edcColseTime = lectureData._educationCloseTime,
            lctreCo = lectureData._lectureContent,
            edcTrgetType = lectureData._educationTargetType,
            edcMthType = lectureData._educationMethodType,
            operDay = lectureData._operatingDay,
            edcPlace = lectureData._educationPlace,
            psncpa = lectureData._personCapacity.toString(),
            lctreCost = lectureData._lectureCost.toString(),
            edcRdnmadr = lectureData._educationRoadNameAddress,
            operInstitutionNm = lectureData._operatingInstitutionName,
            operPhoneNumber = lectureData._operatingPhoneNumber,
            rceptStartDate = lectureData._receiptStartDate,
            rceptEndDate = lectureData._receiptEndDate,
            rceptMthType = lectureData._receiptMethodType,
            slctnMthType = lectureData._selectionMethodType,
            homepageUrl = lectureData._homepageUrl,
            oadtCtLctreYn = lectureData._onlineAdaptLecture,
            pntBankAckestYn = lectureData._pointBankAccepted,
            lrnAcnutAckestYn = lectureData._learningAccountAccepted,
            referenceDate = lectureData._referenceDate,
            insttCode = lectureData.insttCode,
            lectureName = lectureData._lectureName,
            instructorName = lectureData._instructorName,
            startDay = lectureData._educationStartDay,
            endDay = lectureData._educationEndDay,
            startTime = lectureData._educationStartTime,
            closeTime = lectureData._educationCloseTime,
            lectureContent = lectureData._lectureContent,
            targetType = lectureData._educationTargetType,
            methodType = lectureData._educationMethodType,
            operatingDay = lectureData._operatingDay,
            place = lectureData._educationPlace,
            capacity = lectureData._personCapacity,
            cost = lectureData._lectureCost,
            address = lectureData._educationRoadNameAddress,
            operatingInstitutionName = lectureData._operatingInstitutionName,
            operatingPhoneNumber = lectureData._operatingPhoneNumber,
            registrationStartDate = lectureData._receiptStartDate,
            registrationEndDate = lectureData._receiptEndDate,
            registrationMethodType = lectureData._receiptMethodType
        )
    }
}