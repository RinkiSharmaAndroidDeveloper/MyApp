package tecaa.in.com.myapplication.tecsaa.ui.TeacherGallery;

import android.app.Application;

import tecaa.in.com.myapplication.tecsaa.Repository.GalleryRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class GallaryViewModel extends ViewModel {

    private GalleryRepository galleryRepository;
    public GallaryViewModel(@NonNull Application application) {
       // super(application);
        galleryRepository = new GalleryRepository();
    }
    public LiveData<List<GalleryDataClass>> getAllEmployee() {
        return galleryRepository.getMutableLiveData();
    }
}
