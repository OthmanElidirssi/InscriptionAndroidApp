package com.example.myapplication.students;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.roles.entity.Role;
import com.example.myapplication.students.entity.Student;

import org.w3c.dom.Text;

import java.util.List;

public class SrudentDetailsActivity extends AppCompatActivity {

    private TextView name;
    private TextView email;
    private TextView phone;
    private TextView code;
    private TextView libelle;
    private TextView roles_txt;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srudent_details);
        name=findViewById(R.id.student_details_name);
        email=findViewById(R.id.student_details_email);
        phone=findViewById(R.id.student_details_phone);
        code=findViewById(R.id.student_details_filiere_code);
        libelle=findViewById(R.id.student_details_filiere_libelle);
        roles_txt=findViewById(R.id.student_details_roles);
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("student");

        populateUI(student);
    }

    private void populateUI(Student student) {
        name.setText(student.getName());
        email.setText(student.getEmail());
        phone.setText(student.getPhone());
        code.setText(student.getFiliere().getCode());
        libelle.setText(student.getFiliere().getLibelle());
        List<Role> roles=student.getRoles();
        roles_txt.setText("");
        for(Role role:roles){

            String textToWrite="-"+role.getName()+"\n";
            roles_txt.setText(roles_txt.getText()+textToWrite);
        }
    }
}