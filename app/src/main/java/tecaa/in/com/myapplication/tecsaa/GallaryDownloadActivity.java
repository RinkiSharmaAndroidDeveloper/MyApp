package tecaa.in.com.myapplication.tecsaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.GridLayout;

import tecaa.in.com.myapplication.tecsaa.Adapters.GallaryAdapter;
import tecaa.in.com.myapplication.tecsaa.Adapters.GallaryDownloadAdapter;

public class GallaryDownloadActivity extends AppCompatActivity {
RecyclerView recyclerView;
    GallaryDownloadAdapter gallaryDownloadAdapter;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary_download);
        recyclerView =findViewById(R.id.my_recycler_view);
        gallaryDownloadAdapter = new GallaryDownloadAdapter(GallaryDownloadActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(GallaryDownloadActivity.this);
        recyclerView.setLayoutManager(new GridLayoutManager(GallaryDownloadActivity.this, 2, GridLayout.VERTICAL, false));

        recyclerView.setAdapter(gallaryDownloadAdapter);
    }
}
