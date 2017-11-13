package com.snehpandya.offlinefirst.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.snehpandya.offlinefirst.R;
import com.snehpandya.offlinefirst.database.Result;
import com.snehpandya.offlinefirst.databinding.ListItemBinding;
import com.snehpandya.offlinefirst.viewholder.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sneh.pandya on 10/11/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Result> mResults = new ArrayList<>();

    public RecyclerAdapter() {
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemBinding listItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item, parent, false);
        return new RecyclerViewHolder(listItemBinding);
    }

    public void addUsers(List<Result> results) {
        this.mResults = results;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Result result = mResults.get(position);
        holder.setResult(result);
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    @BindingAdapter({"android:src"})
    public static void setImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
        imageView.setAdjustViewBounds(true);
    }

    @BindingAdapter({"text"})
    public static void setName(TextView textView, Result name) {
        String string = String.valueOf((name.getTitle()).charAt(0)).toUpperCase() + name.getTitle().substring(1)
                + " " + String.valueOf((name.getFirstName()).charAt(0)).toUpperCase() + name.getFirstName().substring(1)
                + " " + String.valueOf((name.getLastName()).charAt(0)).toUpperCase() + name.getLastName().substring(1);
        textView.setText(string);
    }
}
