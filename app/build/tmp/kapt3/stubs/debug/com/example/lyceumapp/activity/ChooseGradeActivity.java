package com.example.lyceumapp.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/example/lyceumapp/activity/ChooseGradeActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/lyceumapp/activity/ChooseGradeActivity$ChooseGradeAdapter;", "chosenSchool", "Lcom/example/lyceumapp/json/schools/SchoolJson;", "viewModel", "Lcom/example/lyceumapp/viewmodel/ChooseGradeViewModel;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "ChooseGradeAdapter", "app_debug"})
public final class ChooseGradeActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.lyceumapp.viewmodel.ChooseGradeViewModel viewModel;
    private com.example.lyceumapp.json.schools.SchoolJson chosenSchool;
    private com.example.lyceumapp.activity.ChooseGradeActivity.ChooseGradeAdapter adapter;
    
    public ChooseGradeActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0015B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u0018\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/example/lyceumapp/activity/ChooseGradeActivity$ChooseGradeAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/lyceumapp/activity/ChooseGradeActivity$ChooseGradeAdapter$GradeJsonHolder;", "grades", "", "Lcom/example/lyceumapp/json/grades/GradeJson;", "viewModel", "Lcom/example/lyceumapp/viewmodel/ChooseGradeViewModel;", "(Ljava/util/List;Lcom/example/lyceumapp/viewmodel/ChooseGradeViewModel;)V", "checkedRadioButton", "Landroid/widget/CompoundButton;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "GradeJsonHolder", "app_debug"})
    public static final class ChooseGradeAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.lyceumapp.activity.ChooseGradeActivity.ChooseGradeAdapter.GradeJsonHolder> {
        private final java.util.List<com.example.lyceumapp.json.grades.GradeJson> grades = null;
        private final com.example.lyceumapp.viewmodel.ChooseGradeViewModel viewModel = null;
        private android.widget.CompoundButton checkedRadioButton;
        
        public ChooseGradeAdapter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.example.lyceumapp.json.grades.GradeJson> grades, @org.jetbrains.annotations.NotNull()
        com.example.lyceumapp.viewmodel.ChooseGradeViewModel viewModel) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.example.lyceumapp.activity.ChooseGradeActivity.ChooseGradeAdapter.GradeJsonHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.lyceumapp.activity.ChooseGradeActivity.ChooseGradeAdapter.GradeJsonHolder holder, int position) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/lyceumapp/activity/ChooseGradeActivity$ChooseGradeAdapter$GradeJsonHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "bindingGradeElement", "Lcom/example/lyceumapp/databinding/RecyclerElementGradesBinding;", "(Lcom/example/lyceumapp/databinding/RecyclerElementGradesBinding;)V", "getBindingGradeElement", "()Lcom/example/lyceumapp/databinding/RecyclerElementGradesBinding;", "app_debug"})
        public static final class GradeJsonHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull()
            private final com.example.lyceumapp.databinding.RecyclerElementGradesBinding bindingGradeElement = null;
            
            public GradeJsonHolder(@org.jetbrains.annotations.NotNull()
            com.example.lyceumapp.databinding.RecyclerElementGradesBinding bindingGradeElement) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.example.lyceumapp.databinding.RecyclerElementGradesBinding getBindingGradeElement() {
                return null;
            }
        }
    }
}