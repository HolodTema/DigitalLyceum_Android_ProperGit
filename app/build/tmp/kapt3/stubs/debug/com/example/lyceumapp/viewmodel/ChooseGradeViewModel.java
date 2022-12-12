package com.example.lyceumapp.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0002R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001f\u0010\u0013\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00150\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u001b"}, d2 = {"Lcom/example/lyceumapp/viewmodel/ChooseGradeViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "chosenSchool", "Lcom/example/lyceumapp/json/schools/SchoolJson;", "(Landroid/app/Application;Lcom/example/lyceumapp/json/schools/SchoolJson;)V", "amountAttemptsToConnect", "", "getAmountAttemptsToConnect", "()I", "setAmountAttemptsToConnect", "(I)V", "chosenGrade", "Lcom/example/lyceumapp/json/grades/GradeJson;", "getChosenGrade", "()Lcom/example/lyceumapp/json/grades/GradeJson;", "setChosenGrade", "(Lcom/example/lyceumapp/json/grades/GradeJson;)V", "liveDataListGrades", "Landroidx/lifecycle/MutableLiveData;", "", "getLiveDataListGrades", "()Landroidx/lifecycle/MutableLiveData;", "downloadGrades", "", "schoolId", "app_debug"})
public final class ChooseGradeViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.lyceumapp.json.grades.GradeJson>> liveDataListGrades = null;
    private int amountAttemptsToConnect = 1;
    public com.example.lyceumapp.json.grades.GradeJson chosenGrade;
    
    public ChooseGradeViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.schools.SchoolJson chosenSchool) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.example.lyceumapp.json.grades.GradeJson>> getLiveDataListGrades() {
        return null;
    }
    
    public final int getAmountAttemptsToConnect() {
        return 0;
    }
    
    public final void setAmountAttemptsToConnect(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.lyceumapp.json.grades.GradeJson getChosenGrade() {
        return null;
    }
    
    public final void setChosenGrade(@org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.grades.GradeJson p0) {
    }
    
    private final void downloadGrades(int schoolId) {
    }
}