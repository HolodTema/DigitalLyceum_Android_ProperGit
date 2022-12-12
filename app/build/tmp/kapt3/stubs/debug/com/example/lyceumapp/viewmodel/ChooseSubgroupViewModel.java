package com.example.lyceumapp.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\bH\u0002R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001f\u0010\u001c\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001e0\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 \u00a8\u0006$"}, d2 = {"Lcom/example/lyceumapp/viewmodel/ChooseSubgroupViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "chosenGrade", "Lcom/example/lyceumapp/json/grades/GradeJson;", "(Landroid/app/Application;Lcom/example/lyceumapp/json/grades/GradeJson;)V", "amountAttemptsToConnect", "", "getAmountAttemptsToConnect", "()I", "setAmountAttemptsToConnect", "(I)V", "amountGrades", "getAmountGrades", "setAmountGrades", "chosenSchool", "Lcom/example/lyceumapp/json/schools/SchoolJson;", "getChosenSchool", "()Lcom/example/lyceumapp/json/schools/SchoolJson;", "setChosenSchool", "(Lcom/example/lyceumapp/json/schools/SchoolJson;)V", "chosenSubgroup", "Lcom/example/lyceumapp/json/subgroups/SubgroupJson;", "getChosenSubgroup", "()Lcom/example/lyceumapp/json/subgroups/SubgroupJson;", "setChosenSubgroup", "(Lcom/example/lyceumapp/json/subgroups/SubgroupJson;)V", "liveDataListSubgroups", "Landroidx/lifecycle/MutableLiveData;", "", "getLiveDataListSubgroups", "()Landroidx/lifecycle/MutableLiveData;", "downloadSubgroups", "", "gradeId", "app_debug"})
public final class ChooseSubgroupViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.lyceumapp.json.subgroups.SubgroupJson>> liveDataListSubgroups = null;
    private int amountAttemptsToConnect = 1;
    private int amountGrades = 1;
    public com.example.lyceumapp.json.schools.SchoolJson chosenSchool;
    public com.example.lyceumapp.json.subgroups.SubgroupJson chosenSubgroup;
    
    public ChooseSubgroupViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.grades.GradeJson chosenGrade) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.example.lyceumapp.json.subgroups.SubgroupJson>> getLiveDataListSubgroups() {
        return null;
    }
    
    public final int getAmountAttemptsToConnect() {
        return 0;
    }
    
    public final void setAmountAttemptsToConnect(int p0) {
    }
    
    public final int getAmountGrades() {
        return 0;
    }
    
    public final void setAmountGrades(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.lyceumapp.json.schools.SchoolJson getChosenSchool() {
        return null;
    }
    
    public final void setChosenSchool(@org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.schools.SchoolJson p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.lyceumapp.json.subgroups.SubgroupJson getChosenSubgroup() {
        return null;
    }
    
    public final void setChosenSubgroup(@org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.subgroups.SubgroupJson p0) {
    }
    
    private final void downloadSubgroups(int gradeId) {
    }
}