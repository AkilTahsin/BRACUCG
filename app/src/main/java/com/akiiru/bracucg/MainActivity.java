package com.akiiru.bracucg;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class MainActivity extends AppCompatActivity {
    private EditText oldCreditText, oldCGPAText, courseCreditText, courseGradeText;
    private Button enter, addCourse, calculate, reset;
    private TextView textView;

    double oldCGPA = 0.0;
    double oldCredit = 0.0;
    double semCGPA = 0.0;
    double courseGrade = 0.0;
    double courseCredit = 0.0;
    double semCredits = 0.0;
    double temp = 0.0;

    double totalCredit = 0.0;
    double newCGPA = 0.0;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDialog = new Dialog(this);

        // EditText ID connection
        oldCreditText = findViewById(R.id.oldCreditText);
        oldCGPAText = findViewById(R.id.oldCGPAText);
        courseCreditText = findViewById(R.id.courseCreditText);
        courseGradeText = findViewById(R.id.courseGradeText);

        // Button ID connection
        enter = findViewById(R.id.enter);
        addCourse = findViewById(R.id.addCourse);
        calculate = findViewById(R.id.calculate);
        reset = findViewById(R.id.reset);
        textView = findViewById(R.id.textView);

        // Defining Button click operations
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(oldCreditText.getText().toString()).isEmpty() && !(oldCGPAText.getText().toString()).isEmpty()) {
                    oldCredit = Double.parseDouble(oldCreditText.getText().toString());
                    oldCGPA = Double.parseDouble(oldCGPAText.getText().toString());

                    Toast.makeText(getApplicationContext(),"Previous CGPA : "+oldCGPA+"\nPrevious Credits : "+oldCredit,Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Kindly enter value!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(courseCreditText.getText().toString()).isEmpty() && !(courseGradeText.getText().toString()).isEmpty()) {
                    courseCredit = Double.parseDouble(courseCreditText.getText().toString());
                    courseGrade = Double.parseDouble(courseGradeText.getText().toString());

                    temp += (courseGrade * courseCredit);
                    semCredits+= courseCredit;
                    Toast.makeText(getApplicationContext(),"Course Grade : "+courseGrade+"\nCourse Credits : "+courseCredit,Toast.LENGTH_LONG).show();
                    courseCreditText.setText("");
                    courseGradeText.setText("");
                    semCGPA = temp/semCredits;
                    semCGPA = Math.round(semCGPA * 100.0) / 100.0;
                    Toast.makeText(getApplicationContext(),"Semester CGPA : "+semCGPA,Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Kindly enter value!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalCredit = oldCredit + semCredits;
                newCGPA = ((oldCGPA*oldCredit) + (semCGPA*semCredits))/totalCredit;
                newCGPA = Math.round(newCGPA * 100.0) / 100.0;
                textView.setText(""+newCGPA);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldCGPA = 0.0;
                oldCredit = 0.0;
                semCGPA = 0.0;
                courseGrade = 0.0;
                courseCredit = 0.0;
                semCredits = 0.0;
                temp = 0.0;

                totalCredit = 0.0;
                newCGPA = 0.0;
                oldCGPAText.setText("");
                oldCreditText.setText("");
                courseCreditText.setText("");
                courseGradeText.setText("");
                textView.setText("");
            }
        });

    }

    public void ShowPopup(View v) {
        TextView txtclose;
        Button help;
        myDialog.setContentView(R.layout.custompopup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        help = (Button) myDialog.findViewById(R.id.help);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}