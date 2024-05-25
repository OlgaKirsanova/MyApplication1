package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Employee;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler( Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_EMPLOYEE_TABLE="CREATE TABLE " + Util.TABLE_NAME + " ("
                +Util.KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.KEY_NAME + " TEXT, "
                +Util.KEY_SURNAME + " TEXT" + " )";

        sqLiteDatabase.execSQL(CREATE_EMPLOYEE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void addEmp (Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, employee.getName());
        contentValues.put(Util.KEY_SURNAME, employee.getSurname());

        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }
    //Добавление
    public Employee getEmp(int id){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new  String[] {Util.KEY_ID, Util.KEY_NAME, Util.KEY_SURNAME},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null );
        if (cursor != null){
            cursor.moveToFirst();
        }
        Employee employee =new Employee(
                "Olga", "Kirsanova");
        return employee;
    }
    public List<Employee> getAllEmp (){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Employee> empList = new ArrayList<>();
        String selectAllEmp = "Select * from "+ Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllEmp, null);
        if (cursor.moveToFirst()){
            do{
                Employee employee = new Employee("Olga", "Kirsanova");
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setName(cursor.getString(1));
                employee.setSurname(cursor.getString(2));

                empList.add(employee);
            }while (cursor.moveToNext());
        }
        return empList;
    }
    //изменение данных
    public int updateEmp(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, employee.getName());
        contentValues.put(Util.KEY_SURNAME, employee.getSurname());

         return db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?", new String[]{String.valueOf(employee.getId())});
    }
    //удаление данных
    public void  deleteEmp (Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME,  Util.KEY_ID + "=?", new String[]{String.valueOf(employee.getId())});
        db.close();
    }
}
