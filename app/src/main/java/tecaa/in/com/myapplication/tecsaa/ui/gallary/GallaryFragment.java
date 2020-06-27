package tecaa.in.com.myapplication.tecsaa.ui.gallary;

import android.annotation.SuppressLint;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;


import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import tecaa.in.com.myapplication.tecsaa.Adapters.GallaryTeacherAdapter;
import tecaa.in.com.myapplication.tecsaa.CreateAlbumActivity;
import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.SessionManager;
import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class GallaryFragment extends AppCompatActivity {

    private GallaryViewModel shareViewModel;
    GallaryTeacherAdapter mAdapter;
    Context context;
    RecyclerView recyclerView;

    ProgressBar progressBar;
    SessionManager sessionManager;
    HashMap<String, String> user;
    String token = null;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.fragment_gallary);
        sessionManager = new SessionManager(GallaryFragment.this);
        user = new HashMap<String, String>();
        user = sessionManager.getUserDetails();
        recyclerView =findViewById(R.id.my_recycler_view);
        progressBar=findViewById(R.id.progress);
        mAdapter = new GallaryTeacherAdapter(GallaryFragment.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2, GridLayout.VERTICAL, false));

        recyclerView.setAdapter(mAdapter);

    }

    public void getRequestForSpinner(String lastString) {
        RequestQueue queue = null;
        queue = Volley.newRequestQueue(this);

        final String URL = "http://13.233.167.170//api/albums";
// Post params to be sent to the server


        JsonObjectRequest request_json = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);

                        Log.e("Response", response.toString());
                        String responsemessage = null;
                        try {

                            JSONArray array = response.getJSONArray("items");

                            String resposne_sucess = response.getString("isSuccess");
                            if (resposne_sucess.equals("true")) {
                                for (int i = 0; i < array.length(); i++) {

                                   /* JSONObject object1 = array.getJSONObject(i);
                                    int id = object1.getInt("id");
                                    String name = object1.getString("name");
                                    Data data = new Data(name, id);*/

                                }

                            } else {

                                Toast.makeText(GallaryFragment.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(GallaryFragment.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
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
        queue.add(request_json);
    }

}