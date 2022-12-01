package com.example.lab_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    int flagResult = RESULT_CANCELED;
    String flagAction = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void onSendClick(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            flagResult = RESULT_OK;
//            flagAction.concat("Отправлена смс!");
            flagAction += " Отправлена смс!";
            TextView textView = findViewById(R.id.tw_resultPhoneMessage);
            EditText editTextMessage = findViewById(R.id.et_textMessage);

            String phoneText = extras.get("phone").toString();
            String textMessage = editTextMessage.getText().toString();

            textView.setText(
                    "Телефон: " +  phoneText + '\n' +
                            "Сообщение: \n" + textMessage
            );

            /// Стандартный ввод смс через сторонние программы
//            String toSms="smsto:" + phoneText;
//
//            Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse(toSms));
//
//            sms.putExtra("sms_body", textMessage);
//            startActivity(sms);

            /// Но как и с телефоном мы также можем использовать прямую отправку
            /// смс без сторонних программ
            SmsManager.getDefault()
                    .sendTextMessage(phoneText, null, textMessage.toString(), null, null);
        }


    }

    public void onCallClick(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TextView textView = findViewById(R.id.tw_resultPhoneMessage);
            String phoneText = extras.get("phone").toString();

            textView.setText("Совершен звонок на номер: " +  phoneText);

            String toCall = "tel:" + phoneText;
//            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toCall)));
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(toCall)));
        }

        flagResult = RESULT_OK;
        flagAction += " Совершен вызов!";
    }

    public void onCancelClick(View view) {
        Intent intent = new Intent();

        intent.putExtra(MainActivity.ACCESS_MESSAGE, flagResult);
        intent.putExtra(MainActivity.RESULT_MESSAGE, flagAction);
        setResult(flagResult, intent);
        finish();
    }
}