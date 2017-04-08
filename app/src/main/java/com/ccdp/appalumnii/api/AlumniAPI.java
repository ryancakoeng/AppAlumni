package com.ccdp.appalumnii.api;

import com.ccdp.appalumnii.model.Alumni;
import com.ccdp.appalumnii.model.AlumniResults;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by lenovo on 23/03/2017.
 */
public interface AlumniAPI {
    @GET("alumni/all")
    Call<AlumniResults> all();

    @GET("alumni/find")
    Call<AlumniResults> find(@Query("id")int id);

    @FormUrlEncoded
    @POST("alumni/insert")
    Call<Alumni> insert(@Field("nama")String nama,
                        @Field("nrp") String nrp,
                        @Field("id_jurusan") String idJurusan,
                        @Field("tgl_lulus") String tglLulus,
                        @Field("tempat_kerja") String tempatKerja,
                        @Field("telpon") String telpon,
                        @Field("email") String email);

    @FormUrlEncoded
    @POST("alumni/update")
    Call<Alumni> update(@Field("nama")String nama,
                        @Field("nrp") String nrp,
                        @Field("id_jurusan") String idJurusan,
                        @Field("tgl_lulus") String tglLulus,
                        @Field("tempat_kerja") String tempatKerja,
                        @Field("telpon") String telpon,
                        @Field("email") String email,
                        @Field("id")int id);

    @FormUrlEncoded
    @POST("alumni/delete")
    Call<Alumni> delete(@Field("id")int id);

}
