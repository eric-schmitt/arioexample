package com.example.arioproject
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.arioproject.API.Story
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("topstories.json")
    suspend fun getTopStories(): List<String>

    @GET("item/{id}.json")
    suspend fun getStory(@Path("id") id: String): Story
}

object APIHandler{

    val BASE_URL = "https://hacker-news.firebaseio.com/v0/"
    val apiService: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)

    var stories: ArrayList<Story> = ArrayList<Story>()
    lateinit var activity: MainActivity

    //Designed to expand to a delegate type pattern here
    fun notificationToActivity(activity: MainActivity) {
        this.activity = activity
    }


    suspend fun fetchTopStory(){
        if (stories.size > 0) return

        run {
           val storyIDs = apiService.getTopStories()
            storyIDs.forEachIndexed { i, id ->
                stories.add(i, apiService.getStory(id))

                //Occassionally update view
                if (i%20 ==0) {
                    activity.recieveUpdateNotificationFromAPI()
                }
            }
            //Update at end
            activity.recieveUpdateNotificationFromAPI()
        }

    }
}