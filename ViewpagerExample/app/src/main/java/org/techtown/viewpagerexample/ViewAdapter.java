package org.techtown.viewpagerexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewAdapter extends FragmentStateAdapter {

    public ViewAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    //프래그먼트를 교체를 보여주는 처리를 구현
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
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
    public int getItemCount() {     // 리스트뷰 어뎁터나 리사이클러 어뎁터에서도 자주 보임
        // 갯수를 명시해줘야 한다.
        // 3가지의 프래그먼트를 만들었으므로
        // return 3이 됨
        return 3;
    }

    // 상단의 탭레이아웃 인디케이터 쪽에 텍스트를 선언해줌
    @Nullable
    @Override

    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Monday";

            case 1:
                return "Tuesday";

            case 2:
                return "Wednesday";
            default:
                return null;
        }
    }
}
