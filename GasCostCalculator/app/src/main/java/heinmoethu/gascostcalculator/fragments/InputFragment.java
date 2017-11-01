package heinmoethu.gascostcalculator.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.math.BigDecimal;

import heinmoethu.gascostcalculator.R;
import heinmoethu.gascostcalculator.activities.MainActivity;
import heinmoethu.gascostcalculator.models.InputModel;

/**
 * Created by Hein Moe Thu on 10/19/2017.
 * Assignemnt: mpg_fragments
 * Class: CPSC 356
 */

public class InputFragment extends Fragment {
    EditText m,p,l;
    private InputModel input;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_input,container,false);
        this.m = v.findViewById(R.id.et_mpg);
        this.p = v.findViewById(R.id.et_ppg);
        this.l = v.findViewById(R.id.et_len);

        try {//this can fail since new InputFragment does not have argument
            this.input = getArguments().getParcelable("InputModel"); //this will succeed if the call come from orientation change
            //updating the texts
            setViewValues(m, input.getMpg());
            setViewValues(p, input.getPpg());
            setViewValues(l, input.getLength());
        }
        catch(Exception e){
            input=new InputModel();
        }

        //Updating the InputModel as text changes
        this.m.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (m.getText().toString().equals("")){
                    input.setMpg(0);
                }
                else{
                    input.setMpg(Double.parseDouble(m.getText().toString()));
                }
            }
        });
        this.p.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (p.getText().toString().equals("")){
                    input.setPpg(0);
                }
                else{
                    input.setPpg(Double.parseDouble(p.getText().toString()));
                }
            }
        });
        this.l.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (l.getText().toString().equals("")){
                    input.setLength(0);
                }
                else{
                    input.setLength(Double.parseDouble(l.getText().toString()));
                }
            }
        });

        return v;
    }
    public InputModel getInputModel(){//return the data to activity
        return this.input;
    }
    public void setViewValues(EditText v, double d){
        if (d!=0){//will update values other than 0 (0 is blank)
            BigDecimal bd = new BigDecimal(Double.toString(d));
            v.setText(bd.stripTrailingZeros().toPlainString());//used to rid of trailing zero when orientation change
            //v.setText(String.format(getString(R.string.just_double),bd.stripTrailingZeros()));
        }
    }
}