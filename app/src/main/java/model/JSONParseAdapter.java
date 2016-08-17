package model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.matschema.my.matschema.R;

import java.util.ArrayList;

public class JSONParseAdapter extends RecyclerView.Adapter<JSONParseAdapter.MyViewHolder> {
    protected LayoutInflater inflater;
    private ArrayList<FoodModel> listFoodModel=new ArrayList<>();
    private ArrayList<FoodModel> foodCal=new ArrayList<>();

    FoodCalc foodCalc=new FoodCalc();

    public JSONParseAdapter(Context context){

       // this.data=data;

        inflater= LayoutInflater.from(context);


    }

    public void setlistFoodModel(ArrayList<FoodModel> listFoodModel){

        this.listFoodModel=listFoodModel;

        notifyItemRangeChanged(0, listFoodModel.size());


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view=inflater.inflate(R.layout.food_row_lay, parent,false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //change to visibile obj from listview
        FoodModel currentFood=listFoodModel.get(position);



        holder.foodName.setText(currentFood.getName());
        //måste cast from int to String

        holder.kjtext.setText("Kj: "+currentFood.getEnergyKj());
        holder.kcaltext.setText("Kcal: "+currentFood.getEnergyKcal());
        holder.protientext.setText("Protien: "+currentFood.getProtein());
        holder.fattext.setText("fat: "+currentFood.getFat());
        holder.carbtext.setText("Carbohydrate: "+currentFood.getCarbohydrates());
        holder.add.setText("+");

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Log.d("position",listFoodModel.get(position).toString());



                foodCalc.addName(listFoodModel.get(position).getName().toString());

            }
        });

    }

    @Override
    public int getItemCount() {
        //change to visibile obj from listview
        return listFoodModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView foodName;
        private TextView kjtext;
        private TextView kcaltext;
        private TextView protientext;
        private TextView fattext;
        private TextView carbtext;
        private TextView add;

        public MyViewHolder(View itemView) {
            super(itemView);

            foodName=(TextView)itemView.findViewById(R.id.foodName);
            kjtext=(TextView)itemView.findViewById(R.id.kjtext);
            kcaltext=(TextView)itemView.findViewById(R.id.kcaltext);
            protientext=(TextView)itemView.findViewById(R.id.protientext);
            fattext=(TextView)itemView.findViewById(R.id.fattext);
            carbtext=(TextView)itemView.findViewById(R.id.carbtext);
            add=(TextView)itemView.findViewById(R.id.add);



        }
    }
}
