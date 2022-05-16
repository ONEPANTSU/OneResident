package space.onepantsu.oneresident;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Struct;

import space.onepantsu.oneresident.database.DBMS;
import space.onepantsu.oneresident.database.DataBase;

public class ResidentActivity extends AppCompatActivity {

    LinearLayout linear;

    DBMS dbms = new DBMS(this);

    protected static class ResidentInfo{
        public int currentID;
        public String currentCity;
        public String currentStreet;
        public String currentHouse;
        public Integer currentLevel;
        public Integer currentFlat;
        public String currentSurname;
        public String currentName;
        public String currentSecondname;
        public String currentPhone;
        public String currentDate;
        public Integer currentPeriod;
        public Integer currentPrice;
        public String currentComment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident);
        linear = (LinearLayout) findViewById(R.id.residentLinear);
        checkResidents();
    }

    public void checkResidents(){
        SQLiteDatabase db = dbms.getReadableDatabase();

        String[] projection = {
                DataBase.ResidentsTable._ID, DataBase.ResidentsTable.COLUMN_CITY,
                DataBase.ResidentsTable.COLUMN_STREET, DataBase.ResidentsTable.COLUMN_HOUSE,
                DataBase.ResidentsTable.COLUMN_LEVEL, DataBase.ResidentsTable.COLUMN_FLAT,
                DataBase.ResidentsTable.COLUMN_SURNAME, DataBase.ResidentsTable.COLUMN_NAME,
                DataBase.ResidentsTable.COLUMN_SECONDNAME, DataBase.ResidentsTable.COLUMN_PHONE,
                DataBase.ResidentsTable.COLUMN_DATE, DataBase.ResidentsTable.COLUMN_PERIOD,
                DataBase.ResidentsTable.COLUMN_PRICE, DataBase.ResidentsTable.COLUMN_COMMENT};

        Cursor cursor = db.query(
                DataBase.ResidentsTable.TABLE_NAME,   // таблица
                projection,            // столбцы
                null,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // порядок сортировки

        int idColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable._ID);
        int cityColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_CITY);
        int streetColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_STREET);
        int houseColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_HOUSE);
        int levelColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_LEVEL);
        int flatColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_FLAT);
        int surnameColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_SURNAME);
        int nameColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_NAME);
        int secondnameColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_SECONDNAME);
        int phoneColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_PHONE);
        int dateColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_DATE);
        int periodColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_PERIOD);
        int priceColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_PRICE);
        int commentColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_COMMENT);


        while (cursor.moveToNext()) {
            try {
                ResidentInfo newResident = new ResidentInfo();

                newResident.currentID = cursor.getInt(idColumnIndex);
                newResident.currentCity = cursor.getString(cityColumnIndex);
                newResident.currentStreet = cursor.getString(streetColumnIndex);
                newResident.currentHouse = cursor.getString(houseColumnIndex);
                newResident.currentLevel = cursor.getInt(levelColumnIndex);
                newResident.currentFlat = cursor.getInt(flatColumnIndex);
                newResident.currentSurname = cursor.getString(surnameColumnIndex);
                newResident.currentName = cursor.getString(nameColumnIndex);
                newResident.currentSecondname = cursor.getString(secondnameColumnIndex);
                newResident.currentPhone = cursor.getString(phoneColumnIndex);
                newResident.currentDate = cursor.getString(dateColumnIndex);
                newResident.currentPeriod = cursor.getInt(periodColumnIndex);
                newResident.currentPrice = cursor.getInt(priceColumnIndex);
                newResident.currentComment = cursor.getString(dateColumnIndex);

                addResidentsView(newResident);

            }
            catch (Exception e){
                System.out.println("Ошибка при чтении строки");
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private void addResidentsView(ResidentInfo newResident){
        @SuppressLint("InflateParams") final View view = getLayoutInflater().inflate(R.layout.custom_resident_layout, null);
        Button deleteField = (Button) view.findViewById(R.id.residentDeleteButton);

        deleteField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ((LinearLayout) view.getParent()).removeView(view);
                    SQLiteDatabase db = dbms.getWritableDatabase();
                    db.execSQL("DELETE FROM " + DataBase.ResidentsTable.TABLE_NAME + " WHERE "
                            + DataBase.ResidentsTable._ID + " = " + newResident.currentID);

                } catch(IndexOutOfBoundsException ex) {
                    ex.printStackTrace();
                }
            }
        });

        TextView text = (TextView) view.findViewById(R.id.residentTextView);
        text.setText(newResident.currentID + "\t" + newResident.currentCity + "\t" + newResident.currentStreet + "\t" +
                newResident.currentHouse + "\t" + newResident.currentLevel + "\t" + newResident.currentFlat + "\n" +
                newResident.currentSurname + "\t" + newResident.currentName + "\t" + newResident.currentSecondname + "\n" +
                newResident.currentPhone + "\n" + newResident.currentDate + "\t" + newResident.currentPeriod + "\n" +
                newResident.currentPrice + "\n" + newResident.currentComment);

        linear.addView(view);
    }

    public void onAddActivity(View view){
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
        closeActivity();
    }

    public void goBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        closeActivity();
    }

    private void closeActivity() {
        this.finish();
    }
}