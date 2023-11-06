package com.example.myapplication.stdfiliere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.filieres.entity.Filiere;
import com.example.myapplication.roles.entity.Role;
import com.example.myapplication.students.StudentAdapter;
import com.example.myapplication.students.entity.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class StudentFiliere extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener {


    Spinner spinner;
    Button button;

    List<Student> students;
    RecyclerView recyclerView;
    StudentAdapter student_adapter;

    RequestQueue requestQueue;

    List<Filiere> filieres;

    List<String> filiereNames;
    ArrayAdapter<String> adapter;

    String filiere_url="http://10.0.2.2:8087/api/v1/filieres";
    String student_url="http://10.0.2.2:8087/api/student/filiere/";

    Filiere selectedFiliere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_filiere);
        spinner=findViewById(R.id.filiere_spinner);
        button=findViewById(R.id.filiere_spinner_search);
        recyclerView = findViewById(R.id.student_filiere_recycle_view);
        filiereNames=new ArrayList<>();
        filieres=new ArrayList<>();
        students=new ArrayList<>();
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=selectedFiliere.getId();
        sendStudentGETRequest(requestQueue,id);
    }

    private void sendStudentGETRequest(RequestQueue requestQueue, int id) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,student_url+id,null,
                response ->
                {
                    getStudentData(response);
                    student_adapter = new StudentAdapter(getApplicationContext(), R.layout.student_item, students);
                    recyclerView.setAdapter(student_adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }, error -> Log.d("Error in method sendStudentGETRequest", error.toString()));
        requestQueue.add(jsonArrayRequest);
    }

    private void getStudentData(JSONArray response) {
        students.clear();
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject object = response.getJSONObject(i);
                int id = object.getInt("id");
                String name = object.getString("name");
                String email = object.getString("email");
                String phone = object.getString("phone");
                Filiere filiere = null;
                List<Role> roles = null;

                Student student = new Student(id, name, email, phone, filiere, roles);
                students.add(student);
            }
        } catch (JSONException e) {
            Log.e("JSON Parsing Error", "Error parsing JSON data: " + e);
        }
    }


    private void sendFiliereGETRequest(RequestQueue requestQueue) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,filiere_url, null,
                response ->
                {
                    getData(response);
                    populateSpinner();
                }, error -> Log.d("Error", error.toString()));
        requestQueue.add(jsonArrayRequest);
    }

    private void getData(JSONArray roles) {
        try {
            for (int i = 0; i < roles.length(); i++) {
                JSONObject object = roles.getJSONObject(i);
                int id = object.getInt("id");
                String code = object.getString("code");
                String libelle = object.getString("libelle");
                Filiere filiere = new Filiere(id, code, libelle);
                filieres.add(filiere);
            }
        } catch (JSONException e) {
            Log.e("JSON Parsing Error", "Error parsing JSON data: " + e.toString());
        }
    }
    private void populateSpinner() {
        for (Filiere filiere : filieres) {
            filiereNames.add(filiere.getCode());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, filiereNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       selectedFiliere=filieres.get(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sendFiliereGETRequest(requestQueue);
        if(filieres.size()!=0) {
            selectedFiliere=filieres.get(0);
        }
    }
}
