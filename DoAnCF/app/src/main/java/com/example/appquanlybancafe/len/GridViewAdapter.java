package com.example.appquanlybancafe.len;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanlybancafe.R;

import java.text.DecimalFormat;
import java.util.List;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder> {
    private List<ThucDon> items;
    private Activity activity;
    private  OnItemClickListener listener;
    Context context1;
    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
    public GridViewAdapter(Activity activity, List<ThucDon> items)
    {
        this.context1=activity;
        this.activity=activity;
        this.items=items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.item_fb,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // holder.imageView.setImageResource(R.drawable.coca);
        holder.imageView.setImageBitmap(ThucDon.convertStringToBitmapFromAccess(context1,items.get(position).hinhAnh));
        holder.textView.setText(items.get(position).getTenMon());

        try {
            Double giaC=items.get(position).getDonGia()/1000;
            holder.txtGia.setText((new DecimalFormat("#0").format(giaC)) + "K");
            try
            {
                if(items.get(position).getSlDat()>0) {
                    holder.txtSL.setVisibility(View.VISIBLE);
                    holder.txtSL.setText("x" + items.get(position).getSlDat());
                }
                else
                    holder.txtSL.setVisibility(View.INVISIBLE);
            }
            catch (Exception e)
            {
                holder.txtSL.setVisibility(View.INVISIBLE);
            }
        }catch(Exception e)
        {

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textView;
        private TextView txtGia;
        private TextView txtSL;
        public ViewHolder(final View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text);
            imageView = (ImageView) view.findViewById(R.id.image);
            txtGia=view.findViewById(R.id.giaSP);
            txtSL=view.findViewById(R.id.slDat);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onItemClick(view,getAdapterPosition());
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }

}
