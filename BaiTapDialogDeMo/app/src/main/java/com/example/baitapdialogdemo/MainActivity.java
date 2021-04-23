package com.example.baitapdialogdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.baitapdialogdemo.databinding.ActivityMainBinding;
import com.google.android.material.timepicker.TimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        List<String> list = new ArrayList<>();
        list.add("Work");
        list.add("Friend");
        list.add("Family");
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        binding.spMonHoc.setAdapter(stringArrayAdapter);
        binding.tvTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] string = {"Family", "Game", "Android", "VTC", "Friend"};
                boolean[] booleans = {false, false, false, false, false};
                final String[] s = {""};

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Choose tags:")
                        .setMultiChoiceItems(string, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) s[0] += string[which] + ",";
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                binding.tvTags.setText(s[0].substring(0, s[0].length() - 1));

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
        binding.tvWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] string = {"Monday", "Tuesday", "Wednesday", "Thusday", "Friday", "Saturday", "Sunday"};
                boolean[] booleans = {false, false, false, false, false, false, false};
                final String[] s = {""};
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Choose tags:")
                        .setMultiChoiceItems(string, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) s[0] += string[which].substring(0, 3) + ",";
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                binding.tvWeek.setText(s[0].substring(0, s[0].length() - 1));

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
        binding.tvTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                final int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        binding.tvTimePicker.setText(hourOfDay+":"+minute);
                    }
                }, hour, minute, true);

                timePickerDialog.show();
            }
        });
        binding.tvDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.tvDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                String sDate = binding.tvDatePicker.getText().toString();
                Date inputDate;
                try {
                    inputDate = sdf.parse(sDate);
                    cal.setTime(inputDate);
                } catch (ParseException e) {

                }
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        binding.tvDatePicker.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        binding.tvMenuCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getBaseContext(), v);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.menu_cancel, popupMenu.getMenu());
                popupMenu.show();
            }
        });
        binding.tvTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getBaseContext(), v);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.menu_tune, popupMenu.getMenu());
                popupMenu.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_tune, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itFromFile:
                break;
            case R.id.itFromDefault:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}