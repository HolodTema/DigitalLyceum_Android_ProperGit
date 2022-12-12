package com.example.lyceumapp.fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016\u00a8\u0006\f"}, d2 = {"Lcom/example/lyceumapp/fragment/TeachersFragment;", "Landroidx/fragment/app/Fragment;", "()V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "TeacherAdapter", "app_debug"})
public final class TeachersFragment extends androidx.fragment.app.Fragment {
    
    public TeachersFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/example/lyceumapp/fragment/TeachersFragment$TeacherAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/lyceumapp/fragment/TeachersFragment$TeacherAdapter$Holder;", "teachers", "", "Lcom/example/lyceumapp/json/teachers/TeacherJson;", "inflater", "Landroid/view/LayoutInflater;", "(Ljava/util/List;Landroid/view/LayoutInflater;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Holder", "app_debug"})
    public static final class TeacherAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.lyceumapp.fragment.TeachersFragment.TeacherAdapter.Holder> {
        private final java.util.List<com.example.lyceumapp.json.teachers.TeacherJson> teachers = null;
        private final android.view.LayoutInflater inflater = null;
        
        public TeacherAdapter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.example.lyceumapp.json.teachers.TeacherJson> teachers, @org.jetbrains.annotations.NotNull()
        android.view.LayoutInflater inflater) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.example.lyceumapp.fragment.TeachersFragment.TeacherAdapter.Holder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.lyceumapp.fragment.TeachersFragment.TeacherAdapter.Holder holder, int position) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/lyceumapp/fragment/TeachersFragment$TeacherAdapter$Holder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/lyceumapp/databinding/RecyclerElementTeachersBinding;", "(Lcom/example/lyceumapp/databinding/RecyclerElementTeachersBinding;)V", "getBinding", "()Lcom/example/lyceumapp/databinding/RecyclerElementTeachersBinding;", "app_debug"})
        public static final class Holder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull()
            private final com.example.lyceumapp.databinding.RecyclerElementTeachersBinding binding = null;
            
            public Holder(@org.jetbrains.annotations.NotNull()
            com.example.lyceumapp.databinding.RecyclerElementTeachersBinding binding) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.example.lyceumapp.databinding.RecyclerElementTeachersBinding getBinding() {
                return null;
            }
        }
    }
}