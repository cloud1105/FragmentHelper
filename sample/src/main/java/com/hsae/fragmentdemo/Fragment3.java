package com.hsae.fragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hsae.helper.FragmentHelper;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {
    private FragmentHelper helper;
    private Button button;
    private int index = 1;
    private Fragment fragment1, fragment2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        button = view.findViewById(R.id.btn2);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        helper = new FragmentHelper(this, R.id.frameLayout2);
        helper.showChild(fragment1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index) {
                    case 1:
                        hideFragment(fragment1);
                        replaceFragment(fragment2);
                        index++;
                        break;
                    default:
                        hideFragment(fragment2);
                        replaceFragment(fragment1);
                        index = 1;
                        break;
                }

            }

            private void hideFragment(Fragment fragment) {
                helper.hideChild(fragment);
            }

            private void replaceFragment(Fragment fragment) {
                helper.showChild(fragment);
            }
        });
        return view;
    }
}
