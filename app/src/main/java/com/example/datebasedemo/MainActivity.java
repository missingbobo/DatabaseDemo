package com.example.datebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewAdd();
        setupQuery();
        setupDel();
        setupEdit();
    }

    private void setupViewAdd() {
        final EditText addEt = findViewById(R.id.etAdd);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person person = new Person();
                person.setName(addEt.getText().toString());
                DBHelper.getInstance(MainActivity.this).add(person);
            }
        });
    }

    private void setupQuery() {
        findViewById(R.id.query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView show = findViewById(R.id.show);
                ArrayList<Person> items = DBHelper.getInstance(MainActivity.this).getItems();
                StringBuffer stringBuffer = new StringBuffer();
                for (Person p : items) {
                    stringBuffer.append(p.getId() + "-" + p.getName()+",");
                }
                show.setText(stringBuffer.toString());
            }
        });

    }

    private void setupDel() {
        findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.etDel);
                DBHelper.getInstance(MainActivity.this).del(Integer.valueOf(editText.getText().toString()));
            }
        });
    }

    private void setupEdit() {
        findViewById(R.id.edt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = ((EditText) findViewById(R.id.etbef)).getText().toString();
                String after = ((EditText) findViewById(R.id.etaft)).getText().toString();
                Person p = new Person();
                p.setId(Integer.valueOf(id));
                p.setName(after);
                DBHelper.getInstance(MainActivity.this).update(p);
            }
        });
    }
}
