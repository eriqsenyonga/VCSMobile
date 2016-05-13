package ug.dataplus_systems.vcsmobile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senyer on 5/12/2016.
 */
public class CropsListAdapter extends RecyclerView.Adapter<CropsListAdapter.ViewHolder> {

    List<Crop> cropsListToShow;
    Context context;

    public CropsListAdapter(Context c, List<Crop> cropsList) {

        this.context = c;
        cropsListToShow = new ArrayList();
        cropsListToShow = cropsList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.crops_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Crop crop = cropsListToShow.get(position);
        holder.tvCropName.setText(crop.getCropName());
        holder.tvQuantityUnits.setText(crop.getQuantity() + " " + crop.getUnit());

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //do something when crop is clicked

                Toast.makeText(context, "Crop interaction Coming soon", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {

        if (this.cropsListToShow != null) {
            return this.cropsListToShow.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvCropName;
        TextView tvQuantityUnits;
        ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            tvCropName = (TextView) itemView.findViewById(R.id.tv_crop_name);
            tvQuantityUnits = (TextView) itemView.findViewById(R.id.tv_crop_quantity_units);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            this.clickListener.onClick(v, getAdapterPosition(), false);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }
    }
}
