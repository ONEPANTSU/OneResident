package space.onepantsu.oneresident.residents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.io.Serializable;
import java.util.Calendar;

import space.onepantsu.oneresident.MainActivity;
import space.onepantsu.oneresident.R;
import space.onepantsu.oneresident.history.database.HistoryDB;
import space.onepantsu.oneresident.history.database.HistoryDBMS;
import space.onepantsu.oneresident.history.database.HistoryType;
import space.onepantsu.oneresident.payment.database.PaymentDB;
import space.onepantsu.oneresident.payment.database.PaymentDBMS;
import space.onepantsu.oneresident.residents.database.DBMS;
import space.onepantsu.oneresident.residents.database.DataBase;
import space.onepantsu.oneresident.dialogframe.AcceptButton;
import space.onepantsu.oneresident.dialogframe.DeleteResidentButton;
import space.onepantsu.oneresident.dialogframe.DialogFrame;

public class ResidentActivity extends AppCompatActivity {

    LinearLayout linear;

    DBMS dbms = new DBMS(this);
    PaymentDBMS paymentDBMS = new PaymentDBMS(this);

    public static class ResidentInfo implements Serializable {
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
        linear = findViewById(R.id.residentLinear);
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

        @SuppressLint("Recycle") Cursor cursor = db.query(
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
                newResident.currentComment = cursor.getString(commentColumnIndex);

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
        Button deleteField = view.findViewById(R.id.residentDeleteButton);

        deleteField.setOnClickListener(v -> acceptDeleting(newResident, v));

        Button residentInfoBttn = view.findViewById(R.id.residentInfo);
        StringBuilder residentInfoBttnTextBuilder = new StringBuilder();


        int maxLength = 20;

        if(!newResident.currentStreet.equals("")){
            residentInfoBttnTextBuilder.append("ул.").append(newResident.currentStreet);

            int currentLenght = residentInfoBttnTextBuilder.toString().length();

            if(currentLenght > maxLength){
                residentInfoBttnTextBuilder.delete(maxLength-3, currentLenght-1);
                residentInfoBttnTextBuilder.append("...");
            }
            residentInfoBttnTextBuilder.append("\n");
        }
        if(!newResident.currentHouse.equals("")){
            residentInfoBttnTextBuilder.append("д.").append(newResident.currentHouse);
        }
        if(newResident.currentFlat > 0){
            residentInfoBttnTextBuilder.append(",\t кв.").append(newResident.currentFlat);
        }
        if(!newResident.currentDate.equals("")){
            residentInfoBttnTextBuilder.append("\n");
            residentInfoBttnTextBuilder.append(newResident.currentDate);
        }

        residentInfoBttn.setText(residentInfoBttnTextBuilder.toString());

        residentInfoBttn.setOnClickListener(view1 -> {
            Intent intent = new Intent(ResidentActivity.this, ResidentInfoActivity.class);
            intent.putExtra(ResidentInfo.class.getSimpleName(), newResident);
            intent.putExtra("FROM", "ResidentActivity");
            startActivity(intent);
            closeActivity();
        });


        linear.addView(view);
    }

    public void acceptDeleting(ResidentInfo newResident, View v){
        AcceptButton dialogButton = new DeleteResidentButton(this, newResident, v);
        DialogFrame warning = new DialogFrame("Вы уверены, что хотите удалить арендатора?",  "", dialogButton);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        warning.show(transaction, "dialog");
    }

    private void deletePayment(ResidentInfo resident){
        try {
            SQLiteDatabase db = paymentDBMS.getWritableDatabase();
            db.delete(PaymentDB.PaymentTable.TABLE_NAME,
                    PaymentDB.PaymentTable._ID + "=" + resident.currentID, null);
        }
        catch(IndexOutOfBoundsException ex) {
            ex.printStackTrace();
            Toast.makeText(ResidentActivity.this, "Ошибка при удалении арендатора", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteResident(View view, ResidentInfo newResident){
        try {
            ((RelativeLayout) view.getParent()).removeView(view);
            SQLiteDatabase db = dbms.getWritableDatabase();

            db.execSQL("DELETE FROM " + DataBase.ResidentsTable.TABLE_NAME + " WHERE "
                    + DataBase.ResidentsTable._ID + " = " + newResident.currentID);
            deletePayment(newResident);
            Toast.makeText(ResidentActivity.this, "Арендатор успешно удалён", Toast.LENGTH_SHORT).show();
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }
        catch(IndexOutOfBoundsException ex) {
            ex.printStackTrace();
            Toast.makeText(ResidentActivity.this, "Ошибка при удалении арендатора", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteToHistoryDB(int id, String name, String surname){
        HistoryDBMS dbms = new HistoryDBMS(this);
        SQLiteDatabase db = dbms.getWritableDatabase();
        ContentValues values = new ContentValues();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        String month;
        if(calendar.get(Calendar.MONTH) + 1 < 10) {
            month = "0" + (calendar.get(Calendar.MONTH) + 1);
        }
        else{
            month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        }
        String day;
        if(calendar.get(Calendar.DATE) < 10) {
            day = "0" + calendar.get(Calendar.DATE);
        }
        else {
            day = String.valueOf(calendar.get(Calendar.DATE));
        }
        String date = day + "." + month + "." + calendar.get(Calendar.YEAR);

        values.put(HistoryDB.HistoryTable.DATE, date);
        values.put(HistoryDB.HistoryTable.RESIDENT_ID, id);
        values.put(HistoryDB.HistoryTable.RESIDENT_NAME, name);
        values.put(HistoryDB.HistoryTable.RESIDENT_SURNAME, surname);
        values.put(HistoryDB.HistoryTable.TYPE, String.valueOf(HistoryType.DELETED_RESIDENT));

        db.insert(HistoryDB.HistoryTable.TABLE_NAME, null, values);
    }

    public void onAddActivity(View view){
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
        closeActivity();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
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