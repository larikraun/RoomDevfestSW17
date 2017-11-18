package me.larikraun.room_devfestsw

import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import me.larikraun.room_devfestsw.data.Attendee

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        MyAsynTask().execute()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    class MyAsynTask : AsyncTask<Any, Any, ArrayList<Attendee>>() {

        override fun doInBackground(vararg p0: Any?): ArrayList<Attendee> {
            val attendees = ArrayList(RoomApp.database.attendeeDao().getAllAttendees())

            if (attendees.isEmpty()) {
                val attendee = Attendee(1, "Omolara Adejuwon", "omolara.adejuwon", "Speaker",null)
                RoomApp.database.attendeeDao().insert(attendee)
                return ArrayList(RoomApp.database.attendeeDao().getAllAttendees())
            }
            return attendees
        }

        override fun onPostExecute(result: ArrayList<Attendee>) {
            super.onPostExecute(result)
            //TODO Do whatever thing you want to do with the result
            Log.d("Result", result.toString())
        }
    }
}
