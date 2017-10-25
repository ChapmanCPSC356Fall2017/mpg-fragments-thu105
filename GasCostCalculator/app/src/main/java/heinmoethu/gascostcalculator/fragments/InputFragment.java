package heinmoethu.gascostcalculator.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import heinmoethu.gascostcalculator.R;
import heinmoethu.gascostcalculator.models.InputModel;

/**
 * Created by Hein Moe Thu on 10/19/2017.
 */

public class InputFragment extends Fragment {
    EditText m,p,l;
    private InputModel input;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.input=new InputModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        input = new InputModel();
        View v = inflater.inflate(R.layout.fragment_input,container,false);
        this.m = v.findViewById(R.id.et_mpg);
        this.p = v.findViewById(R.id.et_ppg);
        this.l = v.findViewById(R.id.et_len);

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
    public InputModel getInputModel(){
        return this.input;
    }
}