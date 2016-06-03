package ug.dataplus_systems.vcsmobile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eriq on 3/4/2016.
 */
public class PapListLiveAdapter extends RecyclerView.Adapter<PapListLiveAdapter.ViewHolder> {

    private ImageLoader imageLoader;
    List<PapLive> papLives;
    Context context;


    public PapListLiveAdapter(Context c, List<PapLive> paramList) {

        this.context = c;
        this.papLives = new ArrayList();
        this.papLives = paramList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pap_list_item_live, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PapLive localPapLive = (PapLive) this.papLives.get(position);
        this.imageLoader = CustomVolleyRequest.getInstance(this.context).getImageLoader();
        this.imageLoader.get(localPapLive.getPapPhotoUrl(), ImageLoader.getImageListener(holder.ivPapPhoto,
                R.drawable.default_pap_photo, R.drawable.default_pap_photo));
        holder.ivPapPhoto.setImageUrl(localPapLive.getPapPhotoUrl(), this.imageLoader);
        holder.tvHHID.setText("HHID: " + localPapLive.getHhid());
        holder.tvPapName.setText(localPapLive.getName());

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, final int position, boolean isLongClick) {
                if (isLongClick) {


                    Toast.makeText(PapListLiveAdapter.this.context, "Long:" + ((PapLive) PapListLiveAdapter.this.papLives.get(position)).getName() + "\n dialog show", Toast.LENGTH_LONG).show();
                    final String[] papLiveClickOptions = PapListLiveAdapter.this.context.getResources().getStringArray(R.array.live_pap_click_options);


                    AlertDialog.Builder localBuilder = new AlertDialog.Builder(PapListLiveAdapter.this.context);

                    localBuilder.setItems(papLiveClickOptions, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int position) {
                            if (position == 0) {
                                //if view pap is clicked
                                PapListLiveAdapter.this.viewPap(position);
                            }

                            if (position == 1) {
                                //if edit pap is clicked
                                Toast.makeText(PapListLiveAdapter.this.context, "Edit..coming soon", Toast.LENGTH_LONG).show();
                                return;
                            }
                            if (position == 2)
                                //if delete pap is clicked
                                Toast.makeText(PapListLiveAdapter.this.context, "Delete..coming soon", Toast.LENGTH_LONG).show();
                        }
                    });
                    localBuilder.create().show();
                    return;
                }

                Toast.makeText(PapListLiveAdapter.this.context, "Short:" + ((PapLive) PapListLiveAdapter.this.papLives.get(position)).getName(), Toast.LENGTH_LONG).show();
                PapListLiveAdapter.this.viewPap(position);
            }
        });

    }

    @Override
    public int getItemCount() {

        if (this.papLives != null) {
            return this.papLives.size();
        }
        return 0;
    }

    public void clearData() {

        if (this.papLives != null) {
            this.papLives.clear();
        }
        notifyDataSetChanged();

    }

    private void viewPap(int paramInt) {
        PapLive selectedPap = papLives.get(paramInt);
        Bundle localBundle = new Bundle();
        localBundle.putString("hhid", selectedPap.getHhid());
        localBundle.putString("name", selectedPap.getName());
        localBundle.putString("sex", selectedPap.getSex());
        localBundle.putString("dateOfBirth", selectedPap.getDateOfBirth());
        localBundle.putString("physicalAddress", selectedPap.getPhysicalAddress());
        localBundle.putString("papStatus", selectedPap.getPapStatus());
        localBundle.putString("plotReference", selectedPap.getPlotReference());
        localBundle.putString("photoUrl", selectedPap.getPapPhotoUrl());
        localBundle.putString("birthPlace", selectedPap.getBirthPlace());
        if (selectedPap.isMarried()) {
            localBundle.putString("isMarried", "Yes");
        } else {
            localBundle.putString("isMarried", "No");
        }

        Intent i = new Intent(this.context, PapViewLive.class);
        i.putExtra("papBundle", localBundle);
        this.context.startActivity(i);
        return;


    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        private ItemClickListener clickListener;
        TextView tvPapName;
        NetworkImageView ivPapPhoto;
        TextView tvHHID;

        public ViewHolder(View itemView) {
            super(itemView);

            tvPapName = (TextView) itemView.findViewById(R.id.tv_papName);
            ivPapPhoto = (NetworkImageView) itemView.findViewById(R.id.iv_papPhoto);
            tvHHID = (TextView) itemView.findViewById(R.id.tv_papHHID);


            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.clickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            this.clickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }
    }
}
