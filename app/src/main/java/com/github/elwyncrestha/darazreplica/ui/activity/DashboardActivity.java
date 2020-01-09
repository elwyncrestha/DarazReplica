package com.github.elwyncrestha.darazreplica.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.github.elwyncrestha.darazreplica.R;
import com.github.elwyncrestha.darazreplica.ui.fragment.AccountFragment;
import com.github.elwyncrestha.darazreplica.ui.fragment.CartFragment;
import com.github.elwyncrestha.darazreplica.ui.fragment.HomeFragment;
import com.github.elwyncrestha.darazreplica.ui.fragment.MessageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private CartFragment cartFragment;
    private AccountFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));

        BottomNavigationView bnvMain = findViewById(R.id.bnvMain);

        homeFragment = new HomeFragment();
        messageFragment = new MessageFragment();
        cartFragment = new CartFragment();
        accountFragment = new AccountFragment();

        setFragment(homeFragment);

        bnvMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_message:
                        setFragment(messageFragment);
                        return true;

                    case R.id.nav_cart:
                        setFragment(cartFragment);
                        return true;

                    case R.id.nav_account:
                        setFragment(accountFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
