package com.example.moneygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class UserFragment extends Fragment {

    Button logout;

    public UserFragment() {

    }

    public void logout() {
        JWTSharedPref.removeDefaults("jwt_token", getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_fragment, container, false);
        TextView firstName = view.findViewById(R.id.first_name_user);
        TextView lastName = view.findViewById(R.id.last_name_user);
        TextView userEmail = view.findViewById(R.id.user_email_user);
        TextView regDate = view.findViewById(R.id.user_date_user);
        UserInfoService dashboardService = new UserInfoService(getActivity());
        dashboardService.getUserInfo(new UserInfoService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(UserInfoModel userInfo) {
                firstName.setText(userInfo.getFirst_name());
                lastName.setText(userInfo.getLast_name());
                userEmail.setText(userInfo.getUser_email());
                regDate.setText(userInfo.getRegistration_date());

            }
        });

        logout = (Button) view.findViewById(R.id.user_logoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });


        return view;


    }
}
