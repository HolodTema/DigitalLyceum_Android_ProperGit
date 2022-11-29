package com.example.lyceumapp

class Const {
    companion object{
        const val LOG_TAG_RETROFIT_ON_FAILURE = "logTagRetrofitOnFailure"

        const val SH_PREFERENCES_NAME = "shPreferencesSettings"
        const val SH_PREFERENCES_KEY_SCHOOL_ID = "shPreferencesKeySchoolId"
        const val SH_PREFERENCES_KEY_GRADE_ID = "shPreferencesKeyGradeId"
        const val SH_PREFERENCES_KEY_SUBGROUP_ID = "shPreferencesKeySubgroupId"
        const val SH_PREFERENCES_KEY_SCHOOL_NAME = "shPreferencesKeySchoolName"
        const val SH_PREFERENCES_KEY_SCHOOL_ADDRESS ="shPreferencesKeySchoolAddress"
        const val SH_PREFERENCES_KEY_SCHOOL_PHONE_NUMBER = "shPreferencesKeySchoolPhoneNumber"

        const val INTENT_KEY_NO_RESPONSE_TYPE = "intentKeyNoResponseType"
        const val INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT = "intentKeyAmountAttemptsToConnect"
        const val INTENT_KEY_CHOSEN_SCHOOL = "intentKeyChosenSchool"
        const val INTENT_KEY_CHOSEN_GRADE = "intentKeyChosenGrade"
        const val INTENT_KEY_AMOUNT_GRADES = "intentKeyAmountGrades"
        const val INTENT_KEY_LESSONS_FOR_SUBGROUP = "intentKeyLessonsForSubgroup"

        const val BUNDLE_KEY_LESSONS_FOR_ONE_DAY_OF_SUBGROUP = "bundleKeyLessonsForOneDayOfSubgroup"
        const val BUNDLE_KEY_LESSONS_FOR_ONE_DAY_OF_GRADE = "bundleKeyLessonsForOneDayOfGrade"

        const val DB_NAME = "lyceumDatabase"

        const val AMOUNT_ATTEMPTS_TO_CONNECT_BEFORE_TIMING = 5
        const val DELAY_SECONDS_MANY_ATTEMPTS_TO_CONNECT = 20

        const val NETWORK_CONNECT_TIMEOUT_SECONDS = 30L
        const val NETWORK_READ_TIMEOUT_SECONDS = 30L
        const val NETWORK_WRITE_TIMEOUT_SECONDS = 30L
    }

    enum class NoResponseType{
        GetSchools,
        GetGrades,
        GetSubgroups,
        GetLessons
    }
}