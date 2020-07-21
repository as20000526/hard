package com.example.hard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main extends AppCompatActivity {
    private TextView production,today_report,emil;
    PopupWindow mPopupWindow;
    private ViewPager viewPager;
    private TabLayout tab_layout;
    private MainPagerAdapter mainPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        production=findViewById(R.id.production);
        today_report=findViewById(R.id.today_report);
        emil=findViewById(R.id.emil);
        tab_layout=findViewById(R.id.tabLayoutMain);
        viewPager=findViewById(R.id.viewPagerMain);
        mainPagerAdapter=new MainPagerAdapter(getSupportFragmentManager());
        mainPagerAdapter.addFragment(new Production(),"production");
        mainPagerAdapter.addFragment(new today_report(),"todayReport");
        mainPagerAdapter.addFragment(new Emil(),"email" );
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(Main.this, ""+tab.getText(), Toast.LENGTH_SHORT).show();
                if (tab.getText().equals("production")){

                }
                else if(tab.getText().equals("todayReport")){
                    mPopupWindow.showAsDropDown(tab_layout);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

//    private  void gotoproduction(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment, fragment_pro);
//            }
//
//    private  void gototoday_report(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment,fragment_tod);
//    }
//    private  void gotoemil(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment, fragment_emil);
//    }
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.production:
//                gotoproduction();
//            break;
//            case R.id.today_report:
//                gototoday_report();
//            break;
//            case R.id.emil:
//                gotoemil();
//                break;
//        }
//
//    }
}