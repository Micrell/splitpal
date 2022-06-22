package com.example.nomoola.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nomoola.R;
import com.example.nomoola.adapter.InOutComeAdapter;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.viewModel.InOutComeViewModel;

public class ActivityFragment extends Fragment {

    private InOutComeViewModel mInOutViewModel;
    private InOutComeAdapter inOutComeAdapter;
    private RecyclerView inOutComeRecyclerView;
    private SubCategory subCategory;


    public ActivityFragment(){
        super();
        this.subCategory = new SubCategory();
    }

    public ActivityFragment(SubCategory subCategory){
        super();
        this.subCategory = subCategory;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        this.mInOutViewModel = new ViewModelProvider(this).get(InOutComeViewModel.class);
        this.inOutComeAdapter = new InOutComeAdapter(new InOutComeAdapter.InOutComeDiff(), this.getParentFragmentManager());

        View view= inflater.inflate(R.layout.fragment_activity, container, false);


        this.inOutComeRecyclerView = view.findViewById(R.id.inOutCome_recyclerView);
        this.inOutComeRecyclerView.setAdapter(this.inOutComeAdapter);


        mInOutViewModel.getInOutComeOf(subCategory.getM_SUBCAT_ID()).observe(getViewLifecycleOwner(), subCategories -> {
            inOutComeAdapter.submitList(subCategories);
        });

        return view;
    }
}