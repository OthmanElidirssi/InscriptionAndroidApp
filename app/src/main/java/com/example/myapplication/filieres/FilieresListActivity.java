package com.example.myapplication.filieres;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.filieres.entity.Filiere;
import com.example.myapplication.roles.RoleAdapter;
import com.example.myapplication.roles.RolesViewActivity;
import com.example.myapplication.roles.entity.Role;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FilieresListActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Filiere> filieres;
    private String url = "http://10.0.2.2:8087/api/v1/filieres";
    private RequestQueue requestQueue;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private FiliereAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filieres_list);
        recyclerView = findViewById(R.id.filiere_recycle_view);
        imageView = findViewById(R.id.filiere_add_icon);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        imageView.setOnClickListener(this);
        new ItemTouchHelper(helper).attachToRecyclerView(recyclerView);
    }
    @Override
    protected void onResume() {
        super.onResume();
        filieres = new ArrayList<>();
        sendGETRequest(requestQueue);
    }
    private void sendGETRequest(RequestQueue requestQueue) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response ->
                {
                    getData(response);
                    adapter = new FiliereAdapter(getApplicationContext(), R.layout.filiere_item, filieres);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }, error -> Log.d("Error", error.getMessage()));
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
            Log.e("JSON Parsing Error", "Error parsing JSON data: " + e.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), FiliereAddActivity.class);
        startActivity(intent);
    }
    ItemTouchHelper.SimpleCallback helper =
            new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                    int position = viewHolder.getAdapterPosition();
                    SendDELETERequest(requestQueue, position);

                }
            };

    private void SendDELETERequest(RequestQueue requestQueue,int position) {
        int id = filieres.get(position).getId();
        String url = "http://10.0.2.2:8087/api/v1/filieres/" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null,
                response -> {
                    filieres.remove(position);
                    adapter.notifyDataSetChanged();
                },
                error ->
                        Log.d("JsonError", error.getMessage()));

        requestQueue.add(jsonObjectRequest);
    }
}