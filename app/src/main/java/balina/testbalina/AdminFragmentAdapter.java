package balina.testbalina;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AdminFragmentAdapter extends BaseAdapter {

    private String jsonString;
    private JSONObject jsonObject;
    private Context context;

    public AdminFragmentAdapter(Context context) {
        this.context = context;
        jsonObject = MainAdminUser.getJSONFromFile(context);
        Log.d(MainAdminUser.JSON_KEY, jsonObject.toString());
    }

    @Override
    public int getCount() {
        Log.d(MainAdminUser.JSON_KEY,"" +jsonObject.length());
        return jsonObject.length();
    }

    @Override
    public Object getItem(int i) {
        try {
            return jsonObject.names().get(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.task_layout, viewGroup, false);
        } else {
            rowView = view;
        }
        TextView task_name = (TextView) rowView.findViewById(R.id.task_name);
        TextView task_brief = (TextView) rowView.findViewById(R.id.task_brief);
        TextView task_date = (TextView) rowView.findViewById(R.id.task_date);
        TextView task_time = (TextView) rowView.findViewById(R.id.task_time);
        TextView task_cost = (TextView) rowView.findViewById(R.id.task_cost);
        try {
            task_name.setText(jsonObject.names().getString(i));
            task_brief.setText(jsonObject.getJSONArray(jsonObject.names().getString(i)).getString(0));
            task_date.setText(jsonObject.getJSONArray(jsonObject.names().getString(i)).getString(1));
            task_time.setText(jsonObject.getJSONArray(jsonObject.names().getString(i)).getString(2));
            task_cost.setText(jsonObject.getJSONArray(jsonObject.names().getString(i)).getString(3));
            Log.d(MainAdminUser.JSON_KEY, jsonObject.names().getString(i));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowView;
    }
}
