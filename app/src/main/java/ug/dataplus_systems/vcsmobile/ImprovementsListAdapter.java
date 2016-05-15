package ug.dataplus_systems.vcsmobile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by senyer on 5/13/2016.
 */
public class ImprovementsListAdapter extends RecyclerView.Adapter<ImprovementsListAdapter.ViewHolder> {

    List<Improvement> improvementsListToShow;
    Context context;


    public ImprovementsListAdapter(Context c, List<Improvement> improvementListToShow) {

        this.context = c;
        this.improvementsListToShow = improvementListToShow;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.improvement_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Improvement improvement = improvementsListToShow.get(position);
        holder.tvCategory.setText(improvement.getCategory());
        holder.tvSubCategory.setText(improvement.getSubCategory());
        holder.tvArea.setText(improvement.getArea() + " " + improvement.getUnit());

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                //do something when improvement is clicked

                Toast.makeText(context, "Improvement interaction Coming soon", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (this.improvementsListToShow != null) {
            return this.improvementsListToShow.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvCategory, tvSubCategory, tvArea;
        ItemClickListener clickListener;

        public ViewHolder(View itemView) {

            super(itemView);

            tvCategory = (TextView) itemView.findViewById(R.id.tv_category);
            tvSubCategory = (TextView) itemView.findViewById(R.id.tv_sub_category);
            tvArea = (TextView) itemView.findViewById(R.id.tv_area);


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
