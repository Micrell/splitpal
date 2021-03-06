package com.example.nomoola.viewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nomoola.R;
import com.example.nomoola.database.entity.InOutCome;
import com.example.nomoola.fragment.dialog.EditInOutComeDialog;
import com.example.nomoola.viewModel.SubcategoryViewModel;

import java.time.format.DateTimeFormatter;

public class InOutComeViewHolder extends RecyclerView.ViewHolder {

    private TextView dateMonth, dateDay, dateYear, nameView, amountView, ownerName;
    private ImageButton editButton;

    private FragmentManager fragmentManager;
    private SubcategoryViewModel subcategoryViewModel;
    private InOutCome inOutCome;
    private View view;

    public InOutComeViewHolder(@NonNull View view, FragmentManager fragmentManager, SubcategoryViewModel subcategoryViewModel) {
        super(view);
        this.fragmentManager = fragmentManager;
        this.subcategoryViewModel = subcategoryViewModel;
        this.view = view;

        this.nameView = view.findViewById(R.id.item_inoutcome_name);
        this.dateMonth = view.findViewById(R.id.item_inoutcome_dateMonth);
        this.dateDay = view.findViewById(R.id.item_inoutcome_dateDay);
        this.dateYear = view.findViewById(R.id.item_inoutcome_dateYear);
        this.amountView = view.findViewById(R.id.item_inoutcome_amount);
        this.editButton = view.findViewById(R.id.item_inoutcome_editButton);
        this.ownerName = view.findViewById(R.id.item_subcat_ownerName);

    }

    public void bind(InOutCome inOutCome){
        this.inOutCome = inOutCome;
        this.nameView.setText(this.inOutCome.getM_INOUTCOME_NAME());
        this.dateMonth.setText(this.inOutCome.getM_INOUTCOME_DATE().format(DateTimeFormatter.ofPattern("MMM")));
        this.dateDay.setText(String.valueOf(this.inOutCome.getM_INOUTCOME_DATE().getDayOfMonth()));
        this.dateYear.setText(String.valueOf(this.inOutCome.getM_INOUTCOME_DATE().getYear()));
        this.amountView.setText(this.inOutCome.getM_INOUTCOME_AMOUNT() + "???");
        this.subcategoryViewModel.getProfileNameAccordingToID(this.inOutCome.getM_INOUTCOME_OWNER_ID()).observe((LifecycleOwner) this.view.getContext(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ownerName.setText(s);
            }
        });

        this.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditInOutComeDialog dialog = new EditInOutComeDialog(inOutCome);
                dialog.show(fragmentManager, "editCome_dialog");
            }
        });
    }

    public static InOutComeViewHolder create(ViewGroup parent, FragmentManager fragmentManager, SubcategoryViewModel subcategoryViewModel){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_in_out_come, parent, false);
        return new InOutComeViewHolder(view, fragmentManager, subcategoryViewModel);
    }
}
