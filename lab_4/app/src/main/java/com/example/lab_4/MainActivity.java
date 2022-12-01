package com.example.lab_4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Musician musician = new Musician(
            "alone wolf",
            21,
            "hard-rock",
            10,
            "guitar",
            56000.56f,
            "+79176342569"
    );

    //Musician musician = new Musician();

    EditText etName;
    EditText etAge;
    EditText etGenre;
    EditText etCountMusic;
    EditText etInstrument;
    EditText etMoneyCondition;
    EditText etPhone;

    final static String COUNT_MUSIC_VARIABLE_KEY = "COUNT_MUSIC_VARIABLE";

    // это будет именем файла настроек
    public static final String APP_PREFERENCES = "Account";
    // Параметр, который будем сохранять в настройках
    public static final String APP_PREFERENCES_COUNT_MUSIC = "countMusic"; // имя кота
    SharedPreferences settings;
    SharedPreferences.Editor prefEditor ;

    static final String ACCESS_MESSAGE = "ACCESS_MESSAGE";
    static final String RESULT_MESSAGE = "RESULT_MESSAGE";

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            TextView textView = findViewById(R.id.tw_result);

            if(result.getResultCode() == Activity.RESULT_OK){
                Intent intent = result.getData();
                if (intent != null) {
                    //String accessMessage = intent.getStringExtra(ACCESS_MESSAGE);
                    String actionMessage = intent.getStringExtra(RESULT_MESSAGE);
                    textView.setText(actionMessage);
                }
            } else if(result.getResultCode() == Activity.RESULT_CANCELED) {
                textView.setText("Действие отменено!");
            } else{
                textView.setText("Ошибка доступа");
            }
        }
    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        etGenre = findViewById(R.id.et_genre);
        etCountMusic = findViewById(R.id.et_countMusic);
        etInstrument = findViewById(R.id.et_instrument);
        etMoneyCondition = findViewById(R.id.et_moneyCondition);
        etPhone = findViewById(R.id.et_phone);

        etCountMusic.setText(String.valueOf(settings.getInt(APP_PREFERENCES_COUNT_MUSIC, -1)));

        Button bSaveData = findViewById(R.id.b_save);
        bSaveData.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                musician.setCountMusic(Integer.parseInt(etCountMusic.getText().toString()));
                TextView dataView = findViewById(R.id.tw_result);
                dataView.setText("Count music " + musician.getCountMusic());
            }
        });

        Button bGetData = findViewById(R.id.b_data);
        bGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etName.setText(musician.getName());
                etAge.setText(String.valueOf(musician.getAge()));
                etGenre.setText(musician.getGenre());
                etCountMusic.setText(String.valueOf(musician.getCountMusic()));
                etInstrument.setText(musician.getInstrument());
                etMoneyCondition.setText(String.valueOf(musician.getMoneyCondition()));
                etPhone.setText(musician.getPhone());
            }
        });
    }

    // сохранение состояния
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(COUNT_MUSIC_VARIABLE_KEY, musician.getCountMusic());
        super.onSaveInstanceState(outState);
    }

    // получение ранее сохраненного состояния
    @SuppressLint("SetTextI18n")
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        musician.setCountMusic(savedInstanceState.getInt(COUNT_MUSIC_VARIABLE_KEY));
        TextView dataView = findViewById(R.id.tw_result);
        dataView.setText("Count music: " + musician.getCountMusic());
    }

    @Override
    protected void onStop() {
        super.onStop();

        // сохраняем в настройках
        prefEditor = settings.edit();
        prefEditor.putInt(APP_PREFERENCES_COUNT_MUSIC, Integer.parseInt(etCountMusic.getText().toString()));
        musician.setCountMusic(Integer.parseInt(etCountMusic.getText().toString()));
        prefEditor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();

        int countMusic = settings.getInt(APP_PREFERENCES_COUNT_MUSIC, -1);
        etCountMusic.setText(String.valueOf(countMusic));
        musician.setCountMusic(countMusic);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Операции для выбранного пункта меню
        switch (item.getItemId())
        {
            case R.id.transition_second_activity:
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("phone", etPhone.getText().toString());
                //startActivity(intent);
                mStartForResult.launch(intent);
            case R.id.save_settings:
                return true;
            case R.id.open_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}