package tecaa.in.com.myapplication.tecsaa.ui.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tecaa.in.com.myapplication.tecsaa.Adapters.ResultsAdapters;
import tecaa.in.com.myapplication.tecsaa.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ResultsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    ResultsAdapters mAdapter;
    RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_results, container, false);
        recyclerView =root.findViewById(R.id.my_recycler_view);

        mAdapter = new ResultsAdapters(getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        return root;
    }
}