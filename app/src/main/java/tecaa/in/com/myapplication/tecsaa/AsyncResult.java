package tecaa.in.com.myapplication.tecsaa;

public interface AsyncResult<TData> {
    void success(TData data);
    void error(String error);



}