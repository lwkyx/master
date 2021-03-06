package com.master.activity;

import android.content.ComponentName;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.IntentCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.master.BaseActivty;
import com.master.MainActivity;
import com.master.R;
import com.master.adapter.CardRecyclerAdapter;
import com.master.adapter.MyItemClickListener;
import com.master.api.PlayTask;
import com.master.api.TtsUtil;
import com.master.database.DatabaseUtils;

import java.io.IOException;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CardActivity extends BaseActivty implements MyItemClickListener {

    @InjectView(R.id.tb_custom)
    Toolbar toolbar;
    @InjectView(R.id.recyclerview_card)
    RecyclerView mRecyclerView;

    private Map<String, String> travelengMap;
    private int mId;//ID号码
    private String mIdString; //id字段名
    private String mZh; //中文字段名
    private String mEn; //外文字段名
    private String mDbName  ;
    private String mTableName ;
    private SQLiteDatabase db;
    private String[] provinceArray;
    private Map<String,Object> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        ButterKnife.inject(this);
        initData ( );
        initToolbar();
        initDB();




        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardRecyclerAdapter adapter = new CardRecyclerAdapter(provinceArray, travelengMap);
       mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);



    }

    private void initData() {
        //获取data
        mData = (Map<String, Object>) getIntent().getSerializableExtra("data");
        mDbName = (String) mData.get("dbName");
        mTableName = (String) mData.get("tableName");
        mIdString = (String) mData.get("id");
        mZh = (String) mData.get("zh");
        mEn = (String) mData.get("en");
        mId = (int) mData.get("idNum");

    }

    private void initDB() {
        try {
            DatabaseUtils.copyDB(CardActivity.this, mDbName);
            if (db == null) {
                db = openOrCreateDatabase(getFilesDir().getAbsolutePath() + "/"
                        + mDbName, Context.MODE_PRIVATE, null);
            }
            travelengMap = DatabaseUtils.getItem(db, mTableName,mIdString,mZh,mEn ,mId);
            provinceArray = travelengMap.keySet().toArray(
                    new String[travelengMap.size()]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initToolbar() {
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        //toolbar�˺�
        toolbar.setNavigationIcon(R.drawable.ic_up);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateUpToFromChild(CardActivity.this,
                        IntentCompat.makeMainActivity(new ComponentName(CardActivity.this,
                                MainActivity.class)));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card, menu);
        Log.e("Test", "Test");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(View v, int position) {
        if (mDbName.equals("traveleng.db")){
            Snackbar.make(v, travelengMap.get(provinceArray[position]), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            new PlayTask(this).execute(TtsUtil.getMp3URL(travelengMap.get(provinceArray[position])));
        }else if(mDbName.equals("101_travel.db")){
            if (db == null) {
                db = openOrCreateDatabase(getFilesDir().getAbsolutePath() + "/"
                        + mDbName, Context.MODE_PRIVATE, null);
            }
           String mp3Path =  DatabaseUtils.getMp3Path(db,travelengMap.get(provinceArray[position]));

            Toast.makeText(this,mp3Path,Toast.LENGTH_SHORT).show();
        }


    }
}
