package com.example.razu.newcsitproject.Semester;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.razu.newcsitproject.Fragments.Fragment_1stsem;
import com.example.razu.newcsitproject.Fragments.Fragment_Solutions;
import com.example.razu.newcsitproject.Fragments.Fragment_oldquestions;
import com.example.razu.newcsitproject.Fragments.Fragment_syllabus;
import com.example.razu.newcsitproject.R;

import java.util.ArrayList;
import java.util.List;

public class Firstsem extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView text_toolbars;
    ImageView btn_back;
    String names;
    private int[] tabIcons = {
            R.drawable.ic_notes,
            R.drawable.ic_syllabus,
            R.drawable.ic_questions,
            R.drawable.ic_solutions
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstsem);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        names = getIntent().getExtras().getString("name");
         setTitle(names);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }
    public int getdata(){
        if(names.equals("First Semester")){
            return 1;
        }else if(names.equals("Second Semester")){
            return 2;
        }else if(names.equals("Third Semester")){
         return 3;
        }else if (names.equals("Fourth Semester")){
            return 4;
        }else if(names.equals("Fifth Semester")){
            return 5;
        }else if(names.equals("Sixth Semester")){
            return 6;
        }else if(names.equals("Seventh Semester")){
            return 7;
        }else
        {
            return 8;
        }

    }
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.customs_tabs, null);
        tabOne.setText("Notes");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_notes, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.customs_tabs, null);
        tabTwo.setText("Syllabus");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_syllabus, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.customs_tabs, null);
        tabThree.setText("Old-Ques");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0,  R.drawable.ic_questions, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.customs_tabs, null);
        tabFour.setText("Solutions");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_solutions, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_1stsem(), "Notes");
        adapter.addFragment(new Fragment_syllabus(), "Syllabus");
        adapter.addFragment(new Fragment_oldquestions(), "Oldques");

        adapter.addFragment(new Fragment_Solutions(), "Solutions");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opendownloadfolder,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:
                Toast.makeText(this, "Comming Soon!!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
