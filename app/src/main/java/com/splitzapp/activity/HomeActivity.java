package com.splitzapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.splitzapp.R;
import com.splitzapp.ViewPagerAdapter;
import com.splitzapp.tab.Expense;
import com.splitzapp.tab.Group;
import com.splitzapp.tab.Expense2;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar)findViewById(R.id.myToolbar);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        viewPager = (ViewPager)findViewById(R.id.myViewpager);

        setSupportActionBar(toolbar);
        setViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Expense(), "Expense");
        viewPagerAdapter.addFragment(new Group(), "Group");
        viewPagerAdapter.addFragment(new Expense2(), "Expense2");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
