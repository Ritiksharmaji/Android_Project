package com.example.volley_with_array_object_json_example_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    public StudentAdapter(Context context, List<Student> students) {
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Student student = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_student, parent, false);
        }

        // Lookup view for data population
        TextView nameTextView = convertView.findViewById(R.id.student_name);
        TextView ageTextView = convertView.findViewById(R.id.student_age);
        TextView courseTextView = convertView.findViewById(R.id.student_course);

        // Populate the data into the template view using the data object
        nameTextView.setText(student.getName());
        ageTextView.setText(String.valueOf(student.getAge()));
        courseTextView.setText(student.getCourse());

        // Return the completed view to render on screen
        return convertView;
    }

}
