package org.techtown.viewbindingstudy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.techtown.viewbindingstudy.databinding.FragTestBinding;

public class TestFragment extends Fragment {

    private FragTestBinding fragTestBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragTestBinding = FragTestBinding.inflate(inflater, container, false);

        fragTestBinding.btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragTestBinding.tvFragment.setText("Good !!");
            }
        });

        return fragTestBinding.getRoot();
    }
}
