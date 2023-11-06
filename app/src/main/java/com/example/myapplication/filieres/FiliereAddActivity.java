package com.example.myapplication.filieres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

public class FiliereAddActivity extends AppCompatActivity implements View.OnClickListener {

    private String url="http://10.0.2.2:8087/api/v1/filieres";
    private RequestQueue requestQueue;
    private Button button;
    private EditText code;
    private EditText libelle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filiere_add);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        button=findViewById(R.id.filiere_add);
        code=findViewById(R.id.filiere_code_edit_text);
        libelle=findViewById(R.id.filiere_libelle_edit_text);
        button.setOnClickListener(this);
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

        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.POST, url, object, response -> finish(), error -> Log.d("JsonError",error.getMessage()));
        requestQueue.add(jsonObjectRequest);
    }

}