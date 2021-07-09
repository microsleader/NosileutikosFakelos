package micros_leader.george.nosileutikosfakelos.OROFOI.f_example;

import android.os.Bundle;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.R;

public class ExampleActivity2 extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example2);

        getPatientsList(this, R.id.patientsTV ,R.id.floorsSP);
    }
}
