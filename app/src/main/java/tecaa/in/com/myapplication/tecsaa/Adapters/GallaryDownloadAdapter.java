package tecaa.in.com.myapplication.tecsaa.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import tecaa.in.com.myapplication.tecsaa.GallaryDownloadActivity;
import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.ui.classWork.ClassWorkModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class GallaryDownloadAdapter  extends RecyclerView.Adapter<GallaryDownloadAdapter.MyViewHolder> {

    private List<ClassWorkModel> moviesList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;



        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);


        }
    }


    public GallaryDownloadAdapter( Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_gallary_download, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


    }

    @Override
    public int getItemCount() {
        return 10;
    }
}