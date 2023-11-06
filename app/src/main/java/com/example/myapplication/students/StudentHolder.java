package com.example.myapplication.students;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.students.entity.Student;

public class StudentHolder extends RecyclerView.ViewHolder {

    private TextView id;
    private TextView name;
    private TextView email;
    private TextView phone;
    public StudentHolder(@NonNull View itemView) {
        super(itemView);
        id=itemView.findViewById(R.id.student_id);
        name=itemView.findViewById(R.id.student_name);
        email=itemView.findViewById(R.id.student_email);
        phone=itemView.findViewById(R.id.student_phone);
    }

    public void populate(Student student){
        id.setText(String.valueOf(student.getId()));
        name.setText(student.getName());
        email.setText(student.getEmail());
        phone.setText(student.getPhone());
    }
}
