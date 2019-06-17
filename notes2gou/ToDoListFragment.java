package com.ivanfrescas.notes2gou;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ivanfrescas.notes2gou.data.TaskContract;
import com.ivanfrescas.notes2gou.data.TaskDBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */

public class ToDoListFragment extends Fragment {


    TaskAdapter mTaskAdapter;

    // These indices are tied to TASKS_COLUMNS.  If TASKS_COLUMNS changes, these must change.
    static final int COL_TASK_ID = 0;
    static final int COL_TASK_NAME = 1;

    public ToDoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_to_do_list, container, false);

        //Find the listView
        ListView listView = (ListView) rootView.findViewById(R.id.listview_tasks);

        //Get DBHelper to read from database
        TaskDBHelper helper = new TaskDBHelper(getActivity());
        SQLiteDatabase sqlDB = helper.getReadableDatabase();

        //Query database to get any existing data
        Cursor cursor = sqlDB.query(TaskContract.TaskEntry.TABLE_NAME,
                new String[]{TaskContract.TaskEntry._ID, TaskContract.TaskEntry.COLUMN_TASK},
                null, null, null, null, null);

        //Create a new TaskAdapter and bind it to ListView
        mTaskAdapter = new TaskAdapter(getActivity(), cursor);
        listView.setAdapter(mTaskAdapter);

        return rootView;

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tasks_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_add_task:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Añadir Tarea");
                builder.setMessage("¿qué necesitas hacer?");
                final EditText inputField = new EditText(getActivity());
                builder.setView(inputField);
                builder.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Get user input
                        String inputTask = inputField.getText().toString();

                        //Get DBHelper to write to database
                        TaskDBHelper helper = new TaskDBHelper(getActivity());
                        SQLiteDatabase db = helper.getWritableDatabase();

                        //Put in the values within a ContentValues.
                        ContentValues values = new ContentValues();
                        values.clear();
                        values.put(TaskContract.TaskEntry.COLUMN_TASK, inputTask);

                        //Insert the values into the Table for Tasks
                        db.insertWithOnConflict(
                                TaskContract.TaskEntry.TABLE_NAME,
                                null,
                                values,
                                SQLiteDatabase.CONFLICT_IGNORE);

                        //Query database again to get updated data
                        Cursor cursor = db.query(TaskContract.TaskEntry.TABLE_NAME,
                                new String[]{TaskContract.TaskEntry._ID, TaskContract.TaskEntry.COLUMN_TASK},
                                null, null, null, null, null);

                        //Swap old data with new data for display
                        mTaskAdapter.swapCursor(cursor);
                    }
                });
                builder.setNegativeButton("Cancelar", null);
                builder.create().show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
