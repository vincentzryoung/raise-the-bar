package com.raisethebar.raisethebar;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.raisethebar.raisethebar.R;
import com.raisethebar.raisethebar.orderActivity;
import com.raisethebar.raisethebar.rumList;

import java.util.ArrayList;
import java.util.List;


public class RumActivity extends ActionBarActivity {

    public int count[] = {0,0,0,0,0};

    private List<rumList> myRum = new ArrayList<rumList>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drinkpage);

        Button someBtn = (Button) findViewById(R.id.btn);
        someBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(RumActivity.this, orderActivity.class);
                intent.putExtra("count_list_rum", count);
                startActivity(intent);

                //startActivityForResult(intent,0);


            }
        });

        populateRumList();
        populateListView();
        //registerClickCallBack();
    }


    private void populateRumList() {
        myRum.add(new rumList("Shot", "Rum", "$5.00", 0));
        myRum.add(new rumList("Rum and Coke", "Rum, Coke", "$7.50", 0));
        myRum.add(new rumList("Daiquiri", "White Rum, Lime Juice, Sugar Syrup", "$11.00", 0));
        myRum.add(new rumList("Dark & Stormy", "Black Seal Rum, Ginger Beer, Lime Juice", "$13.00",0));
        myRum.add(new rumList("Rum Punch", "Rum, Sugar Syrup, Lime Juice", "$11.00", 0));
    }

    static class UserHolder{
        Button addQ;
        Button deleteQ;
    }


    private void populateListView(){
        ArrayAdapter<rumList> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.rumdrinks);
        list.setAdapter(adapter);
        /*list.setItemsCanFocus(false);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/
    }

   /* private void registerClickCallBack() {
        ListView list = (ListView) findViewById(R.id.rumdrinks);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rumList clickedPos = myRum.get(position);

            }
        });
    }*/


    private class MyListAdapter extends ArrayAdapter<rumList>{


        public MyListAdapter(){
            super(RumActivity.this,R.layout.item_view,myRum);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){

            UserHolder holder = null;

            //make sure we have a view to work with
            View itemView = convertView;
            if(itemView==null){
                itemView = getLayoutInflater().inflate(R.layout.item_view,parent,false);
                holder = new UserHolder();
                holder.addQ=(Button)itemView.findViewById(R.id.addBtn);
                holder.deleteQ=(Button)itemView.findViewById(R.id.deleteBtn);
                itemView.setTag(holder);
            }
            else{
                holder = (UserHolder)itemView.getTag();
            }

            if (position % 2 == 1) {
                itemView.setBackgroundColor(0xFF3498DB);
            } else {
                itemView.setBackgroundColor(0xFF2980B9);
            }


            //find rum to work with
            final rumList currentRum = myRum.get(position);

            //fill the view
            TextView nameText = (TextView) itemView.findViewById(R.id.item_name);
            nameText.setText(currentRum.getRumName());


            TextView priceText = (TextView) itemView.findViewById(R.id.item_price);

            priceText.setText(""+currentRum.getPrice());


            TextView ingredientText = (TextView) itemView.findViewById(R.id.item_ingredient);
            ingredientText.setText(currentRum.getIngredients());

            final TextView quantityText = (TextView) itemView.findViewById(R.id.item_quantity);
            String someOutput = Integer.toString(currentRum.getQuantity());
            quantityText.setText(someOutput);
            //final TextView quantityText = (TextView) itemView.findViewById(R.id.item_quantity);
            //quantityText.setText(""+currentRum.getQuantity());

            holder.addQ.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    count[position]++;
                    String someOutput = Integer.toString(count[position]);
                    quantityText.setText(someOutput);

                }
            });

            holder.deleteQ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(count[position]>0) {
                        count[position]--;
                        String someOutput = Integer.toString(count[position]);
                        quantityText.setText(someOutput);

                    }
                }
            });



            //TextView quantityText = (TextView) itemView.findViewById(R.id.item_quantity);
            //quantityText.setText(currentRum.getQuantity());

            return itemView;

            //return super.getView(position, convertView, parent);

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rum, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
