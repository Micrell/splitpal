package com.example.nomoola.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.nomoola.R;
import com.example.nomoola.adapter.FragmentAdapter;
import com.example.nomoola.adapter.InOutComeAdapter;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.fragment.dialog.AddCategoryDialog;
import com.example.nomoola.fragment.dialog.AddInOutComeDialog;
import com.example.nomoola.viewModel.InOutComeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class InOutComeFragment extends Fragment {

    private SubCategory subCategory;
    private FloatingActionButton addCome;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private FragmentAdapter adapter;


    private RecyclerView inOutComeRecyclerView;

    public InOutComeFragment(){
        super();
        this.subCategory = new SubCategory();
    }

    public InOutComeFragment(SubCategory subCategory){
        super();
        this.subCategory = subCategory;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_in_out_come, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2= view.findViewById(R.id.child_fragment_container);

        tabLayout.addTab(tabLayout.newTab().setText("Activity"));
        tabLayout.addTab(tabLayout.newTab().setText("Group"));

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        adapter= new FragmentAdapter(fragmentManager,getLifecycle(),this.subCategory);
        viewPager2.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


        this.addCome = view.findViewById(R.id.fragment_come_addInOutCome);
        this.addCome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddInOutComeDialog dialog = new AddInOutComeDialog();
                dialog.show(getParentFragmentManager(), "inOutCome_dialog");
            }
        });

        return view;
    }
}
