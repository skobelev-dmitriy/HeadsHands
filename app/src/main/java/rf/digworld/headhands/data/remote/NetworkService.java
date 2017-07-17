package rf.digworld.headhands.data.remote;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rf.digworld.headhands.data.model.WeatherResponce;
import rf.digworld.headhands.util.ConnectivityInterceptor;
import rx.Observable;

public interface NetworkService {


    String ENDPOINT = "http://api.openweathermap.org/data/2.5/";
    String WEATHER = "weather";
    String PATH="?APPID=2ba163ce87da7a7a4c31494a66103631&lang=ru&units=metric";
    @GET(WEATHER+PATH)
    Observable<WeatherResponce> getWeather(@Query("id") long cityId);


    /******** Helper class that sets up a new services *******/
    class Creator {

        public static NetworkService newNetworkService(Context context) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new ConnectivityInterceptor(context))
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(NetworkService.ENDPOINT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(NetworkService.class);
        }
    }
}
