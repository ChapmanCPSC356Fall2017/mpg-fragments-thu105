package heinmoethu.gascostcalculator.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import heinmoethu.gascostcalculator.R;
import heinmoethu.gascostcalculator.models.InputModel;

/**
 * Created by Hein Moe Thu on 10/20/2017.
 * Assignemnt: mpg_fragments
 * Class: CPSC 356
 */

public class OutputFragment extends Fragment {
    TextView c, t;
    CheckBox cb;
    boolean eco;
    InputModel input;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_output,container,false);
        c = v.findViewById(R.id.tv_cpm);
        t = v.findViewById(R.id.tv_tc);
        cb = v.findViewById(R.id.cb_eco);
        input=this.getArguments().getParcelable("InputModel");
        eco=this.getArguments().getBoolean("IsEco");
        cb.setChecked(eco);
        setValues();

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                eco=b;
                setValues();
            }
        });

        return v;
    }
    public void setValues(){//calculates result based on InputModel data
        double mpg = input.getMpg();
        if (eco){//add 5 mpg if eco mode
            mpg+=5;
        }

        if (mpg!=0){//to avoid division by 0 problem
            double cpm = input.getPpg()/mpg;
            c.setText(String.format(getString(R.string.dollar), cpm));
            t.setText(String.format(getString(R.string.dollar), cpm*input.getLength()));
        }
        else{
            c.setText(R.string.mpg0);
            t.setText(R.string.mpg0);
        }
    }
    public boolean isEco(){//to pass back data to activity
        return eco;
    }
}
