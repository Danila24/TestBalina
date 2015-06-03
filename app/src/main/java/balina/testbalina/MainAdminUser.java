package balina.testbalina;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainAdminUser extends ActionBarActivity implements View.OnClickListener {

    public static final String JSON_KEY = "JsonKEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_admin_user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id){
            case R.id.admin:
                Intent admin = new Intent(this, Admin.class);
                startActivity(admin);
                break;
            case R.id.user:
                Intent user = new Intent(this, User.class);
                startActivity(user);
                break;
        }
    }

    public static JSONObject getJSONFromFile(Context context){
        JSONObject object = null;
        File file = new File(context.getFilesDir(), "tasks.json");
        if(!file.exists()){
            object = new JSONObject();
            Log.d(JSON_KEY, "file doesn't exist");
            return object;
        }
        FileInputStream stream = null;
        try {
            StringBuffer buffer = new StringBuffer();
            stream = new FileInputStream(file);
            int read = -1;
            while((read=stream.read())!=-1){
                buffer.append((char)read);
            }
            String jsonString = buffer.toString();
            object = new JSONObject(jsonString);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void saveJSONToFile(Context context, JSONObject object){
        File file = new File(context.getFilesDir(),"tasks.json");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            stream.write(object.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
