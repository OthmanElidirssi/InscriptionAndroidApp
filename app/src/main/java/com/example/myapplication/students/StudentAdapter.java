package com.example.myapplication.students;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.filieres.FiliereUpdateActivity;
import com.example.myapplication.students.entity.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentHolder> {

    private Context context;
    private LayoutInflater inflater;
    private int layout;
    private List<Student> students;

    public StudentAdapter(Context context, int layout, List<Student> students) {
        this.context = context;
        this.layout = layout;
        this.students = students;
        this.inflater=LayoutInflater.from(this.context);
    }




    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=this.inflater.inflate(layout,parent,false);
        StudentHolder holder=new StudentHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student student=students.get(position);
        holder.populate(student);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SrudentDetailsActivity.class);
                intent.putExtra("student",student);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
