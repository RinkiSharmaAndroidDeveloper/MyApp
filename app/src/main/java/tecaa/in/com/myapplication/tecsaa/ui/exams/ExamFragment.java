package tecaa.in.com.myapplication.tecsaa.ui.exams;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tecaa.in.com.myapplication.tecsaa.Adapters.ExamAdapters;
import tecaa.in.com.myapplication.tecsaa.Adapters.ResultsAdapters;
import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.ui.result.ToolsViewModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExamFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    ExamAdapters mAdapter;
    RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_exam, container, false);
        recyclerView =root.findViewById(R.id.my_recycler_view);

        mAdapter = new ExamAdapters(getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        return root;
    }
}