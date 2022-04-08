package micros_leader.george.nosileutikosfakelos.Main_menu;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.BasicActivity;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;
import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;

import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.databinding.ActivitySigxoneusiFiladiwnBinding;

public class SigxoneusiFiladiwnActivity extends BasicActivity {


    private RecyclerView rv_hor;
    private ActivitySigxoneusiFiladiwnBinding bd;
    public int departmentID ;
    public static final String FILLADIO_KEY = "filladio";
    public static final int NO_DEPARTMENT = -1;
    public static final int DEPARTMENT_OROFOI = 0;
    public static final int DEPARTMENT_METH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivitySigxoneusiFiladiwnBinding.inflate(getLayoutInflater());
        View view = bd.getRoot();
        setContentView(view);

        departmentID = getIntent().getIntExtra("department" , NO_DEPARTMENT);
        ArrayList<Menu_general_Item> menu_lista =  InfoSpecificLists.getSigxoneusiFilladiwn(departmentID);

        Menu_general_RV_Adapter adapter = new Menu_general_RV_Adapter(menu_lista, this, false, departmentID);
        setRecyclerViewHorizontalLinearLayout(rv_hor,  R.id.recyclerView , adapter);
        adapter.addFrameLayout(bd.actFR);

        setDisplayUserName();

    }



   public enum FILLADIO_SIGXONEUSIS {
        ZOTIKA_METH,
        ZOTIKA_OROFOI,
        KATHIMERINO_ZIGISMA,
        ISOZIGIO_METH,
        NEURIKI_AKSIOLOGISI,
        KATHETIRES_METH,
        KATHETIRES_OROFOI,
    }




}