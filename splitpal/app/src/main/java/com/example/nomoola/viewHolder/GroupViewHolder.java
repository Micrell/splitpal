package com.example.nomoola.viewHolder;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomoola.R;
import com.example.nomoola.database.entity.InOutCome;
import com.example.nomoola.database.entity.Profile;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.fragment.dialog.EditInOutComeDialog;
import com.example.nomoola.viewModel.InOutComeViewModel;
import com.example.nomoola.viewModel.ProfileViewModel;
import com.example.nomoola.viewModel.SubcategoryViewModel;

import org.w3c.dom.Text;

import java.time.format.DateTimeFormatter;

public class GroupViewHolder extends RecyclerView.ViewHolder{


    private InOutComeViewModel inOutComeViewModel;
    private View view;
    private TextView sumText;
    private SubCategory subcategory;

public GroupViewHolder(@NonNull View itemView, InOutComeViewModel inOutComeViewModel, SubCategory subCategory) {
        super(itemView);
        this.view = itemView;
        this.inOutComeViewModel = inOutComeViewModel;
        this.subcategory = subCategory;
        this.sumText = view.findViewById(R.id.item_balance_moneyOweAmount);
    }

    private String getColorCode(String inputString) {
        return String.format("#%06x", 0xFFFFFF & inputString.hashCode());
    }

    void setAvatar(String name) {
        CardView cardView = itemView.findViewById(R.id.card_avatar);
        TextView txtView = itemView.findViewById(R.id.txt_avatar);
        TextView txtName = itemView.findViewById(R.id.txt_name);
        txtView.setText(name.substring(0, 2).toUpperCase());
        txtName.setText(name);
        int hex = Color.parseColor(getColorCode(name));

        int r = (hex & 0xFF0000) >> 16;
        int g = (hex & 0xFF00) >> 8;
        int b = (hex & 0xFF);
        boolean isTooLight = (r * 0.299 + g * 0.587 + b * 0.114) > 186;
        txtView.setTextColor(isTooLight ? Color.BLACK : Color.WHITE);

        cardView.setCardBackgroundColor(hex);
        Color.parseColor(getColorCode(name));

    }
    public void bind(Profile profile){
        setAvatar(profile.getM_USERNAME());
        this.inOutComeViewModel.getToTtalExpense(profile.getM_PROFILE_ID(), this.subcategory.getM_SUBCAT_ID()).observe((LifecycleOwner) view.getContext(), new Observer<Double>() {
            @Override
            public void onChanged(Double sum) {
                sumText.setText("$"+sum);
            }
        });
    }

    public static GroupViewHolder create(ViewGroup parent, FragmentManager fragmentManager, InOutComeViewModel inOutComeViewModel, SubCategory subCategory){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        return new GroupViewHolder(view, inOutComeViewModel, subCategory);
    }

}
