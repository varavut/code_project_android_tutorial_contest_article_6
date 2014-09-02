package me.vable.android.androidresourceslessons;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Varavut on 8/24/2014.
 */
public class ValuesExampleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_values_example);

        String[] colors = getResources().getStringArray(R.array.string_activity_value_example_spinner_color_options);

        Spinner colorSpinner = (Spinner)findViewById(R.id.spinner_color);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        colorSpinner.setAdapter(adapter);
    }
}
