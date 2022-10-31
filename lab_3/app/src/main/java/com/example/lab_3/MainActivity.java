package com.example.lab_3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // набор данных, которые свяжем со списком
    String[] countries = { "Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};
    String[] spin = { "Браз", "Арген", "Колум", "Чи", "Уруг"};
    private final String[] catNames = new String[] {
            "Выбрать", "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
            "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
            "Китти", "Масяня", "Симба"
    };

    private String str = null;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // получаем элемент ListView по его id
        ListView countriesList = findViewById(R.id.lw_countriesList);
        Spinner spinnerList = findViewById(R.id.s_countriesList);

        // получаем объект RadioGroup
        RadioGroup radGrp = (RadioGroup)findViewById(R.id.radios);

        // создаем адаптер
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, catNames);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, catNames);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // устанавливаем для списка адаптер
        countriesList.setAdapter(adapter1);

        spinnerList.setAdapter(adapter2);
        spinnerList.setPrompt("Title");

        // получаем элемент TextView
        TextView selection = findViewById(R.id.tw_selection);
        String[] selection1 = new String[] {"", "", ""};

        selection.setText("Здесь будет результат!");

        selection.setHint("что-то будет");

        countriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selection.setText("");
                // получаем выбранный элемент
                TextView textView = (TextView) view;
                String selectedItem = (String)textView.getText();
                // установка текста элемента TextView
                selection1[0] = selectedItem + " ";
                str = String.join("", selection1);
                selection.setText(str);
                // или так
                // selection.setText(textView.getText());
            }
        });

        spinnerList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // получаем выбранный элемент
                if (i == 0) {

                } else {
                    TextView textView = (TextView) view;
                    String selectedItem = (String) textView.getText();
                    // установка текста элемента TextView
                    selection1[1] = selectedItem + " ";

                    str = String.join("", selection1);
                    selection.setText(str);
                }
                // selection.setText(textView.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        radGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedRadioButton  = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                selection1[2] = selectedRadioButton.getText().toString();
//                        selection.setText("Выбрана Java");
                str = String.join("", selection1);
                selection.setText(str);
            }
        });


    }
}