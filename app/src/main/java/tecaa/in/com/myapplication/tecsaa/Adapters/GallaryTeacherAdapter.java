package tecaa.in.com.myapplication.tecsaa.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import tecaa.in.com.myapplication.tecsaa.CreateAlbumActivity;
import tecaa.in.com.myapplication.tecsaa.R;

import tecaa.in.com.myapplication.tecsaa.ui.TeacherGallery.GalleryDataClass;


import java.util.ArrayList;
import java.util.List;


import androidx.recyclerview.widget.RecyclerView;

public class GallaryTeacherAdapter  extends RecyclerView.Adapter<GallaryTeacherAdapter.MyViewHolder> {


    Context context;
    ArrayList<GalleryDataClass> galleryDataClassArrayList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView backImge,plusImage,closeImage,editTextView;
         TextView totalImages,albumName;



        public MyViewHolder(View view) {
            super(view);
            backImge = (ImageView) view.findViewById(R.id.images);
            plusImage = (ImageView) view.findViewById(R.id.image);
            closeImage = (ImageView) view.findViewById(R.id.cross);
            editTextView = (ImageView) view.findViewById(R.id.edit_image);
            totalImages = view.findViewById(R.id.total_images);
            albumName =view.findViewById(R.id.album_name);


        }
    }

   public void setEmployeeList(ArrayList<GalleryDataClass> gallery){
        this.galleryDataClassArrayList=gallery;

    }
    public GallaryTeacherAdapter( Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_gallary, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if(position==0){
            holder.closeImage.setVisibility(View.GONE);
            holder.backImge.setVisibility(View.GONE);
            holder.editTextView.setVisibility(View.GONE);
            holder.totalImages.setVisibility(View.GONE);
            holder.albumName.setVisibility(View.VISIBLE);
            holder.plusImage.setVisibility(View.VISIBLE);
            holder.plusImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, CreateAlbumActivity.class);
                    context.startActivity(intent);
                }
            });

        }else{
            holder.closeImage.setVisibility(View.VISIBLE);
            holder.backImge.setVisibility(View.VISIBLE);
            holder.editTextView.setVisibility(View.VISIBLE);
            holder.totalImages.setVisibility(View.VISIBLE);
            holder.albumName.setVisibility(View.VISIBLE);
            holder.plusImage.setVisibility(View.GONE);

        }


    }

    @Override
    public int getItemCount() {
        return 1;
    }
}