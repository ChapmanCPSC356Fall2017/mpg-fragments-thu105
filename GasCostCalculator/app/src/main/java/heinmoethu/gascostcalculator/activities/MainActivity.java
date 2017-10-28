package heinmoethu.gascostcalculator.activities;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import heinmoethu.gascostcalculator.R;
import heinmoethu.gascostcalculator.fragments.InputFragment;
import heinmoethu.gascostcalculator.fragments.OutputFragment;
import heinmoethu.gascostcalculator.models.InputModel;

public class MainActivity extends AppCompatActivity {

    private boolean nextOutput=true; //tells if the next fragment is the OutputFragment
    private boolean eco=false;//container of eco mode checkbox data
    private FloatingActionButton fab; //will be referenced later to change img src

    private InputFragment inputF;
    private OutputFragment outputF;
    private InputModel input;


    @Override
    public void onSaveInstanceState(Bundle outState) {//when orientation is changed
        if(outputF!=null){//if OutputFragment was created, retain checkbox data
            eco=outputF.isEco();
        }
        if(inputF!=null){//if InputFragment was created, retain inputModel
            input=inputF.getInputModel();
        }
        //passing in all the dynamic data
        outState.putParcelable("InputModel",input);
        outState.putBoolean("IsEco",eco);
        outState.putBoolean("IsNextOutput",nextOutput);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab_button);

        if(savedInstanceState!=null){//get all the dynamic data if orientation change was made
            input=savedInstanceState.getParcelable("InputModel");
            eco=savedInstanceState.getBoolean("IsEco");
            nextOutput=savedInstanceState.getBoolean("IsNextOutput");
        }


        if(nextOutput){//if it is nextOutput, then current one is InputFragment
            fab.setImageResource(R.drawable.ic_arrow_forward);
            inputF = new InputFragment();
            Bundle b = new Bundle();
            b.putParcelable("InputModel",input);//input can be null
            inputF.setArguments(b);
            showFragment(inputF);
        }
        else{
            fab.setImageResource(R.drawable.ic_arrow_back);
            outputF = new OutputFragment();
            Bundle b = new Bundle();
            b.putParcelable("InputModel",input);
            b.putBoolean("IsEco",eco);
            outputF.setArguments(b);
            showFragment(outputF);
        }
    }

    @SuppressLint("PrivateResource")
    private void showFragment(Fragment f) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_slide_out_bottom)
                .replace(R.id.fl_fragment_container,f)
                .commit();
    }

    public void onClick(View view) {
        if (nextOutput){//create a new OutputFragment and pass in InputModel data
            nextOutput=false;
            fab.setImageResource(R.drawable.ic_arrow_back);

            outputF = new OutputFragment();
            Bundle b = new Bundle();
            input = inputF.getInputModel();
            b.putParcelable("InputModel",input);
            b.putBoolean("IsEco", false);//eco mode is disabled by default
            outputF.setArguments(b);
            showFragment(outputF);
        }
        else {//create a new InputFragment
            nextOutput = true;
            fab.setImageResource(R.drawable.ic_arrow_forward);

            inputF = new InputFragment();
            showFragment(inputF);
        }
    }
}
