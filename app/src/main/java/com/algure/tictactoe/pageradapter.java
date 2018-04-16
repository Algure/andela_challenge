package com.algure.tictactoe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by AJIRI GUNN on 1/3/2018.
 */
public class pageradapter extends FragmentStatePagerAdapter {

    int numoftabs;
    public pageradapter(FragmentManager fm) {
        super(fm);
        this.numoftabs=2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                tab1 t1=new tab1();
                return t1;
            case 1:
                tab2 t2=new tab2();
                return t2;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
