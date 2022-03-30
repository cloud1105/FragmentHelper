package com.hsae.helper;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * FragmentHelper
 *
 * @author huxinliang
 * @since 2022/3/29
 */
public class FragmentHelper {

    private static final String TAG = FragmentHelper.class.getSimpleName();

    private AppCompatActivity appCompatActivity;
    private Fragment fragment;
    private Fragment currentVisibleFragment;
    private int frameLayoutId;

    /**
     * 与activity绑定的构造方法
     *
     * @param activity 绑定的父activity
     * @param resId    FrameLayout id
     */
    public FragmentHelper(AppCompatActivity activity, int resId) {
        appCompatActivity = activity;
        frameLayoutId = resId;
    }

    /**
     * 与fragment绑定的构造方法
     *
     * @param fragment 绑定的父Fragment
     * @param resId    FrameLayout id
     */
    public FragmentHelper(Fragment fragment, int resId) {
        this.fragment = fragment;
        frameLayoutId = resId;
    }

    /**
     * 显示Activity的子Fragment
     *
     * @param targetFragment 需要显示的子Fragment
     * @return this
     */
    public FragmentHelper show(@NonNull Fragment targetFragment) {
        if (currentVisibleFragment == targetFragment) {
            Log.d(TAG, "show: targetFragment is visible,do not show again");
            return this;
        }
        FragmentTransaction transaction = getFragmentTransaction(appCompatActivity);
        String tag = targetFragment.getClass().getName();
        if (targetFragment.isAdded()) {
            show(transaction, targetFragment, getName(appCompatActivity));
        } else {
            add(transaction, targetFragment, tag, getName(appCompatActivity));
        }
        transaction.commitAllowingStateLoss();
        return this;
    }

    /**
     * 隐藏Activity的子Fragment
     *
     * @param fragment 隐藏的Fragment
     * @return this
     */
    public FragmentHelper hide(@NonNull Fragment fragment) {
        FragmentTransaction transaction = getFragmentTransaction(appCompatActivity);
        if (fragment.isAdded() && fragment.isVisible()) {
            transaction.hide(fragment);
            Log.d(TAG, " hide fragment: " + fragment.getClass().getSimpleName());
        }
        transaction.commitAllowingStateLoss();
        return this;
    }

    /**
     * 显示Fragment的子Fragment
     *
     * @param targetFragment 需要显示的子Fragment
     * @return this
     */
    public FragmentHelper showChild(@NonNull Fragment targetFragment) {
        if (currentVisibleFragment == targetFragment) {
            Log.d(TAG, "showChild: targetFragment is visible,do not show again");
            return this;
        }
        FragmentTransaction transaction = getFragmentTransaction(fragment);
        String tag = targetFragment.getClass().getName();
        if (targetFragment.isAdded()) {
            show(transaction, targetFragment, getName(fragment));
        } else {
            add(transaction, targetFragment, tag, getName(fragment));
        }
        transaction.commitAllowingStateLoss();
        return this;
    }

    /**
     * 隐藏Fragment的子Fragment
     *
     * @param fragment 隐藏的Fragment
     * @return this
     */
    public FragmentHelper hideChild(@NonNull Fragment fragment) {
        FragmentTransaction transaction = getFragmentTransaction(this.fragment);
        if (fragment.isAdded() && fragment.isVisible()) {
            transaction.hide(fragment);
            Log.d(TAG, " hideChild fragment: " + fragment.getClass().getSimpleName());
        }
        transaction.commitAllowingStateLoss();
        return this;
    }

    private FragmentTransaction getFragmentTransaction(AppCompatActivity activity) {
        return activity.getSupportFragmentManager().beginTransaction();
    }

    private FragmentTransaction getFragmentTransaction(Fragment fragment) {
        return fragment.getChildFragmentManager().beginTransaction();
    }

    private void show(FragmentTransaction transaction, Fragment fragment, String className) {
        if (currentVisibleFragment != null && currentVisibleFragment.isAdded() && currentVisibleFragment.isVisible()) {
            transaction.hide(currentVisibleFragment);
        }
        transaction.show(fragment);
        Log.d(TAG, " show fragment: " + fragment.getClass().getSimpleName());
        currentVisibleFragment = fragment;
    }

    private void add(FragmentTransaction transaction, Fragment fragment, String tag, String className) {
        if (currentVisibleFragment != null && currentVisibleFragment.isAdded() && currentVisibleFragment.isVisible()) {
            transaction.hide(currentVisibleFragment);
        }
        transaction.add(frameLayoutId, fragment, tag);
        Log.d(TAG, " add fragment: " + fragment.getClass().getSimpleName());
        currentVisibleFragment = fragment;
    }

    private String getName(AppCompatActivity activity) {
        return activity.getClass().getSimpleName();
    }

    private String getName(Fragment fragment) {
        return fragment.getClass().getSimpleName();
    }
}
