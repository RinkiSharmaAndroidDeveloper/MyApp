package tecaa.in.com.myapplication.tecsaa;

public interface AsyncTwoValue<TData,TData1> {
    void success(TData data, TData1 data1);
    void error(String error);


}