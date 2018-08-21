package com.example.manjunath.farmerhelpapp.others;

import android.content.Context;
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
import android.widget.Toast;

import com.example.manjunath.farmerhelpapp.R;
import com.example.manjunath.farmerhelpapp.activity.YourUploads;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter1 extends RecyclerView.Adapter<ImageAdapter1.ImageViewHolder> {
    private Context mcontext;
    private FirebaseStorage mstorage;
    private ArrayList<Upload> muploads;
    private OnItemClickListener mlistener;

    public ImageAdapter1(Context mcontext, ArrayList<Upload> muploads) {
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

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textDistrict,textname,textpincode,textrate,textdescription;
        public ImageView imageView;
        public TextView Contactno;
        public Button moreInfo,deletebutton;

        public ImageViewHolder(View itemView) {
            super(itemView);
            textDistrict = itemView.findViewById(R.id.imageddistrict);
            imageView = itemView.findViewById(R.id.imaged);
            Contactno = itemView.findViewById(R.id.contactd);
            moreInfo = itemView.findViewById(R.id.moreinfo);
            textname = itemView.findViewById(R.id.miname);
            deletebutton = itemView.findViewById(R.id.booking);
            deletebutton.setText("Delete");
            deletebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            DatabaseReference mDatabaseRef;
                            mstorage = FirebaseStorage.getInstance();
                            Upload selecteditem = muploads.get(position);
                            final String selectedkey = selecteditem.getId();
                            StorageReference imageref = mstorage.getReferenceFromUrl(selecteditem.getMimageurl());
                            mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads/"+selecteditem.getCategory()).child(selectedkey);
                            mDatabaseRef.removeValue();
                            imageref.delete();
                            mlistener.onmoreinfoClick(position);
                        }
                    }
                }
            });
            moreInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mlistener.oninfoClick(position);
                        }
                    }
                }
            });
            //itemView.setOnCreateContextMenuListener(this);
            //moreInfo.setOnClickListener(this);
        }

    }

    public interface OnItemClickListener {

        void onmoreinfoClick(int position);
        void oninfoClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mlistener = listener;
    }
}