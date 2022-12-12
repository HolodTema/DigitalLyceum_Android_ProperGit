package com.example.lyceumapp.fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016\u00a8\u0006\f"}, d2 = {"Lcom/example/lyceumapp/fragment/ScheduleFragment;", "Landroidx/fragment/app/Fragment;", "()V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "LessonsTabsPagerAdapter", "app_debug"})
public final class ScheduleFragment extends androidx.fragment.app.Fragment {
    
    public ScheduleFragment() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0002\u0010\u000bJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/lyceumapp/fragment/ScheduleFragment$LessonsTabsPagerAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "viewModel", "Lcom/example/lyceumapp/viewmodel/MainMenuViewModel;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "lessons", "", "Lcom/example/lyceumapp/json/lessons/LessonJson;", "(Lcom/example/lyceumapp/viewmodel/MainMenuViewModel;Landroidx/fragment/app/FragmentManager;Landroidx/lifecycle/Lifecycle;Ljava/util/List;)V", "getLessons", "()Ljava/util/List;", "setLessons", "(Ljava/util/List;)V", "createFragment", "Landroidx/fragment/app/Fragment;", "position", "", "getItemCount", "app_debug"})
    public static final class LessonsTabsPagerAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {
        private com.example.lyceumapp.viewmodel.MainMenuViewModel viewModel;
        @org.jetbrains.annotations.NotNull()
        private java.util.List<com.example.lyceumapp.json.lessons.LessonJson> lessons;
        
        public LessonsTabsPagerAdapter(@org.jetbrains.annotations.NotNull()
        com.example.lyceumapp.viewmodel.MainMenuViewModel viewModel, @org.jetbrains.annotations.NotNull()
        androidx.fragment.app.FragmentManager fragmentManager, @org.jetbrains.annotations.NotNull()
        androidx.lifecycle.Lifecycle lifecycle, @org.jetbrains.annotations.NotNull()
        java.util.List<com.example.lyceumapp.json.lessons.LessonJson> lessons) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.example.lyceumapp.json.lessons.LessonJson> getLessons() {
            return null;
        }
        
        public final void setLessons(@org.jetbrains.annotations.NotNull()
        java.util.List<com.example.lyceumapp.json.lessons.LessonJson> p0) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public androidx.fragment.app.Fragment createFragment(int position) {
            return null;
        }
    }
}