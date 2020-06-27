package tecaa.in.com.myapplication.tecsaa.ui.classWork;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tecaa.in.com.myapplication.tecsaa.Adapters.ClassWorkAdapter;
import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ClassWorkFragment extends Fragment {

    private ClassWorkViewModel galleryViewModel;
    RecyclerView recyclerView;
    ClassWorkAdapter mAdapter;
    Context context;
    List<ClassWorkModel> classWorkModels;
    public ClassWorkFragment newInstance(Context context) {
        ClassWorkFragment fragment = new ClassWorkFragment();
        this.context  = context;
        return fragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(ClassWorkViewModel.class);
        View root = inflater.inflate(R.layout.fragment_class_work, container, false);

        recyclerView =root.findViewById(R.id.my_recycler_view);
        classWorkModels =new ArrayList<>();
        mAdapter = new ClassWorkAdapter(classWorkModels,getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(mAdapter);
       /* galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        prepareMovieData();
        return root;
    }
    private void prepareMovieData() {
        ClassWorkModel classWorkModel = new ClassWorkModel("23 Mar 2020","Sunday", "Riya sharma", "12:00","Read eassy complete","English","https://www.worldometers.info/coronavirus/","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.","");
        classWorkModels.add(classWorkModel);

        ClassWorkModel classWorkModel1 = new ClassWorkModel("23 Mar 2020","Sunday", "Riya sharma", "12:00","Read eassy complete","English","https://www.worldometers.info/coronavirus/","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.","");
        classWorkModels.add(classWorkModel1);

        ClassWorkModel classWorkModel2 = new ClassWorkModel("23 Mar 2020","Sunday", "Riya sharma", "12:00","Read eassy complete","English","https://www.worldometers.info/coronavirus/","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.","");
        classWorkModels.add(classWorkModel2);

        ClassWorkModel classWorkModel3 = new ClassWorkModel("","", "Riya sharma", "12:00","Learn two table","Mathmatics","https://www.worldometers.info/coronavirus/","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.","");
        classWorkModels.add(classWorkModel3);

        ClassWorkModel classWorkModel4 = new ClassWorkModel("23 Mar 2020","Sunday", "Riya sharma", "12:00","Read eassy complete","English","https://www.worldometers.info/coronavirus/","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.","");
        classWorkModels.add(classWorkModel4);
    }
}