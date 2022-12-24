package com.example.recyclerviewsdarot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ToggleButton MathOrGeo;
    EditText x1, d;
    TextView n, Sn;
    double[] Sidra = new double[20];
    String[] StringSidra = new String[20];
    double sum;
    int count = 0;
    MyAdapter myAdapter;
    String bhira;
    boolean flag;
    List<Item> items;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        x1 = (EditText) findViewById(R.id.XTv);
        d = (EditText) findViewById(R.id.DTv);
        n = (TextView) findViewById(R.id.NTv);
        Sn = (TextView) findViewById(R.id.SNTv);

        MathOrGeo = (ToggleButton) findViewById(R.id.Tb);
        MathOrGeo.setTextOff("Mathmatical");
        MathOrGeo.setTextOn("Geometrcal");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        items = new ArrayList<Item>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(getApplicationContext(), items, new ItemClickListener() {
            @Override
            public void onItemClick(int i) {
                if (count > 0) {
                    n.setText("n = " + ""+Integer.toString(i+1));
                    if (MathOrGeo.isChecked() == false){
                        sum = ((i+1) * ((2 * Double.parseDouble(x1.getText().toString())) + (i) * (Double.parseDouble(d.getText().toString()))))/2;

                    }
                    else{
                        sum = Double.parseDouble(x1.getText().toString()) * ((Math.pow(Double.parseDouble(d.getText().toString()), (i + 1)) - 1)/(Double.parseDouble(d.getText().toString()) - 1));
                    }
                    bhira = (""+sum);
                    flag = true;
                    bhira = Mekatsher(bhira, flag);
                    Sn.setText(bhira);

                }
            }
        });
        recyclerView.setAdapter(myAdapter);
        EmptyItems();
    }

    public void EmptyItems(){
        for(int i = 0; i < 20; i++){
            items.add(new Item(""));
        }
    }

    public void Update(View view) {
        flag = false;
        myAdapter.notifyDataSetChanged();
        count++;
        System.out.println(x1.getText().toString());
        if(bodek() == true) {
            Sidra[0] = Double.parseDouble(x1.getText().toString());
            System.out.println(Double.parseDouble(x1.getText().toString()));
            System.out.println("4");
            System.out.println((d.getText().toString()));

            Sidra = Mehashev(Sidra);
            for (int i = 0; i < 20; i++) {
                StringSidra[i] = ""+Sidra[i];
                StringSidra[i] = Mekatsher(StringSidra[i], flag);
                items.set(i, new Item(StringSidra[i]));
            }
        }
        else{
            x1.setText("");
            d.setText("");
            Toast.makeText(MainActivity.this, "You must enter a number", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean bodek(){
        String st  = d.getText().toString();
        if (st.matches("-?\\d+(\\.\\d+)?")) {
            st  = x1.getText().toString();
            if (st.matches("-?\\d+(\\.\\d+)?")) {
                return true;
            }
        }
        return false;
    }

    public double[] Mehashev(double[] sidra){
        if (MathOrGeo.isChecked() == false) {
            System.out.println("dorel");
            for (int i = 1; i < 20; i++) {
                System.out.println("guedge");
                System.out.println(Double.parseDouble(d.getText().toString()));
                sidra[i] = sidra[i - 1] + Double.parseDouble(d.getText().toString());

            }
        }
        else {
            for (int i = 1; i < 20; i++) {
                sidra[i] = sidra[i - 1] * Double.parseDouble(d.getText().toString());
            }
        }
        return sidra;
    }

    public static String Mekatsher(String st1, boolean flag){
        int count2 = 0;
        boolean EChecker = false;
        String ozer = "";
        for(int i = 0; i < st1.length(); i++){
            if (st1.charAt(i) == 'E'){
                EChecker = true;
            }
        }
        if (EChecker == true) {
            for (int i = st1.indexOf('.'); i <= st1.indexOf('E'); i++) {
                count2++;
            }
            ozer = st1.substring(st1.indexOf('E') + 1, st1.length());
            count2 += Integer.parseInt(ozer);
            count2 -= 2;
            st1 = st1.substring(0, 4);
            if(flag == true) {
                ozer = "E" + count2;
            }
            else{
                ozer = "" + count2;
            }
            st1 += ozer;

        }


        return st1;

    }
}
