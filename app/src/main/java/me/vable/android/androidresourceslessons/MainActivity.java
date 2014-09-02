package me.vable.android.androidresourceslessons;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import me.vable.android.androidresourceslessons.data.ActivityData;
import me.vable.android.androidresourceslessons.data.adapter.ActivityDataListAdapter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//set the activity_main.xml as screen content


        List<ActivityData> activityDataList = new ArrayList<ActivityData>();//List of the ActivityData instance, put you ActivityData here
        activityDataList.add(new ActivityData("Values",ValuesExampleActivity.class));
        activityDataList.add(new ActivityData("Menu",MenuExampleActivity.class));
        activityDataList.add(new ActivityData("Drawable",DrawableExampleActivity.class));
        activityDataList.add(new ActivityData("Alternative Resource (String on landscape)",AlternativeResourcesExampleActivity.class));
        activityDataList.add(new ActivityData("Alternative Drawable Resource",AlternativeDrawableExampleActivity.class));
        activityDataList.add(new ActivityData("Alternative Drawable Resource 2",AlternativeDrawableExample2Activity.class));
        activityDataList.add(new ActivityData("Alternative Drawable Resource 3",AlternativeDrawableExample3Activity.class));


        ActivityDataListAdapter activityDataListAdapter = new ActivityDataListAdapter(this,activityDataList);//create the Data Adapter for ListView

        ListView listView = (ListView)findViewById(android.R.id.list);//get the instance of ListView that has an id @android:id/list
        listView.setAdapter(activityDataListAdapter);//set the ListView to consume the data from Adapter
        listView.setOnItemClickListener(onItemClickListener);//set click event action
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ActivityData activityData = (ActivityData)adapterView.getAdapter().getItem(i);//get the selected item from the Adapter
            if(activityData.getActivityClass()!=null)
            {
                try{
                    Intent intent = new Intent(MainActivity.this, activityData.getActivityClass());//create the intent for start the new Activity
                    startActivity(intent);//start the new Activity
                }
                catch(ActivityNotFoundException e)//Activity not found or the class that you provide is not an Activity
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);//Create alert dialog builder
                    builder.setIcon(R.drawable.ic_error);//set dialog icon to drawable resource
                    builder.setTitle(R.string.error_dialog_title);//set dialog title
                    builder.setMessage(
                            String.format(
                                    getString(R.string.error_dialog_activity_not_found_message),
                                    activityData.getActivityClass().getSimpleName()
                            )
                    );//set dialog message
                    builder.setPositiveButton(android.R.string.ok,null);//set positive button title and action
                    builder.show();//show the dialog
                }
            }
        }
    };
}
