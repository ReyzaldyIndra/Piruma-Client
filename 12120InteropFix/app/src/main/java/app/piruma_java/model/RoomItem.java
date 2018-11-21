package app.piruma_java.model;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.piruma_java.AppController;
import app.piruma_java.network.VolleyNetwork;

import static com.android.volley.VolleyLog.TAG;


public class RoomItem {
    private String name_room;
    private String name_dept;

    public RoomItem(String name_room, String name_dept){
        this.name_room = name_room;
        this.name_dept = name_dept;
    }

    public String getName_room() {
                return name_room;
            }

    public void setName_room(String name_room) {
        this.name_room = name_room;
    }

    public String getName_dept() {
        return name_dept;
    }

    public void setName_dept(String name_dept) {
        this.name_dept = name_dept;
    }

}
