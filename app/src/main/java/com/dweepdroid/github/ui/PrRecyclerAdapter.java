package com.dweepdroid.github.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dweepdroid.github.R;
import com.dweepdroid.github.customview.NaviTextView;
import com.dweepdroid.github.model.PullRequest;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PrRecyclerAdapter extends RecyclerView.Adapter<PrRecyclerAdapter.ViewHolder> {

    List<PullRequest> prList;

    public PrRecyclerAdapter(List<PullRequest> prList){
        this.prList = prList;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View prView = inflater.inflate(R.layout.layout_pr_list_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(prView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PrRecyclerAdapter.ViewHolder holder, int position) {
        PullRequest pr = prList.get(position);

        holder.prTitleTv.setText(pr.getTitle());
        holder.prDescTv.setText(pr.getBody());
        holder.prCreatedAtTv.setText("Created: "+pr.getCreatedAt().toString());
        if(pr.getClosedAt() != null)
            holder.prClosedAtTv.setText("Closed: "+pr.getClosedAt().toString());
        else
            holder.prClosedAtTv.setText("Closed: NA");
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        NaviTextView prTitleTv;
        NaviTextView prDescTv;
        NaviTextView prCreatedAtTv;
        NaviTextView prClosedAtTv;

        public ViewHolder(View view){
            super(view);

            prTitleTv = (NaviTextView)view.findViewById(R.id.prTitle);
            prDescTv = (NaviTextView)view.findViewById(R.id.prDescription);
            prCreatedAtTv = (NaviTextView)view.findViewById(R.id.prCreatedAt);
            prClosedAtTv = (NaviTextView)view.findViewById(R.id.prClosedAt);
        }
    }

    @Override
    public int getItemCount() {
        return prList.size();
    }

    public void updateList(List<PullRequest> updatedList){
        prList = updatedList;
        notifyDataSetChanged();
    }
}
