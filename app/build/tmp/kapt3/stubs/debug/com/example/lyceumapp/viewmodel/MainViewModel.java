package com.example.lyceumapp.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u001a\u001a\u00020\u001bH\u0002J&\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00052\u0014\u0010\u001e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0012\u0004\u0012\u00020\u001b0\u001fH\u0002J\u001e\u0010 \u001a\u00020\u001b2\u0014\u0010\u001e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u001b0\u001fH\u0002R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001f\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00140\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/lyceumapp/viewmodel/MainViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "modeToOpenShPreferences", "", "(Landroid/app/Application;I)V", "amountAttemptsToConnect", "getAmountAttemptsToConnect", "()I", "setAmountAttemptsToConnect", "(I)V", "chosenSchool", "Lcom/example/lyceumapp/json/schools/SchoolJson;", "getChosenSchool", "()Lcom/example/lyceumapp/json/schools/SchoolJson;", "setChosenSchool", "(Lcom/example/lyceumapp/json/schools/SchoolJson;)V", "liveDataListSchools", "Landroidx/lifecycle/MutableLiveData;", "", "getLiveDataListSchools", "()Landroidx/lifecycle/MutableLiveData;", "liveDataWeNeedToStartMainMenuActivity", "Lcom/example/lyceumapp/json/subgroups/SubgroupInfoJson;", "getLiveDataWeNeedToStartMainMenuActivity", "downloadSchools", "", "downloadSubgroupInfo", "subgroupId", "listener", "Lkotlin/Function1;", "getShPreferencesData", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.AndroidViewModel {
    private final int modeToOpenShPreferences = 0;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.lyceumapp.json.schools.SchoolJson>> liveDataListSchools = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.lyceumapp.json.subgroups.SubgroupInfoJson> liveDataWeNeedToStartMainMenuActivity = null;
    private int amountAttemptsToConnect = 1;
    public com.example.lyceumapp.json.schools.SchoolJson chosenSchool;
    
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, int modeToOpenShPreferences) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.example.lyceumapp.json.schools.SchoolJson>> getLiveDataListSchools() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.example.lyceumapp.json.subgroups.SubgroupInfoJson> getLiveDataWeNeedToStartMainMenuActivity() {
        return null;
    }
    
    public final int getAmountAttemptsToConnect() {
        return 0;
    }
    
    public final void setAmountAttemptsToConnect(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.lyceumapp.json.schools.SchoolJson getChosenSchool() {
        return null;
    }
    
    public final void setChosenSchool(@org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.schools.SchoolJson p0) {
    }
    
    private final void getShPreferencesData(kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> listener) {
    }
    
    private final void downloadSchools() {
    }
    
    private final void downloadSubgroupInfo(int subgroupId, kotlin.jvm.functions.Function1<? super com.example.lyceumapp.json.subgroups.SubgroupInfoJson, kotlin.Unit> listener) {
    }
}