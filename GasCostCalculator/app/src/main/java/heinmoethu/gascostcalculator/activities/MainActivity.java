package heinmoethu.gascostcalculator.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import heinmoethu.gascostcalculator.R;
import heinmoethu.gascostcalculator.fragments.InputFragment;
import heinmoethu.gascostcalculator.fragments.OutputFragment;
import heinmoethu.gascostcalculator.models.InputModel;

import static heinmoethu.gascostcalculator.R.drawable.ic_arrow_forward;

public class MainActivity extends AppCompatActivity {

    private boolean isResult = true;
    private FloatingActionButton fab;
    private InputFragment inputF;
    private OutputFragment outputF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = (FloatingActionButton) findViewById(R.id.fab_button);
        inputF = new InputFragment();
        showFragment(inputF);
    }

    private void showFragment(Fragment f) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                .replace(R.id.fl_fragment_container,f)
                .commit();
    }

    public void onClick(View view) {
        if (isResult){
            fab.setImageResource(R.drawable.ic_arrow_back);
            isResult=false;
            InputModel input = inputF.getInputModel();
            outputF = new OutputFragment();
            Bundle b = new Bundle();
            b.putParcelable("InputModel",input);
            outputF.setArguments(b);
            showFragment(outputF);
        }
        else {
            isResult = true;
            fab.setImageResource(R.drawable.ic_arrow_forward);
            inputF = new InputFragment();
            showFragment(inputF);

        }
    }
}
