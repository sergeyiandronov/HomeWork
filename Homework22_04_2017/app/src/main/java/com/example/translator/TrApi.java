package com.example.translator;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Сергей on 29.04.2017.
 */

public interface TrApi {
    @GET("/api/v1.5/tr.json/translate?key=trnsl.1.1.20170426T143701Z.5557cd4b1aca325d.724a862bb07c2a187c3ab41ceb1650408bf91a8b&lang=ru-en")
    Call<Translat> getIt(@Query("text") String text);

}
