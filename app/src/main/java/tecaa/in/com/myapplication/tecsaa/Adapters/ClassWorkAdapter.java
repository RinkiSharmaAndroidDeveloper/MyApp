package tecaa.in.com.myapplication.tecsaa.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.ui.classWork.ClassWorkModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ClassWorkAdapter extends RecyclerView.Adapter<ClassWorkAdapter.MyViewHolder> {

    private List<ClassWorkModel> moviesList;
Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date, time,Name,subject,link,textSubject;
        ImageView imageView,shareIcon;
        RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            Name = (TextView) view.findViewById(R.id.name);
            time = (TextView) view.findViewById(R.id.time);
            shareIcon = (ImageView) view.findViewById(R.id.share);
            subject = (TextView) view.findViewById(R.id.subject);
            link = (TextView) view.findViewById(R.id.link);
            textSubject = (TextView) view.findViewById(R.id.text);
            imageView = (ImageView) view.findViewById(R.id.image);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.layout_relative);

        }
    }


    public ClassWorkAdapter(List<ClassWorkModel> moviesList,Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_work_schedule, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ClassWorkModel movie = moviesList.get(position);
        if(movie.getDate()!=null&&!movie.getDate().equals("")){
           holder.date.setText(movie.getDate()+" | "+movie.getDay());
        }else{
            holder.relativeLayout.setVisibility(View.GONE);
        }
        if(movie.getLink()!=null){
           holder.link.setText(movie.getLink());
        }else{
            holder.link.setVisibility(View.GONE);
        }

           holder.time.setText(movie.getTime());
           holder.Name.setText(movie.getTeacherName());
           holder.textSubject.setText(movie.getSubjectText());

        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(movie.getLink()));
                context.startActivity(i);
            }
        });

        holder.shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                context.startActivity(shareIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}