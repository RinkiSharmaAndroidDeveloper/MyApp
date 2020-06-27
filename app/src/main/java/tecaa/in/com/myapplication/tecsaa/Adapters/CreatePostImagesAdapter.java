package tecaa.in.com.myapplication.tecsaa.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import tecaa.in.com.myapplication.tecsaa.AsyncResult;
import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.ui.gallary.AlbumsFiles;
import tecaa.in.com.myapplication.tecsaa.ui.gallary.GalleryData;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CreatePostImagesAdapter extends RecyclerView.Adapter<CreatePostImagesAdapter.MyViewHolder> {
    Context context;
    AsyncResult<String> clickCameraDataAsyncResult;
    List<AlbumsFiles> galleryData;
    AsyncResult<String> deleteCameraDataAsyncResult;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView plusImageView,fullImage,delete;
        LinearLayout linearLayout;
        TextView text;


        public MyViewHolder(View view) {
            super(view);
            plusImageView = view.findViewById(R.id.image);
            fullImage = view.findViewById(R.id.images);
            delete = view.findViewById(R.id.cross);
            text = view.findViewById(R.id.text);

        }
    }

    public CreatePostImagesAdapter(Context context, List<AlbumsFiles> galleryData, AsyncResult<String> clickCameraDataAsyncResult,  AsyncResult<String> deleteCameraDataAsyncResult) {
        this.context = context;
        this.galleryData = galleryData;
        this.clickCameraDataAsyncResult = clickCameraDataAsyncResult;
        this.deleteCameraDataAsyncResult = deleteCameraDataAsyncResult;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_create_gallery, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

       if(position==0){
            holder.text.setVisibility(View.VISIBLE);
            holder.plusImageView.setVisibility(View.VISIBLE);
            holder.fullImage.setVisibility(View.GONE);
            holder.delete.setVisibility(View.GONE);
           holder.plusImageView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   clickCameraDataAsyncResult.success("data");
               }
           });
       }else{
           holder.text.setVisibility(View.GONE);
           holder.plusImageView.setVisibility(View.GONE);
           holder.fullImage.setVisibility(View.VISIBLE);
           holder.delete.setVisibility(View.VISIBLE);
           if(galleryData.get(position).getName()!=null)

           Glide.with(context)
                   .load(galleryData.get(position).getName())
                   .fitCenter()
                   .into(holder.fullImage);




           holder.delete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   deleteCameraDataAsyncResult.success(galleryData.get(position).getId());
               }
           });
       }

    }

    @Override
    public int getItemCount() {
        return galleryData.size();
    }
}