package com.example.paypassworddemo.pay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.paypassworddemo.R;


public class InputPwdNumberAdapter extends BaseAdapter {

    private String mNumbers = "123456789C0#";
    private Context mContext;

    public InputPwdNumberAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mNumbers.length();
    }

    @Override
    public String getItem(int position) {
        return String.valueOf(mNumbers.charAt(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHolder itemHolder;
        if (convertView == null) {
            itemHolder = new ItemHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_view_input_group_code, null);
            itemHolder.mRootView = convertView.findViewById(R.id.number_root_view);
            itemHolder.mNumberTextView = convertView.findViewById(R.id.number_textView);
            itemHolder.mDeleteImageView = convertView.findViewById(R.id.number_delete_imageView);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }
        String curNumber = getItem(position);
        switch (curNumber) {
            case "C":
                itemHolder.mDeleteImageView.setVisibility(View.GONE);
                itemHolder.mNumberTextView.setVisibility(View.VISIBLE);
                itemHolder.mNumberTextView.setText(curNumber);
                itemHolder.mRootView.setBackgroundColor(mContext.getResources().getColor(R.color.light_gray));
                break;
            case "#":
                itemHolder.mRootView.setBackgroundColor(mContext.getResources().getColor(R.color.light_gray));
                itemHolder.mNumberTextView.setVisibility(View.GONE);
                itemHolder.mDeleteImageView.setVisibility(View.VISIBLE);
                break;
            default:
                itemHolder.mRootView.setBackgroundResource(R.drawable.list_selector);
                itemHolder.mDeleteImageView.setVisibility(View.GONE);
                itemHolder.mNumberTextView.setVisibility(View.VISIBLE);
                itemHolder.mNumberTextView.setText(curNumber);
                break;
        }
        return convertView;
    }


    private static class ItemHolder {
        RelativeLayout mRootView;
        TextView mNumberTextView;
        ImageView mDeleteImageView;
    }
}
