package com.example.lyceumapp.json.grades;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lcom/example/lyceumapp/json/grades/SchoolGradesJson;", "", "schoolId", "", "schoolGrades", "", "Lcom/example/lyceumapp/json/grades/GradeJson;", "(ILjava/util/List;)V", "getSchoolGrades", "()Ljava/util/List;", "getSchoolId", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
public final class SchoolGradesJson {
    private final int schoolId = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.lyceumapp.json.grades.GradeJson> schoolGrades = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.lyceumapp.json.grades.SchoolGradesJson copy(@com.squareup.moshi.Json(name = "school_id")
    int schoolId, @org.jetbrains.annotations.NotNull()
    @com.squareup.moshi.Json(name = "classes")
    java.util.List<com.example.lyceumapp.json.grades.GradeJson> schoolGrades) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public SchoolGradesJson(@com.squareup.moshi.Json(name = "school_id")
    int schoolId, @org.jetbrains.annotations.NotNull()
    @com.squareup.moshi.Json(name = "classes")
    java.util.List<com.example.lyceumapp.json.grades.GradeJson> schoolGrades) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getSchoolId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.lyceumapp.json.grades.GradeJson> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.lyceumapp.json.grades.GradeJson> getSchoolGrades() {
        return null;
    }
}