package com.example.nomoola.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.nomoola.database.dao.ProfileDAO;
import com.example.nomoola.database.entity.InOutCome;
import com.example.nomoola.database.entity.Profile;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.viewHolder.GroupViewHolder;
import com.example.nomoola.viewHolder.InOutComeViewHolder;
import com.example.nomoola.viewHolder.SubcategoryViewHolder;
import com.example.nomoola.viewModel.InOutComeViewModel;
import com.example.nomoola.viewModel.SubcategoryViewModel;

public class GroupAdapter extends ListAdapter<Profile, GroupViewHolder> {

    private FragmentManager fragmentManager;
    private InOutComeViewModel inOutComeViewModel;
    private SubCategory subCategory;

    public GroupAdapter(@NonNull DiffUtil.ItemCallback<Profile> diffCallback, FragmentManager fragmentManager, InOutComeViewModel intOutComeViewModel, SubCategory subCategory) {
        super(diffCallback);
        this.inOutComeViewModel = intOutComeViewModel;
        this.fragmentManager = fragmentManager;
        this.subCategory = subCategory;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return GroupViewHolder.create(parent, this.fragmentManager, this.inOutComeViewModel, this.subCategory);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        Profile currentProfile = getItem(position);
        holder.bind(currentProfile);
    }

    public static class GroupDiff extends DiffUtil.ItemCallback<Profile> {

        @Override
        public boolean areItemsTheSame(@NonNull Profile oldItem, @NonNull Profile newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Profile oldItem, @NonNull Profile newItem) {
            return oldItem.getM_PROFILE_ID() == newItem.getM_PROFILE_ID();
        }
    }
}
