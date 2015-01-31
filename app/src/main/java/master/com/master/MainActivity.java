package master.com.master;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import master.com.master.dialog.AboutDialog;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.tb_custom)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle("旅游助手");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, getResources().getString(R.string.action_settings), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_updates:
                Toast.makeText(this, getResources().getString(R.string.action_updates), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_aboutus:
                new AboutDialog().show(getFragmentManager(), "关于");
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
