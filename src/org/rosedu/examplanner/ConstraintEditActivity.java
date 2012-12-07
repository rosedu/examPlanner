package org.rosedu.examplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ConstraintEditActivity extends Activity {
  public static final int GET_DATE = 0;

  private ArrayAdapter<String> adapter = null;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.constraint_edit);

    ListView listView = (ListView) findViewById(R.id.dateList);
    adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1, android.R.id.text1);
    listView.setAdapter(adapter);
  }

  public void selectDate(View v) {
    Intent intent = new Intent(this, ConstraintDateSelection.class);
    startActivityForResult(intent, GET_DATE);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
      case (GET_DATE): {
        if (resultCode == Activity.RESULT_OK) {
          String date = data.getStringExtra(ConstraintDateSelection.DATE);
          adapter.add(date);
        }
        break;
      }
    }
  }

  public void done(View v) {
    finish();
  }
}
