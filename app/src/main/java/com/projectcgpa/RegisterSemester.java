package com.projectcgpa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projectcgpa.entities.User;

public class RegisterSemester extends AppCompatActivity {

    DrawerLayout drawerLayout;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_semester);

        getSupportActionBar().hide();
        drawerLayout = findViewById(R.id.drawer_layout);

        dbHelper = new DBHelper(this);

        TextView fullnameTV = (TextView) findViewById(R.id.usernameTV);
        TextView studentIdTV = (TextView) findViewById(R.id.studentIdTV);
        TextView clickSem = (TextView) findViewById(R.id.clickSemTV);
        TextView AddNewSem = (TextView) findViewById(R.id.btnAddsem);
        TextView clickHome = (TextView) findViewById(R.id.clickHome);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        fullnameTV.setText(user.getFullname());
        long id = user.getStudent_Id();
        String studId = Long.toString(id);
        studentIdTV.setText(studId);

        clickSem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        AddNewSem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(RegisterSemester.this);
                View mview = getLayoutInflater().inflate(R.layout.addnewsem_dialog, null);

                final EditText newSemET = (EditText) mview.findViewById(R.id.semNameET);
                Button btn_cancel = (Button) mview.findViewById(R.id.btn_cancelSem);
                Button btn_save = (Button) mview.findViewById(R.id.btn_saveSem);

                alert.setView(mview);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newSem = newSemET.getText().toString().trim();
                        String studentId = studId.trim();

                        ContentValues values = new ContentValues();

                        if(newSem.equals("")){
                            Toast.makeText(RegisterSemester.this, "Field cannot be blank!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                        }

                    }
                });

                alertDialog.show();
            }
        });

        clickHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterSemester.this,MainActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

    }

    public void clickMenu(View view) {
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void clickHome(View view) {
        startActivity(new Intent(RegisterSemester.this, MainActivity.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}