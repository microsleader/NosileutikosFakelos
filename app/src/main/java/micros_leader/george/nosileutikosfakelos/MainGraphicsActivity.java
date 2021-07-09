package micros_leader.george.nosileutikosfakelos;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainGraphicsActivity extends BasicActivity {

    public CoordinatorLayout coordinatorLayout;
    public ConstraintLayout constraintLayout;
    public LinearLayout linearLayout;
    private boolean hasFabMenu;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    public void setMainLayoutScreen(String titleToolbar, boolean hasFabMenu){
        if (hasFabMenu) {
            setCoordinatorLayout();
            this.hasFabMenu = hasFabMenu;
        }

        setConstraintLayout();

        createToolbar(titleToolbar);

        setMainLinearLayout();
        createFloorSP();
        createDateTV();
        createPatientsTV();

        createRecyclerView();



        if (hasFabMenu) {
            createFabIcon();
            setContentView(coordinatorLayout);
        }
        else
            setContentView(constraintLayout);



    }



    public void setCoordinatorLayout(){
        coordinatorLayout = new CoordinatorLayout(this);
        coordinatorLayout.setId(MainGraphicsValues.MAIN_COORDINATOR_ID);
        coordinatorLayout.setLayoutParams(new CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.MATCH_PARENT));
    }



    public void setConstraintLayout(){
        constraintLayout = new ConstraintLayout(this);
        constraintLayout.setId(MainGraphicsValues.MAIN_CONSTRAINT_ID);
        constraintLayout.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT));

        if (hasFabMenu)
        coordinatorLayout.addView(constraintLayout);


    }


    private void createToolbar(String title){
        toolbar = new Toolbar(this);
        toolbar.setId(MainGraphicsValues.TOOLBAR_ID);
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setLayoutParams(new Toolbar.LayoutParams(
                Toolbar.LayoutParams.MATCH_PARENT,
                Toolbar.LayoutParams.WRAP_CONTENT));
        toolbar.setBackgroundColor(Color.CYAN);

        constraintLayout.addView(toolbar);

        setConstraints(toolbar,ConstraintSet.END,ConstraintSet.END,0);
        setConstraints(toolbar,ConstraintSet.START,ConstraintSet.START,0);
        setConstraints(toolbar,ConstraintSet.TOP,ConstraintSet.TOP,0);



    }





    public void setMainLinearLayout(){

        linearLayout = new LinearLayout(this);
        linearLayout.setId(MainGraphicsValues.MAIN_LINEARLAYOUT_ID);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        constraintLayout.addView(linearLayout);
        setConstraints(linearLayout,ConstraintSet.END,toolbar,ConstraintSet.END,0);
        setConstraints(linearLayout,ConstraintSet.START,toolbar,ConstraintSet.START,0);
        setConstraints(linearLayout,ConstraintSet.TOP,toolbar,ConstraintSet.BOTTOM,0);

    }

    public void createFloorSP(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,8,0,0);
        floorSP = createSpinner(MainGraphicsValues.FLOOR_SP_ID, params , R.drawable.spinner_layout);
        linearLayout.addView(floorSP);
    }

    public Spinner createSpinner(int id, LinearLayout.LayoutParams params , int backgroundResource){

        Spinner spinner  = new Spinner(this);
        spinner.setId(id);
        spinner.setLayoutParams(params);
        spinner.setBackgroundResource(backgroundResource);


        return spinner;
    }

    public void createDateTV(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,22,0,0);

        patientsTV = createTextview(MainGraphicsValues.DATE_TV_ID,"date" ,params, R.drawable.edittext_shape);

        linearLayout.addView(patientsTV);

    }

    public void createPatientsTV(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,22,0,0);
        patientsTV = createTextview(MainGraphicsValues.PATIENTS_TV_ID,"patients" ,params , R.drawable.edittext_shape);

        linearLayout.addView(patientsTV);
    }


    public TextView createTextview(int id,String text,LinearLayout.LayoutParams params , int backgroundResource){

        TextView textView = new TextView(this);
        textView.setId(id);
        textView.setText(text);
        textView.setLayoutParams(params);
        textView.setBackgroundResource(backgroundResource);
        textView.setGravity(Gravity.CENTER);



        return textView;

    }



    public void createRecyclerView(){
        recyclerView = new RecyclerView(this);
        recyclerView.setId(MainGraphicsValues.RECYCLERVIEW_ID);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, 0);
        recyclerView.setLayoutParams( params);



        constraintLayout.addView(recyclerView);
        setConstraints(recyclerView,ConstraintSet.BOTTOM,ConstraintSet.BOTTOM,3);
        setConstraints(recyclerView,ConstraintSet.END,ConstraintSet.END,22);
        setConstraints(recyclerView,ConstraintSet.START,ConstraintSet.START,22);
        setConstraints(recyclerView,ConstraintSet.TOP,linearLayout,ConstraintSet.BOTTOM,32);




    }



    public void createFabIcon(){

        fab = new FloatingActionButton(this);
        fab.setId(MainGraphicsValues.FAB_ICON_ID);

        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams( CoordinatorLayout.LayoutParams.WRAP_CONTENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.END|Gravity.BOTTOM;
        params.setMargins(16,16,16,16);
        fab.setLayoutParams(params);
        fab.setSupportBackgroundTintList(ContextCompat.getColorStateList(this, R.color.blue));
        fab.setImageResource(R.drawable.drug_icon);



        coordinatorLayout.addView(fab);
    }




    public void setConstraints(View view, int const1, View sePoioViewThaProsarmozetai, int const2, int margin){
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(view.getId(),const1,sePoioViewThaProsarmozetai.getId(),const2,margin);
        // constraintSet.connect(view,ConstraintSet.END,R.id.check_answer1,ConstraintSet.END,0);
        constraintSet.applyTo(constraintLayout);
    }


    public void setConstraints(View view, int const1,  int const2, int margin){
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(view.getId(),const1,ConstraintSet.PARENT_ID,const2,margin);
        // constraintSet.connect(view,ConstraintSet.END,R.id.check_answer1,ConstraintSet.END,0);
        constraintSet.applyTo(constraintLayout);
    }
}
