package com.snehpandya.offlinefirst.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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
}
