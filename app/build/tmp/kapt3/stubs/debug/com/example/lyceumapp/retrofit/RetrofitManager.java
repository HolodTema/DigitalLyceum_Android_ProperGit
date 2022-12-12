package com.example.lyceumapp.retrofit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J$\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0014\u0010\f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0004\u0012\u00020\b0\rJ$\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000b2\u0014\u0010\f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0004\u0012\u00020\b0\rJ\u001c\u0010\u0012\u001a\u00020\b2\u0014\u0010\f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0012\u0004\u0012\u00020\b0\rJ$\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000b2\u0014\u0010\f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0004\u0012\u00020\b0\rJ$\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u000b2\u0014\u0010\f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0012\u0004\u0012\u00020\b0\rJ\u001c\u0010\u0019\u001a\u00020\b2\u0014\u0010\f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020\b0\rJ$\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000b2\u0014\u0010\f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0012\u0004\u0012\u00020\b0\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/example/lyceumapp/retrofit/RetrofitManager;", "", "()V", "BASE_URL", "", "retrofit", "Lretrofit2/Retrofit;", "createClient", "", "getGradesForSchool", "schoolId", "", "listener", "Lkotlin/Function1;", "Lcom/example/lyceumapp/json/grades/SchoolGradesJson;", "getScheduleForSubgroup", "subgroupId", "Lcom/example/lyceumapp/json/subgroups/SubgroupScheduleJson;", "getSchools", "Lcom/example/lyceumapp/json/schools/SchoolsListJson;", "getSubgroupInfo", "Lcom/example/lyceumapp/json/subgroups/SubgroupInfoJson;", "getSubgroupsForGrade", "gradeId", "Lcom/example/lyceumapp/json/subgroups/GradeSubgroupsJson;", "getTeachers", "Lcom/example/lyceumapp/json/teachers/TeachersListJson;", "getTodaySchedule", "Lcom/example/lyceumapp/json/subgroups/SubgroupTodayScheduleJson;", "app_debug"})
public final class RetrofitManager {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.lyceumapp.retrofit.RetrofitManager INSTANCE = null;
    private static final java.lang.String BASE_URL = "https://dev.lava-land.ru";
    private static retrofit2.Retrofit retrofit;
    
    private RetrofitManager() {
        super();
    }
    
    private final void createClient() {
    }
    
    public final void getTeachers(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.lyceumapp.json.teachers.TeachersListJson, kotlin.Unit> listener) {
    }
    
    public final void getSchools(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.lyceumapp.json.schools.SchoolsListJson, kotlin.Unit> listener) {
    }
    
    public final void getGradesForSchool(int schoolId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.lyceumapp.json.grades.SchoolGradesJson, kotlin.Unit> listener) {
    }
    
    public final void getSubgroupsForGrade(int gradeId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.lyceumapp.json.subgroups.GradeSubgroupsJson, kotlin.Unit> listener) {
    }
    
    public final void getSubgroupInfo(int subgroupId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.lyceumapp.json.subgroups.SubgroupInfoJson, kotlin.Unit> listener) {
    }
    
    public final void getScheduleForSubgroup(int subgroupId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.lyceumapp.json.subgroups.SubgroupScheduleJson, kotlin.Unit> listener) {
    }
    
    public final void getTodaySchedule(int subgroupId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.lyceumapp.json.subgroups.SubgroupTodayScheduleJson, kotlin.Unit> listener) {
    }
}