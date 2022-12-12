package com.example.lyceumapp.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u0010B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\fH\u0014J\b\u0010\u000f\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/example/lyceumapp/activity/ChooseSubgroupActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/lyceumapp/activity/ChooseSubgroupActivity$ChooseSubgroupAdapter;", "chosenGrade", "Lcom/example/lyceumapp/json/grades/GradeJson;", "viewModel", "Lcom/example/lyceumapp/viewmodel/ChooseSubgroupViewModel;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "saveInShPreferencesAndStartMainMenuActivity", "ChooseSubgroupAdapter", "app_debug"})
public final class ChooseSubgroupActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.lyceumapp.viewmodel.ChooseSubgroupViewModel viewModel;
    private com.example.lyceumapp.json.grades.GradeJson chosenGrade;
    private com.example.lyceumapp.activity.ChooseSubgroupActivity.ChooseSubgroupAdapter adapter;
    
    public ChooseSubgroupActivity() {
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
    
    private final void saveInShPreferencesAndStartMainMenuActivity() {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B#\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eH\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/example/lyceumapp/activity/ChooseSubgroupActivity$ChooseSubgroupAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/lyceumapp/activity/ChooseSubgroupActivity$ChooseSubgroupAdapter$SubgroupJsonHolder;", "subgroups", "", "Lcom/example/lyceumapp/json/subgroups/SubgroupJson;", "inflater", "Landroid/view/LayoutInflater;", "viewModel", "Lcom/example/lyceumapp/viewmodel/ChooseSubgroupViewModel;", "(Ljava/util/List;Landroid/view/LayoutInflater;Lcom/example/lyceumapp/viewmodel/ChooseSubgroupViewModel;)V", "checkedRadioButton", "Landroid/widget/CompoundButton;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "SubgroupJsonHolder", "app_debug"})
    public static final class ChooseSubgroupAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.lyceumapp.activity.ChooseSubgroupActivity.ChooseSubgroupAdapter.SubgroupJsonHolder> {
        private final java.util.List<com.example.lyceumapp.json.subgroups.SubgroupJson> subgroups = null;
        private final android.view.LayoutInflater inflater = null;
        private final com.example.lyceumapp.viewmodel.ChooseSubgroupViewModel viewModel = null;
        private android.widget.CompoundButton checkedRadioButton;
        
        public ChooseSubgroupAdapter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.example.lyceumapp.json.subgroups.SubgroupJson> subgroups, @org.jetbrains.annotations.NotNull()
        android.view.LayoutInflater inflater, @org.jetbrains.annotations.NotNull()
        com.example.lyceumapp.viewmodel.ChooseSubgroupViewModel viewModel) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.example.lyceumapp.activity.ChooseSubgroupActivity.ChooseSubgroupAdapter.SubgroupJsonHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.lyceumapp.activity.ChooseSubgroupActivity.ChooseSubgroupAdapter.SubgroupJsonHolder holder, int position) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/lyceumapp/activity/ChooseSubgroupActivity$ChooseSubgroupAdapter$SubgroupJsonHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "bindingSubgroupElement", "Lcom/example/lyceumapp/databinding/RecyclerElementSubgroupsBinding;", "(Lcom/example/lyceumapp/databinding/RecyclerElementSubgroupsBinding;)V", "getBindingSubgroupElement", "()Lcom/example/lyceumapp/databinding/RecyclerElementSubgroupsBinding;", "app_debug"})
        public static final class SubgroupJsonHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull()
            private final com.example.lyceumapp.databinding.RecyclerElementSubgroupsBinding bindingSubgroupElement = null;
            
            public SubgroupJsonHolder(@org.jetbrains.annotations.NotNull()
            com.example.lyceumapp.databinding.RecyclerElementSubgroupsBinding bindingSubgroupElement) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.example.lyceumapp.databinding.RecyclerElementSubgroupsBinding getBindingSubgroupElement() {
                return null;
            }
        }
    }
}