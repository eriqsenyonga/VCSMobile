package ug.dataplus_systems.vcsmobile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senyer on 6/28/2016.
 */
public class OverviewRecyclerViewAdapter extends RecyclerView.Adapter<OverviewRecyclerViewAdapter.ViewHolder> {


    public static int OVERVIEW_ADDRESSES_LIST = 1;
    public static int OVERVIEW_FAMILY_MEMBERS_LIST = 2;
    public static int OVERVIEW_CROPS_LIST = 3;
    public static int OVERVIEW_IMPROVEMENTS_LIST = 4;

    List itemsList;
    Context context;
    int whichList;

    public OverviewRecyclerViewAdapter(Context c, List list, int which) {

        context = c;

        this.whichList = which;


        if (whichList == OVERVIEW_ADDRESSES_LIST) {

            itemsList = new ArrayList<Address>();

        }

        if (whichList == OVERVIEW_FAMILY_MEMBERS_LIST) {

            itemsList = new ArrayList<FamilyMember>();

        }

        if (whichList == OVERVIEW_CROPS_LIST) {

            itemsList = new ArrayList<Crop>();
        }

        if (whichList == OVERVIEW_IMPROVEMENTS_LIST) {

            itemsList = new ArrayList<Structure>();
        }

        itemsList = list;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_overview, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (whichList == OVERVIEW_ADDRESSES_LIST) {

            Address address = (Address) itemsList.get(position);

            bindFields(address.getPlotNoRoad(), address.getVillage(), holder);

        }

        if (whichList == OVERVIEW_FAMILY_MEMBERS_LIST) {

            FamilyMember familyMember = (FamilyMember) itemsList.get(position);

            bindFields(familyMember.getName(), familyMember.getRelationType(), holder);

        }

        if (whichList == OVERVIEW_CROPS_LIST) {

            Crop crop = (Crop) itemsList.get(position);

            bindFields(crop.getCropName(), crop.getQuantity() + " " + crop.getUnit(), holder);
        }

        if (whichList == OVERVIEW_IMPROVEMENTS_LIST) {

            Structure structure = (Structure) itemsList.get(position);
            bindFields(structure.getStructureName(), structure.getArea() + " " + structure.getUnit(), holder);
        }


    }

    private void bindFields(String title, String subtitle, ViewHolder holder) {

        holder.tvTitle.setText(title);
        holder.tvSubTitle.setText(subtitle);

    }

    @Override
    public int getItemCount() {

        if (itemsList != null) {

            if (itemsList.size() < 3) {

                return itemsList.size();
            } else {

                return 3;
            }
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView tvTitle, tvSubTitle;


        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
            tvSubTitle = (TextView) itemView.findViewById(R.id.tv_item_sub_title);


        }


    }
}
