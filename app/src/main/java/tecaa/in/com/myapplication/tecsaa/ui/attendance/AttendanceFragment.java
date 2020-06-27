package tecaa.in.com.myapplication.tecsaa.ui.attendance;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tecaa.in.com.myapplication.tecsaa.Adapters.AttendanceAdapter;
import tecaa.in.com.myapplication.tecsaa.Adapters.ClassWorkAdapter;
import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.ui.classWork.ClassWorkFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class AttendanceFragment extends Fragment {

    private AttendanceViewModel sendViewModel;
    RecyclerView recyclerView;
    AttendanceAdapter mAdapter;
    Context context;

    public AttendanceFragment newInstance(Context context) {
        AttendanceFragment fragment = new AttendanceFragment();
        this.context  = context;
        return fragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(AttendanceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_attendance, container, false);
        recyclerView =root.findViewById(R.id.my_recycler_view);

        mAdapter = new AttendanceAdapter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(mAdapter);
        return root;
    }
}