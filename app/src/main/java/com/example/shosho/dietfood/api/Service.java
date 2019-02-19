package com.example.shosho.dietfood.api;

import com.example.shosho.dietfood.model.AddToCardResponse;
import com.example.shosho.dietfood.model.CancelSubscribtionResponse;
import com.example.shosho.dietfood.model.CardResponse;
import com.example.shosho.dietfood.model.ChangePasswordResponse;
import com.example.shosho.dietfood.model.CheckOut.CheckOutResponse;
import com.example.shosho.dietfood.model.DeleteCardResponse;
import com.example.shosho.dietfood.model.MinCardResponse;
import com.example.shosho.dietfood.model.MyOrdersResponse;
import com.example.shosho.dietfood.model.MySubscribtionResponse;
import com.example.shosho.dietfood.model.OrderDetailsResponse;
import com.example.shosho.dietfood.model.PackageDetailsResponse;
import com.example.shosho.dietfood.model.PaidConsultationResponse;
import com.example.shosho.dietfood.model.PostOrderResponse;
import com.example.shosho.dietfood.model.SubscribtionResponse;
import com.example.shosho.dietfood.model.home.HomeBannerResponse;
import com.example.shosho.dietfood.model.home.HomeProductResponse;
import com.example.shosho.dietfood.model.LoginResponse;
import com.example.shosho.dietfood.model.MealComponentResponse;
import com.example.shosho.dietfood.model.ProducerFamilyResponse;
import com.example.shosho.dietfood.model.ProfileResponse;
import com.example.shosho.dietfood.model.register.RegisterResponse;
import com.example.shosho.dietfood.model.ResetPasswordResponse;
import com.example.shosho.dietfood.model.UpdateProfileResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {
    @POST("register")
    Call<RegisterResponse> getRegisterData(@Body Map<String,String> map);

    @POST("login")
    Call<LoginResponse> getLoginData(@Body Map<String,String> map);

    @POST("getProfile")
    Call<ProfileResponse> getProfileData(@Body Map<String,String> map);
    @POST("updateProfile")
    Call<UpdateProfileResponse> getUpdatedProfileData(@Body Map<String,String> map);

    @GET("homeMeal")
    Call<HomeProductResponse> getHomeProductData();

    @POST("changePassword")
    Call<ChangePasswordResponse> getChangePasswordData(@Body Map<String,String> map);

    @GET("banners")
    Call<HomeBannerResponse>getHomeBannerData();

    @POST("restPassword")
    Call<ResetPasswordResponse> getResetPasswordData(@Body Map<String,String> map);

    @GET("mealFood")
    Call<ProducerFamilyResponse> getProducerFamilyData();

    @POST("detailsMealFood")
    Call<MealComponentResponse> getMealComponentData(@Body Map<String,String> map);

    @POST("detailsPackages")
    Call<PackageDetailsResponse> getPackageDetailsData(@Body Map<String,String> map);

    @POST("postCart")
    Call<AddToCardResponse> getAddToCardData(@Body Map<String,String> map);

    @POST("getCart")
    Call<CardResponse> getCardData(@Body Map<String,String> map);

    @POST("minCart")
    Call<MinCardResponse> getMinCardData(@Body Map<String,String> map);

    @POST("deleteCart")
    Call<DeleteCardResponse> getDeleteCardData(@Body Map<String,String> map);

    @POST("postOrder")
    Call<PostOrderResponse> getPostOrderData(@Body Map<String,String> map);

    @POST("consultings")
    Call<PaidConsultationResponse> getPaidConsultationData(@Body Map<String,String> map);

    @POST("getOrder")
    Call<MyOrdersResponse> getMyOrdersData(@Body Map<String,String> map);

    @POST("getOrderDetails")
    Call<OrderDetailsResponse> getMyOrderDetailsData(@Body Map<String,String> map);

    @POST("subscribe")
    Call<SubscribtionResponse> getSubscribtionData(@Body Map<String,String> map);

    @POST("getSubscribe")
    Call<MySubscribtionResponse> getMySubscribtionData(@Body Map<String,String> map);


    @POST("cancelSubscribe")
    Call<CancelSubscribtionResponse> getCancelSubscribtionData(@Body Map<String,String> map);

    @POST("checkout")
    Call<CheckOutResponse> getCheckOutData(@Body Map<String,String> map);
}
