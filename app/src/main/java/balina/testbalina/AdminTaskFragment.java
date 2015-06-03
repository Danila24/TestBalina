package balina.testbalina;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * A placeholder fragment containing a simple view.
 */
public class AdminTaskFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    EditText name, brief, cost;
    Button dateTime, time;


    public AdminTaskFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_task, container, false);
        Button add = (Button) view.findViewById(R.id.ok);
        add.setOnClickListener(this);
        name = (EditText) view.findViewById(R.id.name);
        brief = (EditText) view.findViewById(R.id.brief);
        dateTime = (Button) view.findViewById(R.id.DateTime);
        dateTime.setOnClickListener(this);
        time = (Button) view.findViewById(R.id.Time);
        time.setOnClickListener(this);
        cost = (EditText) view.findViewById(R.id.cost);
        return view;
    }

    private void saveJSON(){
        JSONObject object = MainAdminUser.getJSONFromFile(getActivity());
        JSONArray array = new JSONArray();
        array.put(brief.getText().toString());
        array.put(dateTime.getText().toString());
        array.put(time.getText().toString());
        array.put(cost.getText().toString());
        try {
            object.putOpt(name.getText().toString(), array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MainAdminUser.saveJSONToFile(getActivity(), object);
    }

    public void onClickDateTime(){
        Log.d(MainAdminUser.JSON_KEY, "DateTime");

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id==R.id.DateTime){
            DatePickerDialog dateDialog = new DatePickerDialog(getActivity(), this, 2015, 06, 03);
            dateDialog.show();
        }else if(id==R.id.ok) {
            saveJSON();
            Intent intent = new Intent();
            getActivity().setResult(1, intent);
            getActivity().finish();
        }else if(id==R.id.Time){
            TimePickerDialog timeDialog = new TimePickerDialog(getActivity(), this, 15, 00, true);
            timeDialog.show();
        }

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Log.d(MainAdminUser.JSON_KEY, "i - " + i + " i1 - " + i1 + "i2" +i2);
        dateTime.setText(i2 + " - " + i1+" - " + i);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.d(MainAdminUser.JSON_KEY, "hourOfDay - " + hourOfDay+ " minute " + minute);
        time.setText(hourOfDay +" : "+minute);
    }
}