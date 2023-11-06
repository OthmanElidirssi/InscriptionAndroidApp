package com.example.myapplication.roles;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RoleAddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button button;
    RequestQueue requestQueue;
    String url="http://10.0.2.2:8087/api/role/save";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_add);
        editText=findViewById(R.id.role_edit_text);
        button=findViewById(R.id.role_add);
        requestQueue= Volley.newRequestQueue(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String role=editText.getText().toString();

        JSONObject object=new JSONObject();
        try {
            object.put("name",role);
            sendRequest(object);
        } catch (JSONException e) {
           Log.d("JsonException",e.getMessage());
        }


    }

    private void sendRequest(JSONObject object) {

        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JsonError",error.getMessage());

            }
        });

        requestQueue.add(jsonObjectRequest);
    }

}