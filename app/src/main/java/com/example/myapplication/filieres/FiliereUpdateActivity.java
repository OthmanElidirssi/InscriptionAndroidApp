package com.example.myapplication.filieres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.filieres.entity.Filiere;

import org.json.JSONException;
import org.json.JSONObject;

public class FiliereUpdateActivity extends AppCompatActivity  implements View.OnClickListener {

    Button update;

    EditText code;

    EditText libelle;

   String url;

    RequestQueue requestQueue;

    private int id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filiere_update);
        update=findViewById(R.id.filiere_update);
        code=findViewById(R.id.filiere_code_update_edit_text);
        libelle=findViewById(R.id.filiere_libelle_update_edit_text);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        Intent intent = getIntent();
        Filiere filiere = (Filiere) intent.getSerializableExtra("filiere");
        id=filiere.getId();
        url="http://10.0.2.2:8087/api/v1/filieres/"+id;
        populateUI(filiere);
        update.setOnClickListener(this);
    }
    private void populateUI(Filiere filiere) {
        code.setText(filiere.getCode());
        libelle.setText(filiere.getLibelle());
    }

    @Override
    public void onClick(View v) {
        String codeName=code.getText().toString();
        String libelleName=libelle.getText().toString();

        JSONObject object=new JSONObject();
        try {
            object.put("code",codeName);
            object.put("libelle",libelleName);
            sendRequest(object);
        } catch (JSONException e) {
            Log.d("JsonException",e.getMessage());
        }
    }

    private void sendRequest(JSONObject object) {
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.PUT, url, object, response -> finish(), error -> Log.d("JsonError",error.toString()));
        requestQueue.add(jsonObjectRequest);
    }
}