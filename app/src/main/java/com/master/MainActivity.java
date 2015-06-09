package com.master;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.master.activity.SearchActivity;
import com.master.dialog.AboutDialog;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/6/4.
 */
public  class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @InjectView(R.id.tb_custom)
    Toolbar toolbar;
    @InjectView(R.id.dl_left)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.lv_left_menu)
    ListView lvLeftMenu;

    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayAdapter arrayAdapter;
    private String[] lvs = {"英语", "日语" };
    private boolean isDrawerOpen ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                isDrawerOpen = true;
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawerOpen = false;
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //设置菜单列表
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs);
        lvLeftMenu.setAdapter(arrayAdapter);
        lvLeftMenu.setOnItemClickListener(this);

        if(savedInstanceState == null){
            //bug: mToolbar.setTitle() not working here.
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment("traveleng.db","traveleng_cate","id","cate_name_zh"))
                    .commit();
        }else{
            getSupportActionBar().setTitle(savedInstanceState.getString("title"));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.action_settings:

                break;
            case R.id.action_update:

                break;
            case R.id.action_about:
                new AboutDialog().show(getFragmentManager(), getResources().getString(R.string.action_about));
                break;
            case R.id.action_exit:
                MainActivity.this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new MainFragment("traveleng.db","traveleng_cate","id","cate_name_zh");
                break;

            case 1:
                fragment = new MainFragment("101_travel.db","sub_main_menu","indexid","topic_cn");
                break;

        }
        replace(fragment);
        menuToggle();

    }


    private void menuToggle(){
        if (isDrawerOpen){
            mDrawerLayout.closeDrawers();
        }else{

        }
    }


    public void replace(Fragment fragment)
    {
        if (fragment == null)
        {
            return;
        }
         FragmentManager fm = getSupportFragmentManager();
         FragmentTransaction ft = fm.beginTransaction();
         ft.replace(R.id.container, fragment);
         ft.commit();
    }
}