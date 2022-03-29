package com.hsae.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hsae.helper.FragmentHelper;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private FragmentHelper helper;
    private int index = 1;
    private Fragment fragment1,fragment2,fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn1);
        helper = new FragmentHelper(this, R.id.frameLayout1);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        helper.showFragment(fragment1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index) {
                    case 1:
                        hideFragment(fragment1);
                        hideFragment(fragment3);
                        replaceFragment(fragment2);
                        index++;
                        break;
                    case 2:
                        hideFragment(fragment1);
                        hideFragment(fragment2);
                        replaceFragment(fragment3);
                        index++;
                        break;
                    default:
                        hideFragment(fragment2);
                        hideFragment(fragment3);
                        replaceFragment(fragment1);
                        index = 1;
                        break;
                }

            }

            private void hideFragment(Fragment fragment) {
                helper.hide(fragment);
            }

            private void replaceFragment(Fragment fragment) {
                helper.showFragment(fragment);
            }
        });
    }
}
