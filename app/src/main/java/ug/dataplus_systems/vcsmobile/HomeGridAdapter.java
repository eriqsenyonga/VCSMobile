package ug.dataplus_systems.vcsmobile;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eriq on 1/14/2016.
 */
public class HomeGridAdapter extends RecyclerView.Adapter<HomeGridAdapter.ViewHolder> {

    List<HomeGridItem> mItems;
    Context mContext;

    public HomeGridAdapter(Context context) {
        super();

        mContext = context;

        mItems = new ArrayList<HomeGridItem>();
        HomeGridItem myProfile = new HomeGridItem();
        myProfile.setName("My Profile");
        myProfile.setThumbnail(R.drawable.ic_home_my_profile);
        mItems.add(myProfile);

        HomeGridItem projectStats = new HomeGridItem();
        projectStats.setName("Project Stats");
        projectStats.setThumbnail(R.drawable.ic_home_project_stats);
        mItems.add(projectStats);

        HomeGridItem myTasks = new HomeGridItem();
        myTasks.setName("My Tasks");
        myTasks.setThumbnail(R.drawable.ic_home_tasks);
        mItems.add(myTasks);

        HomeGridItem news = new HomeGridItem();
        news.setName("News");
        news.setThumbnail(R.drawable.ic_home_news);
        mItems.add(news);


    }


    @Override
    public HomeGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item_home_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HomeGridAdapter.ViewHolder holder, int position) {
        HomeGridItem item = mItems.get(position);
        holder.tvItemName.setText(item.getName());
        holder.icon.setImageResource(item.getThumbnail());

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(mContext, "LOng#" + position, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "#" + position, Toast.LENGTH_SHORT).show();
                    if (position == 0) {

                        Intent intent = new Intent(mContext, MyProfile.class);
                        mContext.startActivity(intent);
                    }

                    if (position == 1) {

                        Intent intent = new Intent(mContext, ProjectStats.class);
                        mContext.startActivity(intent);
                    }

                    if (position == 2) {

                        Intent intent = new Intent(mContext, MyTasksActivity.class);
                        mContext.startActivity(intent);
                    }

                    if (position == 3) {

                        Intent intent = new Intent(mContext, NewsActivity.class);
                        mContext.startActivity(intent);
                    }
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public ImageView icon;
        public TextView tvItemName;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.iv_home_icon);
            tvItemName = (TextView) itemView.findViewById(R.id.tv_home_title);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }
}
