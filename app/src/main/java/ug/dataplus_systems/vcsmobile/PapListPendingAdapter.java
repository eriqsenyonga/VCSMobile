package ug.dataplus_systems.vcsmobile;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by senyer on 6/2/2016.
 */
public class PapListPendingAdapter extends CursorRecyclerAdapter<PapListPendingAdapter.ViewHolder> {


    Context context;


    public PapListPendingAdapter(Context context, Cursor c) {
        super(c);

        this.context = context;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Cursor cursor) {
        holder.bindData(cursor);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pap_list_item_live, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemClickListener clickListener;
        TextView tvPapName;
        NetworkImageView ivPapPhoto;
        TextView tvHHID;

        public ViewHolder(View itemView) {
            super(itemView);

            tvPapName = (TextView) itemView.findViewById(R.id.tv_papName);
            ivPapPhoto = (NetworkImageView) itemView.findViewById(R.id.iv_papPhoto);
            tvHHID = (TextView) itemView.findViewById(R.id.tv_papHHID);
        }

        public void bindData(final Cursor cursor) {
             /*PAP name*/
            String papName = cursor.getString(cursor.getColumnIndex(DbClass.KEY_NAME));
            //   String dateForDisp = mCC.dateForDisplayNew(dateFromDb);
            tvPapName.setText(papName);


        }
    }
}
