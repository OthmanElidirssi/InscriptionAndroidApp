package com.example.myapplication.students;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.filieres.entity.Filiere;
import com.example.myapplication.roles.entity.Role;
import com.example.myapplication.students.entity.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentsListActivity extends AppCompatActivity {

    private List<Student> students;
    private String url = "http://10.0.2.2:8087/api/student";
    private RequestQueue requestQueue;//used
    private ImageView imageView;//not used
    private RecyclerView recyclerView;//used
    private StudentAdapter adapter;//not used yet

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        recyclerView = findViewById(R.id.student_recycle_view);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        students = new ArrayList<>();
        sendGETRequest(requestQueue);
    }

    private void sendGETRequest(RequestQueue requestQueue) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response ->
                {
                    getData(response);
                    adapter = new StudentAdapter(getApplicationContext(), R.layout.student_item, students);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }, error -> Log.d("Error", error.getMessage()));
        requestQueue.add(jsonArrayRequest);
    }

    private void getData(JSONArray respnse) {

        try {
            for (int i = 0; i < respnse.length(); i++) {
                JSONObject object = respnse.getJSONObject(i);
                int id = object.getInt("id");
                String name = object.getString("name");
                String email = object.getString("email");
                String phone = object.getString("phone");
                Filiere filiere = getFiliere(object);
                List<Role> roles = getRoles(object);

                Student student = new Student(id, name, email, phone, filiere, roles);
                students.add(student);
            }
        } catch (JSONException e) {
            Log.e("JSON Parsing Error", "Error parsing JSON data: " + e.getMessage());
        }
    }

    private List<Role> getRoles(JSONObject object) {

        List<Role> roles = new ArrayList<>();

        try {
            JSONArray roles_json = object.getJSONArray("roles");

            for (int i = 0; i < roles_json.length(); i++) {
                roles.add(getRole(roles_json.getJSONObject(i)));
            }
        } catch (JSONException e) {
            Log.d("Get Roles Error", e.getMessage());
        }

        return roles;
    }

    private Role getRole(JSONObject jsonObject) {

        Role role = new Role();

        try {
            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            role = new Role(id, name);
        } catch (JSONException e) {
            Log.d("Get Role Error", e.getMessage());
        }

        return role;
    }

    private Filiere getFiliere(JSONObject object) {

        Filiere filiere = new Filiere();


        try {
            JSONObject filiere_json = object.getJSONObject("filiere");
            int id = filiere_json.getInt("id");
            String code = filiere_json.getString("code");
            String libelle = filiere_json.getString("libelle");
            filiere = new Filiere(id, code, libelle);
        } catch (JSONException e) {
            Log.d("Get Filiere Error", e.getMessage());
        } finally {
            return filiere;
        }
    }
}
