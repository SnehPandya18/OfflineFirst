package com.snehpandya.offlinefirst.viewholder;

import android.support.v7.widget.RecyclerView;

import com.snehpandya.offlinefirst.database.Result;
import com.snehpandya.offlinefirst.databinding.ListItemBinding;

/**
 * Created by sneh.pandya on 10/11/17.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private ListItemBinding mListItemBinding;

    public RecyclerViewHolder(ListItemBinding listItemBinding) {
        super(listItemBinding.getRoot());
        this.mListItemBinding = listItemBinding;
    }

    public void setResult(Result result) {
        mListItemBinding.setResult(result);
        mListItemBinding.executePendingBindings();
    }
}
