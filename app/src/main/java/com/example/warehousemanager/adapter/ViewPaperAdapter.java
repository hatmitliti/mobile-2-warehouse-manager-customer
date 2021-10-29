package com.example.warehousemanager.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.warehousemanager.screen.Home;
import com.example.warehousemanager.screen.Profile;
import com.example.warehousemanager.screen.ScreenCart;


public class ViewPaperAdapter extends FragmentStateAdapter {
    public ViewPaperAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Home();
            case 1:
                return new ScreenCart();
            case 2:
                return new Profile();
            default:
                return new Home();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
