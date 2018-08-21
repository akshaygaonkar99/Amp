package com.example.manjunath.farmerhelpapp.others;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manjunath.farmerhelpapp.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mcontext;
    private ArrayList<Upload> muploads;
    private OnItemClickListener mlistener;

    public ImageAdapter(Context mcontext, ArrayList<Upload> muploads) {
        this.mcontext = mcontext;
        this.muploads = muploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.image_item,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload uploadCurrent = muploads.get(position);
        holder.Contactno.setText(uploadCurrent.getContactno());
        holder.textDistrict.setText(uploadCurrent.getDistrict());
        Picasso.with(mcontext)
                .load(uploadCurrent.getMimageurl())
                .placeholder(R.drawable.loading)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount()
    {
        return muploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
    View.OnCreateContextMenuListener,MenuItem.OnMenuItemClickListener{
        public TextView textDistrict,textname,textpincode,textrate,textdescription;
        public ImageView imageView;
        public TextView Contactno;
        public Button moreInfo,booknow;

        public ImageViewHolder(View itemView) {
            super(itemView);
            textDistrict = itemView.findViewById(R.id.imageddistrict);
            imageView = itemView.findViewById(R.id.imaged);
            Contactno = itemView.findViewById(R.id.contactd);
            moreInfo = itemView.findViewById(R.id.moreinfo);
            textname = itemView.findViewById(R.id.miname);
            booknow = itemView.findViewById(R.id.booking);
            booknow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mlistener.onbooking(position);
                        }
                    }
                }
            });
            //itemView.setOnClickListener(this);
            //itemView.setOnCreateContextMenuListener(this);
            moreInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mlistener.onmoreinfoClick(position);
                        }
                    }
                }
            });

        }

        @Override
        public void onClick(View v) {
            if (mlistener != null){
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    mlistener.onmoreinfoClick(position);
                    mlistener.onbooking(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem delete = menu.add(Menu.NONE, 1,1,"Delete");
            delete.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mlistener != null){
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    switch (item.getItemId()){
                        case 1:
                            mlistener.ondeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }

    public interface OnItemClickListener {
        void ondeleteClick(int position);
        void onmoreinfoClick(int position);
        void onbooking(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mlistener = listener;
    }
}
