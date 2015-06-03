package balina.testbalina;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A placeholder fragment containing a simple view.
 */
public class AdminFragment extends ListFragment implements View.OnClickListener  {

    public AdminFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutInflater = inflater.inflate(R.layout.fragment_admin, container, false);
        Button addTask = (Button) layoutInflater.findViewById(R.id.add_task);
        addTask.setOnClickListener(this);
        return layoutInflater;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(MainAdminUser.JSON_KEY, "requestCode" + requestCode + "\n" + "resultCode" + resultCode);
        if(resultCode==1) {
            AdminFragmentAdapter adapter = new AdminFragmentAdapter(getActivity());
            setListAdapter(adapter);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AdminFragmentAdapter adapter = new AdminFragmentAdapter(getActivity());
        setListAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), AdminTask.class);
        startActivityForResult(intent, 1);
    }
}
