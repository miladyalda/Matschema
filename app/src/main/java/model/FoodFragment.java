package model;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.matschema.my.matschema.MyApp;
import com.matschema.my.matschema.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import extras.UrlEndpoints;

import static extras.UrlEndpoints.*;

public class FoodFragment extends Fragment {


    private RecyclerView food_list;
    private View v;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.recykle_view, container, false);



//        food_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        //sendJsonRequest("mjölk");
        return v;
    }

    public void sendJsonRequest(String searchWord){

        RequestQueue mRequestQueue= VollySingleton.getsInstance().getmRequestQueue();

        JsonArrayRequest request=new JsonArrayRequest(MAT_API+QUEST_MARK+QUERY+LIKAMED+searchWord,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        parseJSONResponse(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.getMessage());

            }
        });

        mRequestQueue.add(request);

    }

    public static String getRequestUrl(String searchWord){


        return MAT_API+QUEST_MARK+QUERY+LIKAMED+searchWord;



    }

    public static String getRequestUrlId(String  id){


        return MAT_API+SLASH+id;



    }

   private void parseJSONResponse(JSONArray response){

        if(response==null){


           return;
       }

       try{

           StringBuilder data=new StringBuilder();

          // JSONObject jsonObject;
           for(int i = 0; i < response.length(); i++)
           {
               JSONObject jsonObject = response.getJSONObject(i);
               String id=jsonObject.getString("number");

                data.append(id+"\n");
           }
           Log.d("id", data.toString());

       } catch (JSONException e) {
           e.printStackTrace();
       }


   }

}