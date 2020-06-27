package tecaa.in.com.myapplication.tecsaa.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import tecaa.in.com.myapplication.tecsaa.R;

import androidx.recyclerview.widget.RecyclerView;

public class ExamAdapters extends RecyclerView.Adapter<ExamAdapters.MyViewHolder> {


    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        LinearLayout linearLayout;


        public MyViewHolder(View view) {
            super(view);


        }
    }


    public ExamAdapters( Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_exams, parent, false);

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