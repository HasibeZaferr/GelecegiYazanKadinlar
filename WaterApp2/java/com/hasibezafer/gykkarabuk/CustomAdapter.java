package com.hasibezafer.gykkarabuk;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by hasibezafer on 11.06.2017.
 */

public class CustomAdapter extends BaseAdapter {

    private LayoutInflater userInflater;
    private List<User> userList;


    public CustomAdapter(Activity activity, List<User> userList)
    {
        userInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.userList = userList;
    }


    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View lineView;
        lineView = userInflater.inflate(R.layout.custom_layout,null);
        TextView tvUser = (TextView) lineView.findViewById(R.id.tvUserName);
        TextView tvAmount = (TextView) lineView.findViewById(R.id.tvAmount);
        ImageView ivUser = (ImageView) lineView.findViewById(R.id.ivUserPicture);


        User user = userList.get(i);
        tvUser.setText(user.getUserName());
        tvAmount.setText(user.getWaterAmount());

        if(user.isUserGender())
        {
            ivUser.setImageResource(R.drawable.womenprofile);
        }

        else
        {
            ivUser.setImageResource(R.drawable.manprofile);
        }


        return lineView;
    }
}
