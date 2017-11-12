package me.larikraun.room_devfestsw;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import me.larikraun.room_devfestsw.data.Attendee;

class MainActivity extends AppCompatActivity {
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_main);
		Toolbar toolbar = findViewById (R.id.toolbar);
		setSupportActionBar (toolbar);
		new MyAsynTask ().execute ();
		findViewById (R.id.fab).setOnClickListener (new View.OnClickListener () {
			@Override
			public void onClick (View view) {
				Snackbar.make (view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction ("Action", null).show ();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		MenuInflater menuInflater = new MenuInflater (this);
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate (R.menu.menu_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected (MenuItem item) {
		int id = item.getItemId ();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected (item);
	}
	
	class MyAsynTask extends AsyncTask<Void, Void, List<Attendee>> {
		@Override
		protected void onPostExecute (List<Attendee> result) {
			super.onPostExecute (result);
			//TODO Do whatever thing you want to do with the result
			Log.d ("Result", result.toString ());
		}
		
		@Override
		protected List<Attendee> doInBackground (Void... voids) {
			
			List<Attendee> attendees = RoomApp.database.attendeeDao ().getAllAttendees ();
			if (!attendees.isEmpty ()) {
				Attendee attendee = new Attendee (1, "Omolara 'java' Adejuwon", "omolara.adejuwon", "Speaker");
				RoomApp.database.attendeeDao ().insert (attendee);
				return RoomApp.database.attendeeDao ().getAllAttendees ();
			}
			return attendees;
		}
	}
}
