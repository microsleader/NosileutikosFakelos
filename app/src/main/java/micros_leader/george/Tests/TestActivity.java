package micros_leader.george.Tests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import micros_leader.george.nosileutikosfakelos.InfoSpecificLists;
import micros_leader.george.nosileutikosfakelos.NpaGridLayoutManager;
import micros_leader.george.nosileutikosfakelos.R;

import android.os.Bundle;
import android.view.View;

public class TestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NpaGridLayoutManager manager;
    BasicRV_single_item_test adapter;
    private boolean isRun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);


        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);

        int [] titloi_positions = new int[]{0,4,8,19,35,37,43,49,67,79,93,103,118};

         adapter = new BasicRV_single_item_test(this, InfoSpecificLists.getNosil_Elegxos_Meth(),titloi_positions);


        manager = new NpaGridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                boolean isHeader = isHeader(position,titloi_positions);
                if (isHeader)
                    return  2;
                else
                    return  1;

            }
        });
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);
       // setad();

        isRun = true;











    }
    private void setad(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(adapter);
            }
        });
    }
    public static boolean isHeader(int position , int[] theseisTitloi) {

        if (theseisTitloi != null) {
            for (int x : theseisTitloi) {   // ΕΛΕΓΧΕΙ ΑΝ ΤΟ position ΥΠΑΡΧΕΙ ΣΤΗ ΛΙΣΤΑ  theseisTitloi
                if (x == position) {
                    return true;
                }

            }
        }
        return false;

    }
}
