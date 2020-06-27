package tecaa.in.com.myapplication.tecsaa;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tecaa.in.com.myapplication.tecsaa.Adapters.TimeTableAdapters;

public class TeacherHomeFragment extends Fragment {



    RecyclerView recyclerView;
    TimeTableAdapters timeTableAdapter;
    @SuppressLint("WrongConstant")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_timetable_child, container, false);
        recyclerView =root.findViewById(R.id.my_recycler_view);

        timeTableAdapter = new TimeTableAdapters(getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(timeTableAdapter);

        return root;
    }
}
