package org.techtown.viewpagerexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    // 프래그먼트 교체를 보여주는 처리를 위함
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return FragMonday.newInstance();
            case 1:
                return FragTuesday.newInstance();
            case 2:
                return FragWednesday.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    // 상단의 탭 레이아웃 인디케이터쪽에 텍스트를 선언
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "라떼는말이야";
            case 1:
                return "내가바로";
            case 2:
                return "박범수";
            default:
                return null;
        }
    }
}
