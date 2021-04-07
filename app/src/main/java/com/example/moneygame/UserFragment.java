package com.example.moneygame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        UserInfoService dashboardService = new UserInfoService(getActivity());
        dashboardService.getUserInfo(new UserInfoService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(UserInfoModel userInfo) {
                Toast.makeText(getActivity(), userInfo.toString(), Toast.LENGTH_LONG).show();
            }
        });

        return inflater.inflate(R.layout.user_fragment, container, false);

    }
}
