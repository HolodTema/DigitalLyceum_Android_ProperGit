package com.example.lyceumapp.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0019\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010\'\u001a\u00020(X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,\u00a8\u0006-"}, d2 = {"Lcom/example/lyceumapp/viewmodel/NoResponseViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "amountAttemptsToConnect", "", "(Landroid/app/Application;I)V", "getAmountAttemptsToConnect", "()I", "setAmountAttemptsToConnect", "(I)V", "amountGrades", "getAmountGrades", "()Ljava/lang/Integer;", "setAmountGrades", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "chosenGrade", "Lcom/example/lyceumapp/json/grades/GradeJson;", "getChosenGrade", "()Lcom/example/lyceumapp/json/grades/GradeJson;", "setChosenGrade", "(Lcom/example/lyceumapp/json/grades/GradeJson;)V", "chosenSchool", "Lcom/example/lyceumapp/json/schools/SchoolJson;", "getChosenSchool", "()Lcom/example/lyceumapp/json/schools/SchoolJson;", "setChosenSchool", "(Lcom/example/lyceumapp/json/schools/SchoolJson;)V", "liveDataCountDownTimerSeconds", "Landroidx/lifecycle/MutableLiveData;", "getLiveDataCountDownTimerSeconds", "()Landroidx/lifecycle/MutableLiveData;", "noResponseType", "Lcom/example/lyceumapp/Const$NoResponseType;", "getNoResponseType", "()Lcom/example/lyceumapp/Const$NoResponseType;", "setNoResponseType", "(Lcom/example/lyceumapp/Const$NoResponseType;)V", "subgroupInfo", "Lcom/example/lyceumapp/json/subgroups/SubgroupInfoJson;", "getSubgroupInfo", "()Lcom/example/lyceumapp/json/subgroups/SubgroupInfoJson;", "setSubgroupInfo", "(Lcom/example/lyceumapp/json/subgroups/SubgroupInfoJson;)V", "app_debug"})
public final class NoResponseViewModel extends androidx.lifecycle.AndroidViewModel {
    private int amountAttemptsToConnect;
    @org.jetbrains.annotations.NotNull()
    private com.example.lyceumapp.Const.NoResponseType noResponseType = com.example.lyceumapp.Const.NoResponseType.GetSchools;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> liveDataCountDownTimerSeconds = null;
    public com.example.lyceumapp.json.schools.SchoolJson chosenSchool;
    public com.example.lyceumapp.json.grades.GradeJson chosenGrade;
    public com.example.lyceumapp.json.subgroups.SubgroupInfoJson subgroupInfo;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer amountGrades;
    
    public NoResponseViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, int amountAttemptsToConnect) {
        super(null);
    }
    
    public final int getAmountAttemptsToConnect() {
        return 0;
    }
    
    public final void setAmountAttemptsToConnect(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.lyceumapp.Const.NoResponseType getNoResponseType() {
        return null;
    }
    
    public final void setNoResponseType(@org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.Const.NoResponseType p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Integer> getLiveDataCountDownTimerSeconds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.lyceumapp.json.schools.SchoolJson getChosenSchool() {
        return null;
    }
    
    public final void setChosenSchool(@org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.schools.SchoolJson p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.lyceumapp.json.grades.GradeJson getChosenGrade() {
        return null;
    }
    
    public final void setChosenGrade(@org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.grades.GradeJson p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.lyceumapp.json.subgroups.SubgroupInfoJson getSubgroupInfo() {
        return null;
    }
    
    public final void setSubgroupInfo(@org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.subgroups.SubgroupInfoJson p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getAmountGrades() {
        return null;
    }
    
    public final void setAmountGrades(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
}