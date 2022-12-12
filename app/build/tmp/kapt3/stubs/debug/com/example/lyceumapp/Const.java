package com.example.lyceumapp;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00032\u00020\u0001:\u0002\u0003\u0004B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0005"}, d2 = {"Lcom/example/lyceumapp/Const;", "", "()V", "Companion", "NoResponseType", "app_debug"})
public final class Const {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.lyceumapp.Const.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOG_TAG_DRAWER_INCORRECT_MENU_ITEM_ID = "DrawerMenuItemId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOG_TAG_RETROFIT_ON_FAILURE = "RetrofitOnFailure";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SH_PREFERENCES_NAME = "shPreferencesSettings";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SH_PREFERENCES_KEY_SCHOOL_ID = "shPreferencesKeySchoolId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SH_PREFERENCES_KEY_GRADE_ID = "shPreferencesKeyGradeId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SH_PREFERENCES_KEY_SUBGROUP_ID = "shPreferencesKeySubgroupId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SH_PREFERENCES_KEY_SCHOOL_NAME = "shPreferencesKeySchoolName";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SH_PREFERENCES_KEY_SCHOOL_ADDRESS = "shPreferencesKeySchoolAddress";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SH_PREFERENCES_KEY_SCHOOL_PHONE_NUMBER = "shPreferencesKeySchoolPhoneNumber";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INTENT_KEY_NO_RESPONSE_TYPE = "intentKeyNoResponseType";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT = "intentKeyAmountAttemptsToConnect";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INTENT_KEY_CHOSEN_SCHOOL = "intentKeyChosenSchool";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INTENT_KEY_CHOSEN_GRADE = "intentKeyChosenGrade";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INTENT_KEY_AMOUNT_GRADES = "intentKeyAmountGrades";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INTENT_KEY_LESSONS_FOR_SUBGROUP = "intentKeyLessonsForSubgroup";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INTENT_KEY_SUBGROUP_INFO = "intentKeySubgroupInfo";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BUNDLE_KEY_LESSONS_FOR_ONE_DAY_OF_SUBGROUP = "bundleKeyLessonsForOneDayOfSubgroup";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BUNDLE_KEY_LESSONS_FOR_ONE_DAY_OF_GRADE = "bundleKeyLessonsForOneDayOfGrade";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DB_NAME = "lyceumDatabase";
    public static final int AMOUNT_ATTEMPTS_TO_CONNECT_BEFORE_TIMING = 5;
    public static final int DELAY_SECONDS_MANY_ATTEMPTS_TO_CONNECT = 20;
    public static final long NETWORK_CONNECT_TIMEOUT_SECONDS = 30L;
    public static final long NETWORK_READ_TIMEOUT_SECONDS = 30L;
    public static final long NETWORK_WRITE_TIMEOUT_SECONDS = 30L;
    
    public Const() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/lyceumapp/Const$NoResponseType;", "", "(Ljava/lang/String;I)V", "GetSchools", "GetGrades", "GetSubgroups", "GetLessons", "app_debug"})
    public static enum NoResponseType {
        /*public static final*/ GetSchools /* = new GetSchools() */,
        /*public static final*/ GetGrades /* = new GetGrades() */,
        /*public static final*/ GetSubgroups /* = new GetSubgroups() */,
        /*public static final*/ GetLessons /* = new GetLessons() */;
        
        NoResponseType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/example/lyceumapp/Const$Companion;", "", "()V", "AMOUNT_ATTEMPTS_TO_CONNECT_BEFORE_TIMING", "", "BUNDLE_KEY_LESSONS_FOR_ONE_DAY_OF_GRADE", "", "BUNDLE_KEY_LESSONS_FOR_ONE_DAY_OF_SUBGROUP", "DB_NAME", "DELAY_SECONDS_MANY_ATTEMPTS_TO_CONNECT", "INTENT_KEY_AMOUNT_ATTEMPTS_TO_CONNECT", "INTENT_KEY_AMOUNT_GRADES", "INTENT_KEY_CHOSEN_GRADE", "INTENT_KEY_CHOSEN_SCHOOL", "INTENT_KEY_LESSONS_FOR_SUBGROUP", "INTENT_KEY_NO_RESPONSE_TYPE", "INTENT_KEY_SUBGROUP_INFO", "LOG_TAG_DRAWER_INCORRECT_MENU_ITEM_ID", "LOG_TAG_RETROFIT_ON_FAILURE", "NETWORK_CONNECT_TIMEOUT_SECONDS", "", "NETWORK_READ_TIMEOUT_SECONDS", "NETWORK_WRITE_TIMEOUT_SECONDS", "SH_PREFERENCES_KEY_GRADE_ID", "SH_PREFERENCES_KEY_SCHOOL_ADDRESS", "SH_PREFERENCES_KEY_SCHOOL_ID", "SH_PREFERENCES_KEY_SCHOOL_NAME", "SH_PREFERENCES_KEY_SCHOOL_PHONE_NUMBER", "SH_PREFERENCES_KEY_SUBGROUP_ID", "SH_PREFERENCES_NAME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}