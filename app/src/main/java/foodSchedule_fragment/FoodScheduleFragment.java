package foodSchedule_fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.matschema.my.matschema.R;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.FoodFragment;
import model.ModelActivity;

import static com.matschema.my.matschema.R.*;

    public class FoodScheduleFragment extends Fragment {

    private RecyclerView recyview;
        private FoodAdapter adapter;
        private ImageButton imageButton;
        View v;
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();


        //final FoodScheduleFragment context = this;

        List<Information> data= Collections.emptyList();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        //mycket viktig om du vill all din app seka funka på olika versioner
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT
                || Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN
                || Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR2
                ||Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) {
            v = inflater.inflate(layout.floation_button, container, false);
        }

        else{
            v = inflater.inflate(layout.floation_button_v21, container, false);
        }

        //View v = inflater.inflate(layout.food_sch_fragment, container, false);

        recyview= (RecyclerView) v.findViewById(id.recycler_view);
        recyview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyview.setItemAnimator(new DefaultItemAnimator());



        SharedPreferences prefs =getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);

        //teknik för att veta om programmet körs för första gången.
        boolean firstTime = prefs.getBoolean("firstTime", true);
         if (firstTime) {

            String value = gson.toJson(getData());
            SharedPreferences.Editor e = prefs.edit();
            //viktigt
            e.putBoolean("firstTime", false);
            e.putString("list", value);
            e.commit();



        }

            String value = prefs.getString("list", null);

         Information[] list = gson.fromJson(value, Information[].class);
             data=new LinkedList<Information>(Arrays.asList(list));


                adapter = new FoodAdapter(getActivity(), data);

       // adapter = new FoodAdapter(getActivity(),getData());

        recyview.setAdapter(adapter);

        imageButton=(ImageButton)v.findViewById(id.imageButton);

        //viktig del ska kolla på den sen

        recyview.addOnItemTouchListener(new recyclerTouchListner(getActivity(),recyview,new ClickListner() {
            @Override
            public void onClick(View view, int position) {

                if(position==0) {

                    //för att man kör fragmentactivity
                  //  FragmentManager fm = getActivity().getSupportFragmentManager();
                 // fm.beginTransaction().replace(R.id.container, new FoodFragment()).addToBackStack(null).commit();

                   // Intent intent = new Intent(getActivity(), ModelActivity.class);
                   // startActivity(intent);


                }


                Toast.makeText(getActivity(), "onClick" + position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, final int position) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());

                // set title
                alertDialogBuilder.setTitle("Your Title");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Click yes to exit!")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity
                                adapter.remove(position);





                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();



            Toast.makeText(

            getActivity(),

            "onLongClick"+position,Toast.LENGTH_SHORT).

            show();

        }
        }));



        //add buttonsssssssssss
        imageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Information temp=new Information();
                temp.number="+";
                temp.setDate="Set Date";
                adapter.add(temp,0);

                Toast.makeText(getActivity(), "selected button", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }



        public static List<Information> getData(){

             List<Information> data=new ArrayList();
            String [] nummber={"+",
                    "+"
                    ,"+"
                    ,"+"};

            for (int i=0; i<nummber.length; i++){

                Information current=new Information();
                current.number=nummber[i];
                data.add(current);




            }

            return data;

        }


        class recyclerTouchListner implements RecyclerView.OnItemTouchListener{


            private GestureDetector gestureDetector;
            private ClickListner clickListner;

            public recyclerTouchListner(Context context, final RecyclerView recyclerView,final ClickListner clickListner){

                this.clickListner=clickListner;

                gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){


                    @Override
                     public boolean onSingleTapUp(MotionEvent e){


                        return true;


                    }

                    @Override
                    public void onLongPress(MotionEvent e){

                        View child= recyview.findChildViewUnder(e.getX(),e.getY());

                        if(child!=null && clickListner!=null){

                            clickListner.onLongClick(child, recyview.getChildPosition(child));


                        }

                        super.onLongPress(e);


                    }



                } );




            }


            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {


                View child= rv.findChildViewUnder(e.getX(),e.getY());

                if(child!=null && clickListner!=null && gestureDetector.onTouchEvent(e)){

                    clickListner.onClick(child,rv.getChildPosition(child));


                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        }

        public static interface ClickListner{

            public void onClick(View view,int position);
            public void onLongClick(View view,int position);

        }

        @Override
        public void onPause() {


            //viktig för debuging
            Log.e("MyTag", "onPausefragment called");
            super.onPause();

            String value = gson.toJson(data);
            SharedPreferences prefs =getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
            SharedPreferences.Editor e = prefs.edit();
            e.putString("list", value);
            e.commit();
        }
    }
