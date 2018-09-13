package tkacz.kamil.masterapp;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MasterTitleLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_title_layout);


        ConstraintLayout rlayout2 = (ConstraintLayout) findViewById(R.id.activity_master_title_layout);
        rlayout2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent in2 = new Intent(MasterTitleLayout.this, test.class);
                startActivity(in2);
            }


        });
    }
}
