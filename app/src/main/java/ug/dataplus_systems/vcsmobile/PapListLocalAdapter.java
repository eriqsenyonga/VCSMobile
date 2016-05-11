package ug.dataplus_systems.vcsmobile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Eriq on 11/30/2015.
 */
public class PapListLocalAdapter extends RecyclerView.Adapter<PapListLocalAdapter.ViewHolder> {

    List<PapListItemLocal> items;
    private List<PapListItemLocal> visibleItems;
    Context mContext;

    public PapListLocalAdapter(Context context) {

        mContext = context;

        items = new ArrayList<PapListItemLocal>();
        visibleItems = new ArrayList<PapListItemLocal>();


        PapListItemLocal item = new PapListItemLocal("Musajjagulanyago Solomon", 1);
        items.add(item);
        PapListItemLocal item2 = new PapListItemLocal("Difasi Ngobi", 2);
        items.add(item2);
        PapListItemLocal item3 = new PapListItemLocal("Mukene Yoweri", 3);
        items.add(item3);
        PapListItemLocal item4 = new PapListItemLocal("Mukajjanga Yousuf", 4);
        items.add(item4);
        PapListItemLocal item5 = new PapListItemLocal("Jangu Sabiti", 5);
        items.add(item5);

        visibleItems.addAll(items);
    }


    public void flushFilter() {
        visibleItems = new ArrayList<PapListItemLocal>();
        visibleItems.addAll(items);
        notifyDataSetChanged();
    }

    public void setFilter(String queryText) {

        visibleItems = new ArrayList<PapListItemLocal>();
        //  constraint = constraint.toString().toLowerCase();
        for (PapListItemLocal item : items) {
            if (item.getName().toLowerCase().contains(queryText.toLowerCase())) {
                visibleItems.add(item);
            }


        }

        if (visibleItems.size() == 0) {
            Toast.makeText(mContext, "No search result found!", Toast.LENGTH_SHORT).show();
        }

        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pap_list_item_unsynced, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PapListItemLocal papListItemLocal = visibleItems.get(position);
        holder.tvPapName.setText(papListItemLocal.getName());
    }

    @Override
    public int getItemCount() {

        return visibleItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPapName;
        ImageView ivPapPhoto;

        public ViewHolder(View itemView) {
            super(itemView);

            tvPapName = (TextView) itemView.findViewById(R.id.tv_papName);
            ivPapPhoto = (ImageView) itemView.findViewById(R.id.iv_papPhoto);

        }
    }
}
