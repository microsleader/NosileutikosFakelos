package micros_leader.george.nosileutikosfakelos.Interfaces;

import org.json.JSONArray;
import org.json.JSONException;

public interface AsyncCompleteTask {

    void taskComplete(JSONArray results) throws JSONException;
    void taskCompleteGetVardies(JSONArray results) throws JSONException;
}
