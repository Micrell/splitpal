package com.example.nomoola.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.fragment.ActivityFragment;
import com.example.nomoola.fragment.GroupFragment;


public class FragmentAdapter extends FragmentStateAdapter {

    private SubCategory subCategory;
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, SubCategory subCategory) {
        super(fragmentManager, lifecycle);
        this.subCategory = subCategory;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==1){
            return new GroupFragment();
        }
        return new ActivityFragment(this.subCategory);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
