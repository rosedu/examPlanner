package org.rosedu.examplanner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplaySolutionActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_solution);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        
        SolutionAdapter adapter = new SolutionAdapter(this);
        
        adapter.loadData(); // This is just for test now. It is supposed to
        					// load the groups and data from somewhere.
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(DisplaySolutionActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_display_solution, menu);
        return true;
    }
}

//TODO: setters and getters
class Item {
	public String group;
	public String date;
	public String subject;
}

class SolutionAdapter extends BaseAdapter {
    private Context mContext;
    private String series = "CB";
    private ArrayList<Item> items;
    
    public SolutionAdapter(Context c) {
        mContext = c;
        items = new ArrayList<Item>();
    }

    public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(85, 50));
            textView.setPadding(8, 8, 8, 8);
        } else {
            textView = (TextView) convertView;
        }
        
        	// Is a group (first row)
        if (position < 5) {
        	textView.setText(Html.fromHtml("<big><b>" + items.get(position).group +
        			series + "</big></b>"));
        }
        else {
        	//textView.setText(items.get(position).date + "\n" + items.get(position).subject);
        	//textView.setTextColor(getColor(items.get(position)));
        	textView.setText(Html.fromHtml("<font color=\"#0000ff\"><b>" 
        			+ items.get(position).date + "</font></b>" + "<br/>"
        			+ "<big><b>" + "<font color=\"#ff6600\">" +  
        			items.get(position).subject + "</big></b></font>"));
        }
        return textView;
    }
    
    public void setSeries(String series) {
    	this.series = series;
    }
    
    public void loadData() {
      // TODO: Load dates, groups and subjects from some file or database or server.
      // Below is just for test.
    	for (int i = 1; i <= 5; i++) {
    		Item it = new Item();
    		it.group = "31" + String.valueOf(i);
    		items.add(it);
    	}
    	
      SimpleDateFormat dateformat = new SimpleDateFormat("dd MMM");
      Calendar cal = Calendar.getInstance();
      
      for (int i = 0; i < 25; i++) {
      	cal.add(Calendar.DATE, 1);
      	Item it = new Item();
      	it.date = dateformat.format(cal.getTime());
      	
      	if (i % 4 == 0)
      		it.subject="EEA";
      	else if (i % 5 == 0)
      		it.subject="TS";
      	else if (i % 6 == 0)
      		it.subject="POO";
      	else if (i % 7 == 0)
      		it.subject="AA";
      	else
      		it.subject="IOCLA";
      	items.add(it);
      }
      series = "CB";
    }
    
    // Returns a color according to item's subject
    private int getColor(Item item) {
    	ArrayList<String> subjects = new ArrayList<String>();
    	
    	for (Item it : items) {
    		if (!subjects.contains(it.subject)) 
    			subjects.add(it.subject);
    	}
    	
    	switch(subjects.indexOf(item.subject)) {
    	case 1: 
    		return Color.BLUE;
    	case 2:
    		return Color.CYAN;
    	case 3:
    		return Color.RED;
    	case 4:
    		return Color.GREEN;
    	case 5:
    		return Color.MAGENTA;
    	default:
    		return Color.BLACK;
    	}
    }
}