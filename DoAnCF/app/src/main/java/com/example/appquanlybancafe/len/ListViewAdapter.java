package com.example.appquanlybancafe.len;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanlybancafe.R;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {
    private List<LoaiMon> items;
    private Context context;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public ListViewAdapter(List<LoaiMon> items)
    {
        this.items=items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.item_categories,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.imageView.setImageResource(items.get(position).getDrawableId());
        //holder.textView.setText(items.get(position).getName());
        holder.button.setText(items.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //private ImageView imageView;
        //private TextView textView;
        private Button button;
        private TextView txtGia;
        private TextView txtSL;
        public ViewHolder(final View view) {
            super(view);
            //imageView = (ImageView) view.findViewById(R.id.image);
            //textView = (TextView)view.findViewById(R.id.text);
            button=view.findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(view, getAdapterPosition());
                    }
                }
            });
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                //User user = users.get(position);
                // We can access the data within the views
                //Toast.makeText(context, button.getText(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
