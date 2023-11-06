package com.example.myapplication.roles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.roles.entity.Role;
import com.example.myapplication.roles.service.RoleService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RolesViewActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    String url = "http://10.0.2.2:8087/api/role/roles";

    RecyclerView recyclerView;

    List<Role> roles;

    RoleService roleService;

    RoleAdapter roleAdapter;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles_view);
        recyclerView = findViewById(R.id.role_recycle_view);
        imageView=findViewById(R.id.add_icon);
        requestQueue = Volley.newRequestQueue(this);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RoleAddActivity.class);
            startActivity(intent);
        });
    }
    private void sendRequest(RequestQueue requestQueue) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response ->
                {
                    getData(response);
                    roleAdapter = new RoleAdapter(getApplicationContext(), roleService.getList(), R.layout.role_item);
                    recyclerView.setAdapter(roleAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(RolesViewActivity.this));
                }, error -> Log.d("Error", error.getMessage()));
        requestQueue.add(jsonArrayRequest);
    }

    private void getData(JSONArray roles) {
        try {
            for (int i = 0; i < roles.length(); i++) {
                JSONObject object = roles.getJSONObject(i);
                int id = object.getInt("id");
                String name = object.getString("name");
                Role role = new Role(id, name);
                roleService.addItem(role);
            }
        } catch (JSONException e) {
            Log.e("JSON Parsing Error", "Error parsing JSON data: " + e.getMessage());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        roles = new ArrayList<>();
        roleService = new RoleService(roles);
        sendRequest(requestQueue);
    }
}