package tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher;

import android.app.DatePickerDialog;
import android.content.Context;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import android.widget.Spinner;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import tecaa.in.com.myapplication.tecsaa.Adapters.TeacherAttendanceAdapter;


import tecaa.in.com.myapplication.tecsaa.AsyncTwoValue;
import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.SessionManager;

import tecaa.in.com.myapplication.tecsaa.ui.result.ToolsViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TeacherAttendanceFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    TeacherAttendanceAdapter mAdapter;
    RecyclerView recyclerView;
    Spinner sectionSpinner,subjectSpinner;
    TextView classSpinner;
    LinearLayout postBtn;
    Context context;
    ProgressBar progressBar;
    String titleStr,descripText;
    int section,subject,classStr;
    List<String> class_data,section_data,subject_data;
    SessionManager sessionManager;
    HashMap<String, String> user;
    List<Data> dataList,dataList1,dataList2;
    List<AttendanceModel> attendanceModels;
    String token=null;
    ArrayAdapter<String> arrayAdapter1;
    List<JSONObject> jsonObjects;
    private int mYear, mMonth, mDay;
    String teacherJoin =null;
    public TeacherAttendanceFragment newInstance(Context context) {
        TeacherAttendanceFragment fragment = new TeacherAttendanceFragment();
        this.context  = context;
        return fragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_teacher_attendance, container, false);
        recyclerView =root.findViewById(R.id.my_recycler_view);
        classSpinner = root.findViewById(R.id.class_spin);
        sectionSpinner = root.findViewById(R.id.section_spin);
        subjectSpinner = root.findViewById(R.id.subject_spin);
        postBtn = root.findViewById(R.id.submit_btn);

        progressBar = root.findViewById(R.id.progress);
        jsonObjects=new ArrayList<>();
        sessionManager = new SessionManager(getContext());
        user = new HashMap<String, String>();
        user= sessionManager.getUserDetails();
        attendanceModels= new ArrayList<>();
        dataList =new ArrayList<>();
        dataList1 =new ArrayList<>();
        dataList2 =new ArrayList<>();
        class_data = new ArrayList<>();
        class_data.add("Class");
        section_data = new ArrayList<>();
        section_data.add("Section");
        subject_data = new ArrayList<>();
        subject_data.add("Class");

        getRequestForSpinner("sections");
        getRequestForSpinner("classes");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, class_data);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                }



                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                teacherJoin= year +"-"+ (monthOfYear + 1)+"-"+dayOfMonth+"T13:26:38.163Z";
                                classSpinner.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }

        });

        arrayAdapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, section_data);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, subject_data);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(arrayAdapter2);
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (parent.getItemAtPosition(position).equals("Class")){

                }else {
                    subject = dataList1.get(position-1).id;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mAdapter = new TeacherAttendanceAdapter(getContext(),attendanceModels,asyncResult );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendClassWorkUpdatedData();

            }
        });

        return root;



    }

    AsyncTwoValue<AttendanceModel,String> asyncResult =new AsyncTwoValue<AttendanceModel,String> () {
        @Override
        public void success(AttendanceModel attendanceModel,String type) {
            JSONObject jsonObject =new JSONObject();
            try {
                jsonObject.put("id",attendanceModel.getId());
                jsonObject.put("userId",attendanceModel.getUserid());
                jsonObject.put("type",type);
                jsonObject.put("date",attendanceModel.date);
                jsonObjects.add(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void error(String error) {

        }
    };

    @Override
    public void onResume() {
        super.onResume();

        sectionSpinner.setAdapter(arrayAdapter1);
        sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (parent.getItemAtPosition(position).equals("Section")){

                }else {

                    section = dataList2.get(position-1).id;
                    getWorksResult(String.valueOf(section));

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void getRequestForSpinner(final String lastString) {
        RequestQueue queue = null;
        queue = Volley.newRequestQueue(getContext());

        final String URL = "http://13.233.167.170/api/"+lastString;
// Post params to be sent to the server



        JsonObjectRequest request_json = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);

                        Log.e("Response", response.toString());
                        String responsemessage = null;
                        try {

                            JSONArray array=response.getJSONArray("items");

                            String resposne_sucess = response.getString("isSuccess");
                            if(resposne_sucess.equals("true"))
                            {
                                for(int i=0;i<array.length();i++) {

                                    JSONObject object1=array.getJSONObject(i);
                                    int id =object1.getInt("id");
                                    String name =object1.getString("name");
                                    Data data =new Data(name,id);

                                  if(lastString.equals("classes")){
                                        subject_data.add(name);
                                        dataList1.add(data);
                                    }else if(lastString.equals("sections")){
                                        section_data.add(name);
                                        dataList2.add(data);
                                    }


                                }

                            }else{

                                Toast.makeText(getContext(),"Something went wrong!!",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(),"Something went wrong!!",Toast.LENGTH_SHORT).show();
                    }
                })
        {
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                token = user.get("token");
                params.put("x-access-token", token);
                return params;
            }


        };
        queue.add(request_json);
    }


    public void getWorksResult(String id) {
        if(TextUtils.isEmpty(teacherJoin)){
            Toast.makeText(getContext(),"Please select date first !!",Toast.LENGTH_SHORT).show();
            return;
        }
        RequestQueue queue = null;
        queue = Volley.newRequestQueue(getContext());
        progressBar.setVisibility(View.VISIBLE);
        System.currentTimeMillis();

        final String URL = "http://13.233.167.170/api/attendances?sectionId="+id+"&date="+teacherJoin;
// Post params to be sent to the server
        if(attendanceModels.size()>0){
            attendanceModels.clear();
        }
        JsonObjectRequest request_json = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        progressBar.setVisibility(View.GONE);
                        Log.e("Response", response.toString());
                        String responsemessage = null;
                        try {

                            JSONArray array=response.getJSONArray("items");

                            String resposne_sucess = response.getString("isSuccess");
                            if(resposne_sucess.equals("true"))
                            {
                                for(int i=0;i<array.length();i++) {

                                    JSONObject object1=array.getJSONObject(i);

                                    String firstName =object1.getString("firstName");
                                    String lastName =object1.getString("lastName");

                                    JSONObject attendance =object1.getJSONObject("attendance");



                                    int  id= object1.getInt("id");
                                    int userId = attendance.getInt("userId");
                                    String type = attendance.getString("type");
                                    String date = attendance.getString("date");

                                  //  String firstName, String lastName, String type, int id, int userid

                                AttendanceModel attendanceModel =new AttendanceModel(firstName,lastName,type,date,id,userId);


                                    attendanceModels.add(attendanceModel);
                                    mAdapter.notifyDataSetChanged();
                                    Toast.makeText(getContext(),"Something went wrong!!",Toast.LENGTH_SHORT).show();
                                }

                            }else{

                                Toast.makeText(getContext(),"Something went wrong!!",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(context,"Something went wrong!!",Toast.LENGTH_SHORT).show();
                    }
                })
        {
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                token = user.get("token");
                params.put("x-access-token", token);
                return params;
            }


        };
        queue.add(request_json);
    }


    public void sendClassWorkUpdatedData(){
        progressBar.setVisibility(View.VISIBLE);
        Map<String, String> params = new HashMap<String, String>();
        JSONArray jsonArray = new JSONArray();
        for(JSONObject jsonObject:jsonObjects){
            jsonArray.put(jsonObject);
        }



params.put("attendances",jsonArray.toString());
        JSONObject jsonObject =new JSONObject();
        try {
            jsonObject.put("attendances",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(params);
        JsonObjectRequest request_json = new JsonObjectRequest(Request.Method.POST,"http://13.233.167.170/api/attendances", jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        progressBar.setVisibility(View.GONE);
                        Log.e("Response", response.toString());
                        String responsemessage = null;
                        try {


                            String resposne_sucess = response.getString("isSuccess");
                            if(resposne_sucess.equals("true")) {

                              //  getWorksResult();
                                Toast.makeText(getContext(),"Attendance updated!!",Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }) {
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                token = user.get("token");
                params.put("x-access-token", token);
                return params;
            }


        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request_json);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


}
