package com.example.database;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import Model.Employee;
import data.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        DatabaseHandler  databaseHandler = new DatabaseHandler( this);

     /*
        databaseHandler.addEmp(new Employee( "Kate", "Ivanova"));
        databaseHandler.addEmp(new Employee( "Jane", "Petrova"));
        databaseHandler.addEmp(new Employee( "Evelina", "Sidorova"));
        databaseHandler.addEmp(new Employee( "Ivan", "Smirnov"));
*/
        List<Employee> employeeList = databaseHandler.getAllEmp();

         for (Employee employee : employeeList){
             Log.d("Employee info:", "ID "+ employee.getId() + " , Name - " + employee.getName() + ", Surname - " + employee.getSurname());
         }
        /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
         Employee employee = databaseHandler.getEmp(3);
         employee.setSurname("Kusnezova");
         databaseHandler.updateEmp(employee);
         Log.d("Employee info:", "ID "+ employee.getId() + " , Name - " + employee.getName()
                 + ", Surname - " + employee.getSurname());
    }
}