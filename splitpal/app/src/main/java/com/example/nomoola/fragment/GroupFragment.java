package com.example.nomoola.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nomoola.R;
import com.example.nomoola.adapter.GroupAdapter;
import com.example.nomoola.adapter.InOutComeAdapter;
import com.example.nomoola.database.entity.Profile;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.viewModel.InOutComeViewModel;

import java.util.ArrayList;
import java.util.List;


public class GroupFragment extends Fragment {

    private InOutComeViewModel mInOutViewModel;
    private GroupAdapter groupAdapter;
    private RecyclerView recyclerView;
    private SubCategory subCategory;


    public GroupFragment() {
        super();
        this.subCategory = new SubCategory();
    }

    public GroupFragment(SubCategory subCategory){
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
        this.groupAdapter = new GroupAdapter(new GroupAdapter.GroupDiff(), this.getParentFragmentManager(), this.mInOutViewModel);
        View view= inflater.inflate(R.layout.fragment_group, container, false);


        this.recyclerView = view.findViewById(R.id.equilibre_recyclerView);
        this.recyclerView.setAdapter(this.groupAdapter);

        mInOutViewModel.getProfileFromSubCat(this.subCategory.getM_SUBCAT_ID()).observe(getViewLifecycleOwner(), profiles -> {
            Log.d("Profile", String.valueOf(profiles.size()));
            groupAdapter.submitList(profiles);
        });

        return view;
    }
}