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
 */

public class OutputFragment extends Fragment {
    TextView c, t;
    CheckBox cb;
    InputModel input;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_output,container,false);
        c = v.findViewById(R.id.tv_cpm);
        t = v.findViewById(R.id.tv_tc);
        cb = v.findViewById(R.id.cb_eco);
        input=this.getArguments().getParcelable("InputModel");
        setValues(false);

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setValues(b);
            }
        });

        return v;
    }
    public void setValues(Boolean isEco){
        double mpg = input.getMpg();
        double cpm;
        if (isEco){
            mpg+=5;
        }
        if (mpg!=0){
            cpm = input.getPpg()/mpg;
            c.setText(String.format(getString(R.string.dollar), cpm));
            t.setText(String.format(getString(R.string.dollar), cpm*input.getLength()));
        }
        else{
            c.setText(R.string.error_mpg0);
            t.setText(R.string.error_mpg0);
        }
    }
}
