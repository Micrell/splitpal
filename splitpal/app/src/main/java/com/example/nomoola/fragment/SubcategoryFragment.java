package com.example.nomoola.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomoola.R;
import com.example.nomoola.adapter.SubcategoryAdapter;
import com.example.nomoola.fragment.dialog.AddSubCategoryDialog;
import com.example.nomoola.viewModel.SubcategoryViewModel;

public class SubcategoryFragment extends Fragment {

    private SubcategoryViewModel mSubcategoryViewModel;
    private RecyclerView subcategoryRecyclerView;
    private SubcategoryAdapter subcategoryAdapter;
    private AppCompatButton addSubcatButton;

    public SubcategoryFragment(){
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("CREATION", "onCreateView from " + this.getClass().toString() + " started");
        super.onCreateView(inflater, container, savedInstanceState);
        this.mSubcategoryViewModel = new ViewModelProvider(this).get(SubcategoryViewModel.class);
        this.subcategoryAdapter = new SubcategoryAdapter(new SubcategoryAdapter.SubcategoryDiff(),
                this.getParentFragmentManager(),
                this.mSubcategoryViewModel);
        View view = inflater.inflate(R.layout.fragment_subcategory, container, false);

        this.subcategoryRecyclerView = view.findViewById(R.id.subcategory_recyclerView);
        this.subcategoryRecyclerView.setAdapter(this.subcategoryAdapter);

        this.addSubcatButton = view.findViewById(R.id.subcategory_addSubcategory_button);
        this.addSubcatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSubCategoryDialog addSubCategoryDialog = new AddSubCategoryDialog();
                addSubCategoryDialog.show(getParentFragmentManager(), "AddSubCat_dialog");
            }
        });


        mSubcategoryViewModel.getmAllSubCategories().observe(getViewLifecycleOwner(), subCategories -> {
            subcategoryAdapter.submitList(subCategories);
        });

        Log.d("CREATION", "onCreateView from " + this.getClass().toString() + " finished");
        return view;
    }
}
