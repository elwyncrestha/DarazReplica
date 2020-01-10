package com.github.elwyncrestha.darazreplica.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.github.elwyncrestha.darazreplica.R;
import com.github.elwyncrestha.darazreplica.ui.activity.LoginActivity;
import com.github.elwyncrestha.darazreplica.util.UserUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        Button btnAccountLogin = view.findViewById(R.id.btnAccountLogin);
        if (UserUtils.loggedIn) {
            btnAccountLogin.setText("Logged In");
        } else {
            btnAccountLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        return view;
    }

}
