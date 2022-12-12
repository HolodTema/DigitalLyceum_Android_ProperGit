package com.example.lyceumapp.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010.\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010/\u001a\u00020\bJ\u0011\u00100\u001a\b\u0012\u0004\u0012\u00020201\u00a2\u0006\u0002\u00103J\b\u00104\u001a\u000205H\u0014J \u00106\u001a\u0002052\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u001c2\u0006\u0010:\u001a\u00020\"H\u0002J\u0010\u0010;\u001a\u0002052\b\b\u0001\u0010<\u001a\u00020\bJ\u001c\u0010=\u001a\u0002052\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010?\u001a\u00020\bR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R-\u0010\u001e\u001a\u001e\u0012\u001a\u0012\u0018\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001f0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R%\u0010!\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\"\u0018\u00010\u001f0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u001f\u0010$\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020%\u0018\u00010\u001b0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u0019\u0010\'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010(0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006@"}, d2 = {"Lcom/example/lyceumapp/viewmodel/MainMenuViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "subgroupInfo", "Lcom/example/lyceumapp/json/subgroups/SubgroupInfoJson;", "(Landroid/app/Application;Lcom/example/lyceumapp/json/subgroups/SubgroupInfoJson;)V", "amountAttemptsToConnect", "", "getAmountAttemptsToConnect", "()I", "setAmountAttemptsToConnect", "(I)V", "chosenNavMenuItemId", "getChosenNavMenuItemId", "setChosenNavMenuItemId", "hasFirstActivityLaunchHappened", "", "getHasFirstActivityLaunchHappened", "()Z", "setHasFirstActivityLaunchHappened", "(Z)V", "liveDataChosenNavViewItemId", "Landroidx/lifecycle/MutableLiveData;", "getLiveDataChosenNavViewItemId", "()Landroidx/lifecycle/MutableLiveData;", "liveDataLessonsForDefiniteDay", "", "Lcom/example/lyceumapp/json/lessons/LessonJson;", "getLiveDataLessonsForDefiniteDay", "liveDataLessonsForSubgroup", "Lkotlin/Pair;", "getLiveDataLessonsForSubgroup", "liveDataNextLessonAndTimeToIt", "Lcom/example/lyceumapp/RequestManager$DeltaTime;", "getLiveDataNextLessonAndTimeToIt", "liveDataTeachers", "Lcom/example/lyceumapp/json/teachers/TeacherJson;", "getLiveDataTeachers", "liveDataTodaySchedule", "Lcom/example/lyceumapp/json/subgroups/SubgroupTodayScheduleJson;", "getLiveDataTodaySchedule", "getSubgroupInfo", "()Lcom/example/lyceumapp/json/subgroups/SubgroupInfoJson;", "timerNextLesson", "Landroid/os/CountDownTimer;", "getLessonsForDefiniteWeek", "week", "getWeekNamesForSubgroup", "", "", "()[Ljava/lang/String;", "onCleared", "", "startNextLessonTimer", "beginMills", "", "lesson", "deltaTime", "updateChosenNavViewItemId", "itemId", "updateLessonsForDefiniteDay", "lessons", "day", "app_debug"})
public final class MainMenuViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.lyceumapp.json.subgroups.SubgroupInfoJson subgroupInfo = null;
    private android.os.CountDownTimer timerNextLesson;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<kotlin.Pair<java.util.List<com.example.lyceumapp.json.lessons.LessonJson>, java.lang.Boolean>> liveDataLessonsForSubgroup = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.lyceumapp.json.subgroups.SubgroupTodayScheduleJson> liveDataTodaySchedule = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<kotlin.Pair<com.example.lyceumapp.json.lessons.LessonJson, com.example.lyceumapp.RequestManager.DeltaTime>> liveDataNextLessonAndTimeToIt = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.lyceumapp.json.lessons.LessonJson>> liveDataLessonsForDefiniteDay = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> liveDataChosenNavViewItemId = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.lyceumapp.json.teachers.TeacherJson>> liveDataTeachers = null;
    private int amountAttemptsToConnect = 1;
    private int chosenNavMenuItemId = com.example.lyceumapp.R.id.menuItemMain;
    private boolean hasFirstActivityLaunchHappened = false;
    
    public MainMenuViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.example.lyceumapp.json.subgroups.SubgroupInfoJson subgroupInfo) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.lyceumapp.json.subgroups.SubgroupInfoJson getSubgroupInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<kotlin.Pair<java.util.List<com.example.lyceumapp.json.lessons.LessonJson>, java.lang.Boolean>> getLiveDataLessonsForSubgroup() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.example.lyceumapp.json.subgroups.SubgroupTodayScheduleJson> getLiveDataTodaySchedule() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<kotlin.Pair<com.example.lyceumapp.json.lessons.LessonJson, com.example.lyceumapp.RequestManager.DeltaTime>> getLiveDataNextLessonAndTimeToIt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.example.lyceumapp.json.lessons.LessonJson>> getLiveDataLessonsForDefiniteDay() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Integer> getLiveDataChosenNavViewItemId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.example.lyceumapp.json.teachers.TeacherJson>> getLiveDataTeachers() {
        return null;
    }
    
    public final int getAmountAttemptsToConnect() {
        return 0;
    }
    
    public final void setAmountAttemptsToConnect(int p0) {
    }
    
    public final int getChosenNavMenuItemId() {
        return 0;
    }
    
    public final void setChosenNavMenuItemId(int p0) {
    }
    
    public final boolean getHasFirstActivityLaunchHappened() {
        return false;
    }
    
    public final void setHasFirstActivityLaunchHappened(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.lyceumapp.json.lessons.LessonJson> getLessonsForDefiniteWeek(int week) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String[] getWeekNamesForSubgroup() {
        return null;
    }
    
    public final void updateChosenNavViewItemId(@androidx.annotation.IdRes()
    int itemId) {
    }
    
    public final void updateLessonsForDefiniteDay(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.lyceumapp.json.lessons.LessonJson> lessons, int day) {
    }
    
    private final void startNextLessonTimer(long beginMills, com.example.lyceumapp.json.lessons.LessonJson lesson, com.example.lyceumapp.RequestManager.DeltaTime deltaTime) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}