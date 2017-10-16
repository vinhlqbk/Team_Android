package com.example.tainv.myapplication;

import android.app.Activity;
import android.app.TabActivity;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import  android.widget.TabHost;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class MainActivity extends TabActivity {
    List<restaurent> model = new ArrayList<restaurent>();
    ArrayAdapter<restaurent> adapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save = (Button) findViewById(R.id.save);

        save.setOnClickListener(onSave);
        ListView list = (ListView) findViewById(R.id.restaurants);

        adapter = new RestaurantAdapter();
        list.setAdapter(adapter);

        TabHost.TabSpec spec=getTabHost().newTabSpec("tag1");
        spec.setContent(R.id.restaurants);
        spec.setIndicator("List", getResources()
                .getDrawable(R.drawable.list));
        getTabHost().addTab(spec);

        spec=getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.details);
        spec.setIndicator("Details", getResources()
                .getDrawable(R.drawable.restaurant));
        getTabHost().addTab(spec);
        getTabHost().setCurrentTab(1);
    }


    class RestaurantAdapter extends ArrayAdapter<restaurent> {
        RestaurantAdapter() {
            super(MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    model);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
// return super.getView(position, convertView, parent);
            LayoutInflater inflater = getLayoutInflater();
            RestaurantHolder holder;
//tạo view mới sử dụng file row.xml
            View row;
            if (convertView == null) {
                row = inflater.inflate(R.layout.restaurent, null);
                holder = new RestaurantHolder(row);
                row.setTag(holder);
            } else {
                row = convertView;
                holder = (RestaurantHolder) row.getTag();
            }
//lấy đối tượng từ model tại vị trí position
            restaurent r = model.get(position);
            holder.populateFrom(r);
//đẩy dữ liệu từ dối tượng vào view vừa tạo ra ở trên


//trả về view vừa tạo
            return row;
        }
    }// end of RestaurantAdapter

    static class RestaurantHolder {

        private TextView name = null;
        private TextView address = null;
        private ImageView icon = null;
        private ImageView iconDiscount = null;

        RestaurantHolder(View row) {
            name = (TextView) row.findViewById(R.id.title);
            address = (TextView) row.findViewById(R.id.address);
            icon = (ImageView) row.findViewById(R.id.icon);
            iconDiscount = (ImageView) row.findViewById(R.id.iconDiscount);
        }

        void populateFrom(restaurent r) {
            name.setText(r.getName());
            address.setText(r.getAddress());
            if (r.getType().equals("sit_down")) {
                icon.setImageResource(R.drawable.ball_red);
            } else if (r.getType().equals("take_out")) {
                icon.setImageResource(R.drawable.ball_yellow);
            } else {
                icon.setImageResource(R.drawable.ball_green);
            }

            if(r.getDiscount() == "25"){
                iconDiscount.setImageResource(R.drawable.sale);
            } else if(r.getDiscount() == "50"){
                iconDiscount.setImageResource(R.drawable.sale1);
            } else if(r.getDiscount() == "75"){
                iconDiscount.setImageResource(R.drawable.sale2);
            }
        }
    }

    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            restaurent r = new restaurent();
            EditText name = (EditText) findViewById(R.id.name);
            EditText address = (EditText) findViewById(R.id.addr);

            r.setName(name.getText().toString());
            r.setAddress(address.getText().toString());

            RadioGroup types = (RadioGroup) findViewById(R.id.types);
            RadioGroup discount = (RadioGroup) findViewById(R.id.saleoff);

            switch (types.getCheckedRadioButtonId()) {
                case R.id.sit_down:
                    r.setType("sit_down");
                    break;

                case R.id.take_out:
                    r.setType("take_out");
                    break;

                case R.id.delivery:
                    r.setType("delivery");
                    break;
            }
            switch (discount.getCheckedRadioButtonId()) {

                case R.id.sale25:
                    r.setDiscount("25");
                    break;

                case R.id.sale50:
                    r.setDiscount("50");
                    break;
                case R.id.sale75:
                    r.setDiscount("75");
                    break;
            }
            adapter.add(r);
        }
    };
}
