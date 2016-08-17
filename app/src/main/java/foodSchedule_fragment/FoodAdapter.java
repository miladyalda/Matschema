package foodSchedule_fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.matschema.my.matschema.R;

import java.util.Collections;
import java.util.List;

import model.ModelActivity;

/**
 * Created by my on 2015-02-01.
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    protected LayoutInflater inflater;
    List<Information> data= Collections.emptyList();
    Context context;
    private Button button;

    public FoodAdapter(Context context,List<Information> data){

        this.data=data;
        this.context=context;

       inflater=LayoutInflater.from(context);


    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view=inflater.inflate(R.layout.custmrow, parent,false);

        MyViewHolder holder= new MyViewHolder(view);


        return holder;
    }

    public void remove(int position) {

        //måste ändra sen ej säkert och fel implement
        //int position = data.indexOf(item);
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void add(Information item, int position) {

        data.add(position, item);
        notifyItemInserted(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Information current=data.get(position);

        //holder.text.setText(current.number);
        //holder.setDate.setText(current.setDate);


        holder.addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final Dialog dialog = new Dialog(context);

                dialog.setContentView(R.layout.custom_dialog);
                dialog.setCancelable(true);
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });


                dialog.show();


            }
        });

        holder.addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent= new Intent(context, ModelActivity.class);
                context.startActivity(intent);


            }
        });


            }



    @Override
    public int getItemCount() {
        return data.size();

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageButton addFood;
        ImageButton addTime;

        public MyViewHolder(View itemView) {
            super(itemView);

            addFood=(ImageButton)itemView.findViewById(R.id.info_text);
            addTime=(ImageButton)itemView.findViewById(R.id.setDate);


        }
    }
}
