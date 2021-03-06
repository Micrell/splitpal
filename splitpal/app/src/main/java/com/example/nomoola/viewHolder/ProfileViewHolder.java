package com.example.nomoola.viewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nomoola.R;
import com.example.nomoola.database.entity.Profile;
import com.example.nomoola.viewModel.ProfileViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileViewHolder extends RecyclerView.ViewHolder {

    private TextView userName;
    private TextInputLayout editUserNameLayout;
    private TextInputEditText editUserName;
    private TextView language_english;
    private TextView language_francais;
    private TextView currency_euro;
    private TextView currency_dollar;
    private Button button;

    private ProfileViewModel profileViewModel;
    private FragmentManager fragmentManager;
    private Profile profile;
    private View view;

    public ProfileViewHolder(View view, FragmentManager fragmentManager, ProfileViewModel profileViewModel){
        super(view);
        this.view = view;
        this.fragmentManager = fragmentManager;
        this.profileViewModel = profileViewModel;

        this.userName = view.findViewById(R.id.profile_name);
        this.editUserNameLayout = view.findViewById(R.id.name_text_input);
        this.editUserName = view.findViewById(R.id.name_editext);
        this.button = view.findViewById(R.id.button);


        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = editUserName.getText().toString();
                profileViewModel.setUsername(1, value);
            }
        });


    }
    public void bind(Profile profile){
        this.profile = profile;
        this.userName.setText(this.profile.getM_USERNAME());
        this.editUserName.setText(this.profile.getM_USERNAME());

    }
}
