package model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.matschema.my.matschema.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static extras.UrlEndpoints.LIKAMED;
import static extras.UrlEndpoints.MAT_API;
import static extras.UrlEndpoints.QUERY;
import static extras.UrlEndpoints.QUEST_MARK;
import static extras.UrlEndpoints.SLASH;

/**
 * Created by my on 2015-02-14.
 */
public class ModelActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private ArrayList<FoodModel> listFoodModel=new ArrayList<>();
    private RecyclerView listFood;
    private JSONParseAdapter adapterJson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.model_activity);


        toolbar=(Toolbar)findViewById(R.id.app_bar);


        //viktig för tolbar och retur knappen där uppe
        if(toolbar != null) {
            setSupportActionBar(toolbar);

            getSupportActionBar().setTitle("");
        }

        listFood=(RecyclerView)findViewById(R.id.listFood);
        listFood.setLayoutManager(new LinearLayoutManager(this));
        adapterJson=new JSONParseAdapter(this);
        listFood.setAdapter(adapterJson);
       // sendJsonArrayRequest("mjölk");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {


        sendJsonArrayRequest(s);
        listFoodModel.clear();
        adapterJson.notifyDataSetChanged();

        Log.e("search", s);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {


      //  if(listFoodModel.size()!=0){

        //    listFoodModel.clear();
       // }

      // sendJsonArrayRequest(s);

       // Log.e("search", s);


        return false;
    }

    public void sendJsonArrayRequest(String searchWord){

        RequestQueue mRequestQueue= VollySingleton.getsInstance().getmRequestQueue();

        JsonArrayRequest ArrRequest=new JsonArrayRequest(MAT_API+QUEST_MARK+QUERY+LIKAMED+searchWord,
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {


                                    parseJSONArrayResponse(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.getMessage());

            }
        });

        mRequestQueue.add(ArrRequest);

    }
    public void sendJsonRequest(int id){
        RequestQueue mRequestQueue= VollySingleton.getsInstance().getmRequestQueue();

        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET,
                MAT_API+SLASH+id, (String)null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        parseJSONResponse(response);


                        adapterJson.setlistFoodModel(listFoodModel);


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("object response", "Error: " + error.getMessage());
                // hide the progress dialog

            }
        });


        mRequestQueue.add(objRequest);


    }

    private void parseJSONArrayResponse(JSONArray response){

        if(response==null){


            return;
        }

        try{

            // JSONObject jsonObject;
            //response.length()
            for(int i = 0; i <response.length(); i++)
            {
                JSONObject jsonObject = response.getJSONObject(i);

                int id=jsonObject.getInt("number");

           //send request to rest api to get the nutries valu
                sendJsonRequest(id);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private void parseJSONResponse(JSONObject response) {


       // ArrayList<FoodModel> listFoodModel = new ArrayList<>();

        if (response != null && response.length() > 0) {

            try {

                //get the jsonObj nutrientValues from jsonobj
                JSONObject jsonObject = response.getJSONObject("nutrientValues");


                //get the name and the number from jsonobj
                String name = response.getString("name");
                int id = response.getInt("number");

                //get nutries valu from nutrien resf-api-- per 100 gram
                double energyKj = jsonObject.getDouble("energyKj");
                double energyKcal = jsonObject.getDouble("energyKcal");
                double protein = jsonObject.getDouble("protein");
                double fat = jsonObject.getDouble("fat");
                double carbohydrates = jsonObject.getDouble("carbohydrates");


                FoodModel foodModel = new FoodModel();


                foodModel.setName(name);
                foodModel.setId(id);

                foodModel.setEnergyKj(energyKj);
                foodModel.setEnergyKcal(energyKcal);
                foodModel.setProtein(protein);
                foodModel.setFat(fat);
                foodModel.setCarbohydrates(carbohydrates);


                listFoodModel.add(foodModel);




            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        adapterJson.setlistFoodModel(listFoodModel);



    }



}

