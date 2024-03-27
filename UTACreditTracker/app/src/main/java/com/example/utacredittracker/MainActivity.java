package com.example.utacredittracker;

import android.os.Bundle;

import com.example.utacredittracker.ui.dashboard.DashboardFragment;
import com.example.utacredittracker.ui.home.HomeFragment;
import com.example.utacredittracker.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.utacredittracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewPager viewPager = findViewById(R.id.viewPager);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);

        navView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                viewPager.setCurrentItem(0, false);
                return true;
            } else if (item.getItemId() == R.id.navigation_dashboard) {
                viewPager.setCurrentItem(1, false);
                return true;
            } else if (item.getItemId() == R.id.navigation_notifications) {
                viewPager.setCurrentItem(2, false);
                return true;
            }
            return false;
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // Update BottomNavigationView when ViewPager page changes
                navView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new DashboardFragment();
                case 2:
                    return new NotificationsFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3; // Number of fragments
        }
    }

}