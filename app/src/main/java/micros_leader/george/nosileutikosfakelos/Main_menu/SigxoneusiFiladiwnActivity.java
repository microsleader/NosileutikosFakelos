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


    private final ArrayList<Menu_general_Item> menu_lista =  InfoSpecificLists.getSigxoneusiFilladiwn();
    private RecyclerView rv_hor;
    private ActivitySigxoneusiFiladiwnBinding bd;
    public static final String FILLADIO_KEY = "filladio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivitySigxoneusiFiladiwnBinding.inflate(getLayoutInflater());
        View view = bd.getRoot();
        setContentView(view);

        Menu_general_RV_Adapter adapter = new Menu_general_RV_Adapter(menu_lista, this, false);
        setRecyclerViewHorizontalLinearLayout(rv_hor,  R.id.recyclerView , adapter);
        adapter.addFrameLayout(bd.actFR);
    }



   public enum FILLADIO_SIGXONEUSIS {
        ZOTIKA_METH,
        KATHIMERINO_ZIGISMA,
        ISOZIGIO_METH,
        NEURIKI_AKSIOLOGISI,
        KATHETIRES_KALLIERGIES
    }




}