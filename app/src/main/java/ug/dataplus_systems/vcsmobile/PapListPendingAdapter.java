package ug.dataplus_systems.vcsmobile;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import de.hdodenhof.circleimageview.CircleImageView;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pap_list_item_pending, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemClickListener clickListener;
        TextView tvPapName;
        CircleImageView ivPapPhoto;
        View viewStatusIndicator;

        public ViewHolder(View itemView) {
            super(itemView);

            tvPapName = (TextView) itemView.findViewById(R.id.tv_papName);
            ivPapPhoto = (CircleImageView) itemView.findViewById(R.id.iv_papPhoto);
            viewStatusIndicator = (View) itemView.findViewById(R.id.view_status_indicator);


        }

        public void bindData(final Cursor cursor) {
             /*PAP name*/
            String papName = cursor.getString(cursor.getColumnIndex(DbClass.KEY_NAME));
            //   String dateForDisp = mCC.dateForDisplayNew(dateFromDb);
            tvPapName.setText(papName);

            Log.d("complete", cursor.getString(cursor.getColumnIndex(DbClass.KEY_COMPLETE)));

            if (cursor.getString(cursor.getColumnIndex(DbClass.KEY_COMPLETE)).equals("true")) {

                viewStatusIndicator.setBackgroundColor(Color.parseColor("#FF3E8F04"));

            } else {

                viewStatusIndicator.setBackgroundColor(Color.parseColor("#d48703"));
            }

            try {
//                ivPapPhoto.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndex(DbClass.KEY_PHOTO))));
            }catch (Exception e){
                e.printStackTrace();
            }

            Log.d("URI IMAGE","RANDOME: " +  cursor.getString(cursor.getColumnIndex(DbClass.KEY_PHOTO)));
        }
    }
}
