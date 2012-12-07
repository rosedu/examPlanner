package org.rosedu.examplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class ConstraintDateSelection extends Activity {
  public static final String DATE = "DATE";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.date_select);
  }

  public void setDate(View v) {
    DatePicker picker = (DatePicker) findViewById(R.id.datePicker);

    // TODO: proper date processing
    String value = picker.getDayOfMonth() + "." + (picker.getMonth() + 1) + "."
        + picker.getYear();
    Intent returnData = new Intent();
    returnData.putExtra(ConstraintDateSelection.DATE, value);
    setResult(RESULT_OK, returnData);
    finish();
  }
}
