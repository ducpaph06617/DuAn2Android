package com.dev.duan2android;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.dev.duan2android.base.BaseActivity;
import com.dev.duan2android.fragment.Fragment_Chat;
import com.dev.duan2android.fragment.Fragment_Home;
import com.dev.duan2android.fragment.Fragment_Menu;
import com.dev.duan2android.fragment.Fragment_Notification;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class HomeActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {

    private String id="";
    private FloatingSearchView floatingSearchView;
    List<String> strings=new ArrayList<>();

    private ListView listView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingSearchView=findViewById(R.id.floating_search_view);

        mapped();
        onclickView();
    }

    //ánh xạ view
    private void mapped() {

        bottomBar = findViewById(R.id.bottomBar);
        nearby = bottomBar.getTabWithId(R.id.tab_cart);
        nearby1 = bottomBar.getTabWithId(R.id.tab_home);
        nearby2 = bottomBar.getTabWithId(R.id.tab_menu);
        nearby3 = bottomBar.getTabWithId(R.id.tab_notification);

    }

    //các sự kiện click

    private void onclickView() {

        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                List<String> lstFound = new ArrayList<String>();
                if (oldQuery!=null&& !oldQuery.isEmpty()){
                    for(String item:strings){
                        if(item.contains(oldQuery))
                            lstFound.add(item);
                    }
                    Log.e("SIZE",strings.size()+"");
                    ArrayAdapter adapter = new ArrayAdapter(HomeActivity.this,android.R.layout.simple_list_item_1,lstFound);

                }

            }
        });

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                fragment(tabId);
            }
        });
    }

    //bottom cilck chuyển đổi giữa các fragment

    public void fragment(int id) {

        switch (id) {
            case R.id.tab_home:

                getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,
                        new Fragment_Home()).commit();

                break;
            case R.id.tab_cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,
                        new Fragment_Chat()).commit();

                break;
            case R.id.tab_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,
                        new Fragment_Menu()).commit();

                break;
            case R.id.tab_notification:
                getSupportFragmentManager().beginTransaction().replace(R.id.viewpager,
                        new Fragment_Notification()).commit();
                break;


        }
    }

    @Override
    public void onBackPressed() {
        HomeActivity.this.finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }



}
