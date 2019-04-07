# CancelledTrainDetails

Cancelled Train Details is an Android Application which is used to get Cancelled Train Details based on the Current Date.
To implement this we need to follow some of the steps

STEP 1:
To get Cancelled Train Details we must need a API.The API I used in this application is RAILWAYAPI,to know more details about it
visit  https://railwayapi.com/ 
after registering you will be given a API Key which is used to make our api requests.

STEP 2:
There are providing Various API Functionalities we need to choose required API for our Application and the api that used in this application is

https://api.railwayapi.com/v2/cancelled/date/<dd-mm-yyyy>/apikey/<apikey>/
        
STEP 3:
In your Android Application Project Gradle file add
dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.android.support:appcompat-v7:26.+'
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
 
    //these three lines are added 
     implementation 'com.google.code.gson:gson:2.8.5'
     implementation 'com.squareup.retrofit2:retrofit:2.5.0'
     implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
     testCompile 'junit:junit:4.12'
}
        
STEP 4:
Covert the JSON format into java Classes(Project Name->Right Click->Create Classes From JSON).The New Java Class looks like

public class CancelledTrainsPOJO {
 
    @SerializedName("train")
    private String train;
    @SerializedName("respone_code")
    private int response;
    @SerializedName("debit")
    private int debit;
    @SerializedName("route")
    private List<RouteItem> route;
}


STEP 5:
Create a New Interface file and make a GET request with Base Url of the Http Request.

public interface ApiInterface {

    @GET("v2/cancelled/date/<dd-mm-yyyy>/apikey/<apikey>/")
    //This Base Url is only for Getting Train Details like Working Days
    Call<CancelledTrainsPOJO> getDetails();
}
<apikey>-->Replace it with your API Key.
TrainDetailsPOJO is the main class name that we named while converting JSON Data into Java or Kotlin Classes.
        
STEP 6:
In your Main Java Class include this Code

private void getInformation() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.railwayapi.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(ApiInterface.class);
        Call<CancelledTrainsPOJO> call = api.getDetails();
        call.enqueue(new Callback<CancelledTrainsPOJO>() {
            @Override
            public void onResponse(Call<CancelledTrainsPOJO> call, Response<CancelledTrainsPOJO> response) {
                CancelledTrainsPOJO troute= response.body();
                 //Write the Required Code here.
                 }
            @Override
            public void onFailure(Call<CancelledTrainsPOJO> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();            }
        })
        }
        
STEP 7:    
 Give Permissions for accessing Internet in AndroidManifest.xml file
  <!-- the internet permission --> 
    <uses-permission android:name="android.permission.INTERNET" />
