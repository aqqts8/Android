package com.example.lab6custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    TextView tvMsg;
    GridView gvmyPicture;

    Integer []mThumbIds;
    MyImageAdapter myadapter = null;

    TextView tvShowMsg;
    ImageView ivmyPicture;
    Button btnBack;

    Bundle myBackupBundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myBackupBundle = savedInstanceState;
        setContentView(R.layout.activity_main);
        get_myWidget();

        mThumbIds = new Integer[] {R.drawable.image1, R.drawable.image2,
                R.drawable.image3, R.drawable.image4,
                R.drawable.image5, R.drawable.image5,
                R.drawable.image7};

        myadapter = new MyImageAdapter(this, mThumbIds);

        gvmyPicture.setAdapter(myadapter);
        set_myEvent();
    }

    private void get_myWidget()
    {
        tvMsg = (TextView)findViewById(R.id.tvMsg);
        gvmyPicture=(GridView) findViewById(R.id.gvmyPicture);
    }
    private void get_myWidget_2()
    {
        tvShowMsg =(TextView) findViewById(R.id.tvShowMsg);
        ivmyPicture = (ImageView) findViewById(R.id.ivmyPicture);
    }

    private void set_myEvent()
    {
        gvmyPicture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long
                    id) {
                showdetail(position);
            }
        });
    }

    private void showdetail(int pos)
    {
        setContentView(R.layout.show_myimage_layout);
        get_myWidget_2();
        tvShowMsg.setText("Image at " + pos);
        ivmyPicture.setImageResource(mThumbIds[pos]);
        btnBack=(Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                onCreate(myBackupBundle);
            }
        });
    }


}