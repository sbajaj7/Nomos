package com.sahib.nomos.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sahib.nomos.R;
import com.sahib.nomos.models.AssetsResponse;

import java.util.List;

/**
 * Created by Sahib on 2/11/2018.
 */

public class AssetsAdapter extends RecyclerView.Adapter<AssetsAdapter.MyViewHolder> {
    private List<AssetsResponse> assetsResponseList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, assetId;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            assetId = (TextView) view.findViewById(R.id.assetId);
            //year = (TextView) view.findViewById(R.id.year);
        }
    }

    public AssetsAdapter(List<AssetsResponse> assetsResponseList) {
        this.assetsResponseList = assetsResponseList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assets, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AssetsResponse assets = assetsResponseList.get(position);
        holder.assetId.setText(assets.getAsset_id());
        holder.name.setText(assets.getName());
        //holder.year.setText(assets.getYear());
    }

    @Override
    public int getItemCount() {
        return assetsResponseList.size();
    }
}
