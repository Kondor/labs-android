package com.example.lab_3;

import static android.view.KeyEvent.KEYCODE_ENTER;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

    List<String> newList = new ArrayList<>(Arrays.asList(catNames));

    private String str = null;
    private int indexEdit = -1;
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
                android.R.layout.simple_list_item_1, newList);

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

                indexEdit = i;

                EditText editText = (EditText) findViewById(R.id.et_input);
                if (indexEdit > 0) editText.setText(newList.get(indexEdit));

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

        EditText et = (EditText) findViewById(R.id.et_input);
        Button bRes = (Button) findViewById(R.id.b_result);

        List<String> listCount = new ArrayList<>();

        bRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("");
            }
        });

        Button bAdd = (Button) findViewById(R.id.b_add);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et.getText().toString();
                selection.setText(name);

                if(!name.isEmpty()){
                    newList.add(0, name);
                    et.setText("");
                    adapter1.notifyDataSetChanged();
                }
//                newList.add(0, name);
                // для обновления ListView
//                adapter1.notifyDataSetChanged();


            }
        });

        Button bEdit = (Button) findViewById(R.id.b_edit);
        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //et.setText(newList.get(indexEdit));

                String name = et.getText().toString();

                if(!name.isEmpty()){
                    newList.set(indexEdit, et.getText().toString());
                    et.setText("");
                    indexEdit = -1;
                    adapter1.notifyDataSetChanged();
                }



                //String name = et.getText().toString();
//                selection.setText(name);
//
//                if(!name.isEmpty()){
//                    newList.set(indexEdit, et.getText().toString());
//                    et.setText("");
//                    indexEdit = -1;
//                    adapter1.notifyDataSetChanged();
//                }

//                newList.set(indexEdit, et.getText().toString());
                //EditText editText = (EditText) findViewById(R.id.et_input);

//                selection.setText(editText.getText().toString());
//                newList.set(indexEdit, editText.getText().toString());

                // для обновления ListView
                //adapter1.notifyDataSetChanged();
//                editText.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String name = editText.getText().toString();
//                        newList.set(indexEdit, name);
//
//                        // для обновления ListView
//                        adapter1.notifyDataSetChanged();
//                    }
//                });

            }
        });


        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                boolean consumed = false;
                if (i == KEYCODE_ENTER) {
                    //Делаем то, что нам нужно...
                    String name = et.getText().toString();

                    if(!name.isEmpty()){
                        newList.set(indexEdit, et.getText().toString());
                        et.setText("");
                        indexEdit = -1;
                        adapter1.notifyDataSetChanged();
                    }

                    selection.setText(name);
                    et.clearFocus();
                    ; //это если не хотим, чтобы нажатая кнопка обрабатывалась дальше видом, иначе нужно оставить false
                }
                return consumed;
            }
        });

        Button bDelete = (Button) findViewById(R.id.b_delete);
        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indexEdit >= 0) {
                    newList.remove(newList.get(indexEdit));
                }
//                newList.remove(newList.get(indexEdit));
                indexEdit = -1;
                // для обновления ListView
                adapter1.notifyDataSetChanged();
            }
        });

    }

}