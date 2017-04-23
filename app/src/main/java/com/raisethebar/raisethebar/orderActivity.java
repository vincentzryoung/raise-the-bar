package com.raisethebar.raisethebar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.raisethebar.raisethebar.R;
import com.raisethebar.raisethebar.rumList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 16-01-16.
 */
public class orderActivity extends ActionBarActivity {
    private List<rumList> myRum = new ArrayList<rumList>();
    private List<rumList> myVodka = new ArrayList<rumList>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderpage);



        Button somebtn = (Button) findViewById(R.id.confirmBtn);
        somebtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(orderActivity.this, OrderNumber.class);
                startActivity(intent);            }
        });

        populateRumList();

        populateListView();

    }

    private void populateRumList() {
        myRum.add(new rumList("Shot","Rum","$5.00",0));
        myRum.add(new rumList("Rum and Coke","Rum, Coke","$7.50",0));
        myRum.add(new rumList("Daiquiri","White Rum, Lime Juice, Sugar Syrup","$11.00",0));
        myRum.add(new rumList("Dark & Stormy", "Black Seal Rum, Ginger Beer, Lime Juice", "$13.00", 0));
        myRum.add(new rumList("Rum Punch", "Rum, Sugar Syrup, Lime Juice", "$11.00", 0));
    }



    private void populateListView(){
        ArrayAdapter<rumList> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);

    }

    private class MyListAdapter extends ArrayAdapter<rumList>{


        public MyListAdapter(){
            super(orderActivity.this,R.layout.order_item,myRum);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){

            double[] priceRum = {5,7.5,11,13,11};
            //double[] priceVodka = {5,7.5,11,13};

            //make sure we have a view to work with
            View itemView = convertView;
            if(itemView==null){
                itemView = getLayoutInflater().inflate(R.layout.order_item,parent,false);

            }
            Bundle extras = getIntent().getExtras();
            int[] countRum = extras.getIntArray("count_list_rum");
            Bundle extras2 = getIntent().getExtras();
            int[] countVodka = extras2.getIntArray("count_list_vodka");

            /*if (position % 2 == 1) {
                itemView.setBackgroundColor(0xFF3498DB);
            } else {
                itemView.setBackgroundColor(0xFF2980B9);
            }*/

            rumList currentRum = myRum.get(position);

            double total = 0;
            for(int i=0;i<5;i++){
                total=total+countRum[i]*priceRum[i];
            }

            /*for(int i=0;i<4;i++){
                total=total+countVodka[i]*priceVodka[i];
            }*/

            DecimalFormat df = new DecimalFormat("#.00");
            String totalR = df.format(total);


            TextView score = (TextView) findViewById(R.id.order_total);
            //String totalS = Double.toString(total);
            score.setText(totalR);


            //find rum to work with
            if(countRum[position]>0) {

                //fill the view
                TextView nameText = (TextView) itemView.findViewById(R.id.order_name);
                nameText.setText(currentRum.getRumName());


                TextView priceText = (TextView) itemView.findViewById(R.id.order_price);
                priceText.setText(currentRum.getPrice());

                TextView quantityText = (TextView) itemView.findViewById(R.id.order_quantity);
                String someOutput = Integer.toString(countRum[position]);
                quantityText.setText(someOutput);


                //final TextView quantityText = (TextView) itemView.findViewById(R.id.item_quantity);
                //quantityText.setText(""+currentRum.getQuantity());


                //TextView quantityText = (TextView) itemView.findViewById(R.id.item_quantity);
                //quantityText.setText(currentRum.getQuantity());

            }



            //return super.getView(position, convertView, parent);
            return itemView;



        }

    }

    private PopupWindow POPUP_WINDOW_SCORE = null;
    private void ShowPopup(String message)
    {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        // Inflate the popup_layout.xml
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.activity_popup, null);

        // Creating the PopupWindow
        POPUP_WINDOW_SCORE = new PopupWindow(this);
        POPUP_WINDOW_SCORE.setContentView(layout);
        POPUP_WINDOW_SCORE.setWidth(width);
        POPUP_WINDOW_SCORE.setHeight(height);
        POPUP_WINDOW_SCORE.setFocusable(true);

        // prevent clickable background
        POPUP_WINDOW_SCORE.setBackgroundDrawable(null);

        POPUP_WINDOW_SCORE.showAtLocation(layout, Gravity.CENTER, 1, 1);

        TextView txtMessage = (TextView) layout.findViewById(R.id.layout_popup_txtMessage);
        txtMessage.setText(message);

        // Getting a reference to button one and do something
        Button butOne = (Button) layout.findViewById(R.id.layout_popup_butOne);
        butOne.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Do Something

                //Close Window
                POPUP_WINDOW_SCORE.dismiss();
            }
        });

        // Getting a reference to button two and do something
        Button butTwo = (Button) layout.findViewById(R.id.layout_popup_butTwo);
        butTwo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Do Something

                //Close Window
                POPUP_WINDOW_SCORE.dismiss();
            }
        });
    }

    public void goToOrderNumber(View view){
        Intent intent = new Intent(this, OrderNumber.class);
        startActivity(intent);
    }

}
