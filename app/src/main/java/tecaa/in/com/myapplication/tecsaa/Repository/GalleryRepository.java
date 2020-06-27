package tecaa.in.com.myapplication.tecsaa.Repository;

import tecaa.in.com.myapplication.tecsaa.RetrofitClient.APIInterface;
import tecaa.in.com.myapplication.tecsaa.RetrofitClient.RetrofitClient;
import tecaa.in.com.myapplication.tecsaa.ui.TeacherGallery.GalleryDataClass;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryRepository {
    private static final String TAG = "EmployeeRepository";
    private List<GalleryDataClass> employees = new ArrayList<>();
    private MutableLiveData<List<GalleryDataClass>> mutableLiveData = new MutableLiveData<>();
    public GalleryRepository() {
    }
    public MutableLiveData<List<GalleryDataClass>> getMutableLiveData() {
        final APIInterface userDataService = RetrofitClient.getService();
        Call<List<GalleryDataClass>> call = userDataService.getGalleryData();
        call.enqueue(new Callback<List<GalleryDataClass>>() {
            @Override
            public void onResponse(Call<List<GalleryDataClass>> call, Response<List<GalleryDataClass>> response) {

                if (response != null && response.body() != null) {
                    employees = response.body();
                    mutableLiveData.setValue(employees);
                }
            }
            @Override
            public void onFailure(Call<List<GalleryDataClass>> call, Throwable t) {
            }
        });
        return mutableLiveData;
    }
}
