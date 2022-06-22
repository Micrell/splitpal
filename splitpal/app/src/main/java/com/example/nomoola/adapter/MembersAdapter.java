package com.example.nomoola.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomoola.R;

import java.util.ArrayList;
import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MembersViewHolder> {

    private List<String> initials;

    public MembersAdapter(List<String> initials){
        this.initials=initials;
    }
    @NonNull
    @Override
    public MembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member , parent , false);
        return new MembersAdapter.MembersViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MembersViewHolder holder, int position) {
        holder.t1.setText(initials.get(position).substring(0,2).toUpperCase());
        setAvatar(initials.get(position), holder);
    }

    private String getColorCode(String inputString) {
        return String.format("#%06x", 0xFFFFFF & inputString.hashCode());
    }

    void setAvatar(String name, MembersViewHolder holder) {
        TextView txtName = holder.itemView.findViewById(R.id.member_initials);
        txtName.setText(name.substring(0, 2).toUpperCase());
        int hex = Color.parseColor(getColorCode(name));

        int r = (hex & 0xFF0000) >> 16;
        int g = (hex & 0xFF00) >> 8;
        int b = (hex & 0xFF);
        boolean isTooLight = (r * 0.299 + g * 0.587 + b * 0.114) > 186;
        txtName.setTextColor(isTooLight ? Color.BLACK : Color.WHITE);
        CardView card = holder.itemView.findViewById(R.id.card_avatar);

        card.setCardBackgroundColor(hex);
        Color.parseColor(getColorCode(name));

    }
    @Override
    public int getItemCount() {
        return initials.size();
    }

    public class MembersViewHolder extends RecyclerView.ViewHolder{

        private TextView t1;
        public MembersViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.member_initials);
        }
    }
}
