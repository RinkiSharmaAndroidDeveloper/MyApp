package tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher;



import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import tecaa.in.com.myapplication.tecsaa.Adapters.ClassWrokTeacherAdapter;

import tecaa.in.com.myapplication.tecsaa.AsyncResult;

import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.SessionManager;
import tecaa.in.com.myapplication.tecsaa.Util.VolleyMultipartRequest;

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
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

import static tecaa.in.com.myapplication.tecsaa.Util.MasterFile.getFileDataFromDrawable;
import static tecaa.in.com.myapplication.tecsaa.Util.MasterFile.runtimePermissionsRequest;

public class RequirtementFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    ClassWrokTeacherAdapter mAdapter;
    RecyclerView recyclerView;
    ImageView imageView,uploadedImage;
    Spinner classSpinner,sectionSpinner,subjectSpinner;
    EditText editText,title;
    Button postBtn;
    Context context;
    TextView frontTitle;
    ProgressBar progressBar;
    String titleStr,descripText;
    int section,subject,classStr;
    List<String> class_data,section_data,subject_data;
    SessionManager sessionManager;
    HashMap<String, String> user;
    List<Data> dataList,dataList1,dataList2;
    List<HomeWorkModel> homeWorkModelList;
    String token=null;
    Bitmap bitmap;
    public RequirtementFragment newInstance(Context context) {
        RequirtementFragment fragment = new RequirtementFragment();
        this.context  = context;
        return fragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.activity_teacher_home_work, container, false);
        recyclerView =root.findViewById(R.id.my_recycler_view);
        classSpinner = root.findViewById(R.id.class_spin);
        sectionSpinner = root.findViewById(R.id.section_spin);
        subjectSpinner = root.findViewById(R.id.subject_spin);
        editText = root.findViewById(R.id.post);
        title = root.findViewById(R.id.title);
        postBtn = root.findViewById(R.id.button_post);
        imageView = root.findViewById(R.id.image);
        uploadedImage = root.findViewById(R.id.post_image);
        progressBar = root.findViewById(R.id.progress);
        frontTitle = root.findViewById(R.id.front_name);

        sessionManager = new SessionManager(getContext());
        user = new HashMap<String, String>();
        user= sessionManager.getUserDetails();
        homeWorkModelList= new ArrayList<>();
        dataList =new ArrayList<>();
        dataList1 =new ArrayList<>();
        dataList2 =new ArrayList<>();
        class_data = new ArrayList<>();
        class_data.add("Class");
        section_data = new ArrayList<>();
        section_data.add("Section");
        subject_data = new ArrayList<>();
        subject_data.add("Subject");
        frontTitle.setText("Requirements");
        getRequestForSpinner("classes");
        getRequestForSpinner("sections");
        getRequestForSpinner("subjects");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, class_data);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(arrayAdapter);
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (parent.getItemAtPosition(position).equals("Class")){

                }else {
                    classStr = dataList.get(position-1).id;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!runtimePermissionsRequest(context)) {
                    Intent intent = new Intent(getContext(), ImageSelectActivity.class);
                    intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                    startActivityForResult(intent, 1213);

                }
            }
        });

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, section_data);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectionSpinner.setAdapter(arrayAdapter1);
        sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (parent.getItemAtPosition(position).equals("Section")){

                }else {

                    section = dataList2.get(position-1).id;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, subject_data);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(arrayAdapter2);
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (parent.getItemAtPosition(position).equals("Subject")){

                }else {
                    subject = dataList1.get(position-1).id;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mAdapter = new ClassWrokTeacherAdapter(homeWorkModelList,getContext(),dataList,dataList1,dataList2,class_data,section_data,subject_data, asyncResult, delete );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   titleStr,descripText,section,subject,classStr;
                titleStr =title.getText().toString();
                descripText =editText.getText().toString();


                if(TextUtils.isEmpty(titleStr)){
                    title.setError("Please fill the title.");
                    return;
                }
                if(TextUtils.isEmpty(descripText)){
                    editText.setError("Please give the description.");
                    return;
                }

                if(section==0){
                    Toast.makeText(getContext(),"Please select section",Toast.LENGTH_LONG);
                    return;
                }

                if(subject==0){

                    Toast.makeText(getContext(),"Please select the subject.",Toast.LENGTH_LONG);
                    return;
                }
                if(classStr==0){
                    Toast.makeText(getContext(),"Please select class",Toast.LENGTH_LONG);
                    return;
                }

                sendClassWorkData();

            }
        });
        getWorksResult();
        return root;



    }
    public void uploadFileOnServer(final String id, final Bitmap bitmap){
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(
                Request.Method.POST,
                "http://13.233.167.170/api/works/files/"+id,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        progressBar.setVisibility(View.GONE);
                        //Handle response
                        uploadedImage.setVisibility(View.GONE);
                        getWorksResult();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Handle error
                    }
                }
        ) {
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                token = user.get("token");
                params.put("x-access-token", token);
                return params;
            }


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                byte[] data = getFileDataFromDrawable(bitmap);
                params.put("media", new DataPart("image"+id+".jpg", data));
                return params;
            }
        };

        //I used this because it was sending the file twice to the server
        volleyMultipartRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        0,
                        -1,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(volleyMultipartRequest);
    }

    AsyncResult<UpdatedData> asyncResult =new AsyncResult<UpdatedData>() {
        @Override
        public void success(UpdatedData updatedData) {
            sendClassWorkUpdatedData(updatedData);
        }

        @Override
        public void error(String error) {

        }
    };

    AsyncResult<String> delete =new AsyncResult<String>() {
        @Override
        public void success(String id) {
            sendClassWorkDeleteData(id);
        }

        @Override
        public void error(String error) {

        }
    };
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
                                        class_data.add(name);
                                        dataList.add(data);
                                    }else if(lastString.equals("subjects")){
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1213){
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            if(bitmap != null) {
                this.bitmap=bitmap;
                uploadedImage.setVisibility(View.VISIBLE);
                uploadedImage.setImageBitmap(bitmap);

            }else{
                Toast.makeText(getContext(),"Picture not taken!",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void getWorksResult() {
        RequestQueue queue = null;
        queue = Volley.newRequestQueue(getContext());
        progressBar.setVisibility(View.VISIBLE);
        final String URL = "http://13.233.167.170/api/requirements";
        // Post params to be sent to the server
        if(homeWorkModelList.size()>0){
            homeWorkModelList.clear();
        }
        JsonObjectRequest request_json = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);

                        Log.e("Response", response.toString());
                        String responsemessage = null;
                        try {
                            progressBar.setVisibility(View.GONE);
                            JSONArray array=response.getJSONArray("items");

                            String resposne_sucess = response.getString("isSuccess");
                            if(resposne_sucess.equals("true"))
                            {
                                for(int i=0;i<array.length();i++) {
                                    String fileID = null;
                                    String filePath=null;
                                    JSONObject object1=array.getJSONObject(i);
                                    int id =object1.getInt("id");
                                    String title =object1.getString("title");
                                    String desc =object1.getString("desc");
                                    String type ="requirment";
                                    int schoolId =object1.getInt("schoolId");
                                    int classId =object1.getInt("classId");
                                    int sectionId =object1.getInt("sectionId");
                                    int subjectId =object1.getInt("subjectId");
                                    int userId =object1.getInt("userId");
                                    JSONObject classIdName =object1.getJSONObject("class");
                                    JSONObject userNameObj =object1.getJSONObject("user");
                                    JSONObject subjectIdName =object1.getJSONObject("subject");
                                    JSONObject sectionIdName =object1.getJSONObject("section");
                                    int classIdServer = classIdName.getInt("id");
                                    String className = classIdName.getString("name");

                                    int subjectIdServer =subjectIdName.getInt("id");
                                    String subjectclassName =subjectIdName.getString("name");

                                    int sectionIdServer =sectionIdName.getInt("id");
                                    String sectionName =sectionIdName.getString("name");
                                    String userName =userNameObj.getString("firstName");
                                    String lastName =userNameObj.getString("lastName");
                                    int userWorkId =userNameObj.getInt("id");
                                    JSONArray fileArray =object1.getJSONArray("files");

                                    if(fileArray.length()>0){
                                        JSONObject fileObject=fileArray.getJSONObject(0);
                                        fileID= String.valueOf(fileObject.getInt("id"));
                                        filePath= fileObject.getString("url");
                                    }
                                    HomeWorkModel.Data classObj =new HomeWorkModel.Data(className,classIdServer);
                                    HomeWorkModel.Data subObj =new HomeWorkModel.Data(subjectclassName,subjectIdServer);
                                    HomeWorkModel.Data sectObj =new HomeWorkModel.Data(sectionName,sectionIdServer);

                                    HomeWorkModel homeWorkModel= new HomeWorkModel(title,desc,type,userName,lastName,fileID,filePath,id,schoolId,classId,sectionId,subjectId,userWorkId,classObj,subObj,sectObj);
                                    homeWorkModelList.add(homeWorkModel);
                                    mAdapter.notifyDataSetChanged();
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

    public void sendClassWorkData(){
        progressBar.setVisibility(View.VISIBLE);
        Map<String, String> params = new HashMap<String, String>();
        params.put("title", titleStr);
        params.put("desc", descripText);
        params.put("schoolId", user.get("schoolId"));
        params.put("classId", String.valueOf(classStr));
        params.put("sectionId", String.valueOf(section));
        params.put("subjectId", String.valueOf(subject));



        JsonObjectRequest request_json = new JsonObjectRequest("http://13.233.167.170/api/requirements", new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);

                        Log.e("Response", response.toString());
                        String responsemessage = null;
                        try {


                            String resposne_sucess = response.getString("isSuccess");
                            JSONObject jsonObject = response.getJSONObject("data");
                            String id = jsonObject.getString("id");
                            if(resposne_sucess.equals("true")) {
                                title.setText("");
                                editText.setText("");
                                classStr =0;
                                section =0;
                                subject=0;
                                if(bitmap!=null){
                                    uploadFileOnServer(id,bitmap);
                                }else{
                                    getWorksResult();
                                }

                                Toast.makeText(getContext(),"Requirement added!!",Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error);
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




    public void sendClassWorkUpdatedData(UpdatedData updatedData){
        progressBar.setVisibility(View.VISIBLE);
        Map<String, String> params = new HashMap<String, String>();
        params.put("title", updatedData.getTitle());
        params.put("desc", updatedData.getDesc());
        params.put("schoolId", user.get("schoolId"));
        params.put("classId", String.valueOf(updatedData.getClassId()));
        params.put("sectionId", String.valueOf(updatedData.getSectionId()));
        params.put("subjectId", String.valueOf(updatedData.getSubjectId()));



        JsonObjectRequest request_json = new JsonObjectRequest(Request.Method.PUT,"http://13.233.167.170/api/requirements/"+updatedData.id, new JSONObject(params),
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

                                getWorksResult();
                                Toast.makeText(getContext(),"Requirement updated!!",Toast.LENGTH_SHORT).show();

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



    public void sendClassWorkDeleteData(String id){
        progressBar.setVisibility(View.VISIBLE);



        JsonObjectRequest request_json = new JsonObjectRequest(Request.Method.DELETE,"http://13.233.167.170/api/requirements/"+id, null,
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


                                Toast.makeText(getContext(),"Requirement item deleted!!",Toast.LENGTH_SHORT).show();
                                getWorksResult();
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
        // camera.deleteImage();
    }

}