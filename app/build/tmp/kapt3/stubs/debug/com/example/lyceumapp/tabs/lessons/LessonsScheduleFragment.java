package com.example.lyceumapp.tabs.lessons;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0012"}, d2 = {"Lcom/example/lyceumapp/tabs/lessons/LessonsScheduleFragment;", "Landroidx/fragment/app/Fragment;", "()V", "viewModel", "Lcom/example/lyceumapp/viewmodel/MainMenuViewModel;", "getViewModel", "()Lcom/example/lyceumapp/viewmodel/MainMenuViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "LessonsAdapter", "app_debug"})
public final class LessonsScheduleFragment extends androidx.fragment.app.Fragment {
    private final kotlin.Lazy viewModel$delegate = null;
    
    public LessonsScheduleFragment() {
        super();
    }
    
    private final com.example.lyceumapp.viewmodel.MainMenuViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0015B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u0018\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/example/lyceumapp/tabs/lessons/LessonsScheduleFragment$LessonsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/lyceumapp/tabs/lessons/LessonsScheduleFragment$LessonsAdapter$LessonHolder;", "inflater", "Landroid/view/LayoutInflater;", "lessons", "", "Lcom/example/lyceumapp/json/lessons/LessonJson;", "strTime", "", "(Landroid/view/LayoutInflater;Ljava/util/List;Ljava/lang/String;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "LessonHolder", "app_debug"})
    public static final class LessonsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.lyceumapp.tabs.lessons.LessonsScheduleFragment.LessonsAdapter.LessonHolder> {
        private final android.view.LayoutInflater inflater = null;
        private final java.util.List<com.example.lyceumapp.json.lessons.LessonJson> lessons = null;
        private final java.lang.String strTime = null;
        
        public LessonsAdapter(@org.jetbrains.annotations.NotNull()
        android.view.LayoutInflater inflater, @org.jetbrains.annotations.NotNull()
        java.util.List<com.example.lyceumapp.json.lessons.LessonJson> lessons, @org.jetbrains.annotations.NotNull()
        java.lang.String strTime) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.example.lyceumapp.tabs.lessons.LessonsScheduleFragment.LessonsAdapter.LessonHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.lyceumapp.tabs.lessons.LessonsScheduleFragment.LessonsAdapter.LessonHolder holder, int position) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/lyceumapp/tabs/lessons/LessonsScheduleFragment$LessonsAdapter$LessonHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/lyceumapp/databinding/LessonInScheduleBinding;", "(Lcom/example/lyceumapp/databinding/LessonInScheduleBinding;)V", "getBinding", "()Lcom/example/lyceumapp/databinding/LessonInScheduleBinding;", "app_debug"})
        public static final class LessonHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull()
            private final com.example.lyceumapp.databinding.LessonInScheduleBinding binding = null;
            
            public LessonHolder(@org.jetbrains.annotations.NotNull()
            com.example.lyceumapp.databinding.LessonInScheduleBinding binding) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.example.lyceumapp.databinding.LessonInScheduleBinding getBinding() {
                return null;
            }
        }
    }
}