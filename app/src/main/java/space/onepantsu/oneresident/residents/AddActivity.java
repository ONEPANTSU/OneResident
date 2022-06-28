package space.onepantsu.oneresident.residents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import space.onepantsu.oneresident.R;
import space.onepantsu.oneresident.history.database.HistoryDB;
import space.onepantsu.oneresident.history.database.HistoryDBMS;
import space.onepantsu.oneresident.history.database.HistoryType;
import space.onepantsu.oneresident.payment.database.PaymentDB;
import space.onepantsu.oneresident.payment.database.PaymentDBMS;
import space.onepantsu.oneresident.payment.database.PaymentStatus;
import space.onepantsu.oneresident.residents.database.DBMS;
import space.onepantsu.oneresident.residents.database.DataBase;
import space.onepantsu.oneresident.dialogframe.AcceptButton;
import space.onepantsu.oneresident.dialogframe.AddResidentButton;
import space.onepantsu.oneresident.dialogframe.BackButtonFromAdd;
import space.onepantsu.oneresident.dialogframe.DialogFrame;
import space.onepantsu.oneresident.dialogframe.InfoButton;
import space.onepantsu.oneresident.service.AlarmReceiver;

public class AddActivity extends AppCompatActivity {

    private boolean isDataError;

    private boolean isDebt = false;

    private EditText addCity;
    private EditText addStreet;
    private EditText addHouse;
    private EditText addLevel;
    private EditText addFlat;
    private EditText addSurname;
    private EditText addName;
    private EditText addSecondName;
    private EditText addPhone;
    private EditText addDate;
    private EditText addPeriod;
    private EditText addPrice;
    private EditText addComment;

    private String city = "";
    private String street = "";
    private String house = "";
    private Integer level = null;
    private Integer flat = null;
    private String surname = "";
    private String name = "";
    private String secondName = "";
    private String phone = "";
    private String date = "";
    private Integer period = 30;
    private Integer price = null;
    private String comment = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addCity = findViewById(R.id.editTextAddCity);
        addStreet = findViewById(R.id.editTextAddStreet);
        addHouse = findViewById(R.id.editTextAddHouse);
        addLevel = findViewById(R.id.editTextAddLevel);
        addFlat = findViewById(R.id.editTextAddFlat);
        addSurname = findViewById(R.id.editTextAddSurname);
        addName = findViewById(R.id.editTextAddName);
        addSecondName = findViewById(R.id.editTextAddSecondName);
        addPhone = findViewById(R.id.editTextAddPhone);
        addDate = findViewById(R.id.editTextAddDate);
        addPeriod = findViewById(R.id.editTextAddPeriod);
        addPrice = findViewById(R.id.editTextAddPrice);
        addComment = findViewById(R.id.editTextAddComment);

    }

    public void acceptChanges(View view){
        AcceptButton dialogButton = new AddResidentButton(this);
        DialogFrame warning = new DialogFrame("Вы уверены, что хотите сохранить арендатора?",  "", dialogButton);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        warning.show(transaction, "dialog");
    }

    private void tryAgain(Error error) {
        String errorMessage;
        switch(error){
            case WRONG_STREET: errorMessage = "Поле \"Улица\" должно быть заполнено!"; break;
            case WRONG_HOUSE: errorMessage = "Поле \"Дом\" должно быть заполнено!"; break;
            case WRONG_SURNAME: errorMessage = "Поле \"Фамилия\" арендатора должно быть заполнено!"; break;
            case WRONG_NAME: errorMessage = "Поле \"Имя\" арендатора должно быть заполнено!"; break;
            case WRONG_DATE: errorMessage = "Поле \"Дата\" должно быть заполнено!"; break;
            case WRONG_DATE_FORMAT: errorMessage = "Неверный формат поля \"Дата!\""; break;
            case WRONG_PRICE: errorMessage = "Поле \"Стоимость арендной платы\" должно быть заполнено!"; break;
            default: errorMessage = "Ошибка при добавлении арендатора!"; break;
           }

        InfoButton dialogButton = new InfoButton();
        DialogFrame warning = new DialogFrame("Ошибка!",  errorMessage, dialogButton);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        warning.show(transaction, "dialog");

        isDataError = true;
    }

    public void addResident() {

        isDataError = false;

        int day = 0;
        int month = 0;
        int year = 0;

        try {
            city = addCity.getText().toString();
        }
        catch (Exception ignored){}
        try {
            street = addStreet.getText().toString();
             if (street.equals("")){
                throw new Exception();
            }
            try {
                house = addHouse.getText().toString();
                if (house.equals("")){
                    throw new Exception();
                }
                try {
                    level = Integer.parseInt(addLevel.getText().toString());
                }
                catch (Exception ignored){}
                try {
                    flat = Integer.parseInt(addFlat.getText().toString());
                }
                catch (Exception ignored){}
                try {
                    surname = addSurname.getText().toString();
                    if (surname.equals("")){
                        throw new Exception();
                    }
                    try {
                        name = addName.getText().toString();
                        if (name.equals("")){
                            throw new Exception();
                        }
                        try {
                            secondName = addSecondName.getText().toString();
                        }
                        catch (Exception ignored){}
                        try {
                            phone = addPhone.getText().toString();
                        }
                        catch (Exception ignored){}
                        try{
                            String stringDate = addDate.getText().toString();
                            if(stringDate.equals("")){
                                tryAgain(Error.WRONG_DATE);
                            }
                            else if (stringDate.charAt(2) != '.' || stringDate.charAt(5) != '.'
                                    || stringDate.length() != 10){
                                throw new Exception();
                            }
                            else {
                                try{
                                    day = (int) stringDate.charAt(0) * 10 + (int) stringDate.charAt(1) - 528;
                                    month = (int) stringDate.charAt(3) * 10 + (int) stringDate.charAt(4) - 528;
                                    year = (int) stringDate.charAt(6) * 1000 + (int) stringDate.charAt(7) * 100
                                            + (int) stringDate.charAt(8) * 10 + (int) stringDate.charAt(9) - 53328;
                                    if(day > 31 || day < 1 || month > 12 || month < 1){
                                        throw new Exception();
                                    }
                                }
                                catch (Exception e){
                                    tryAgain(Error.WRONG_DATE_FORMAT);
                                }

                                date = stringDate;

                                try{
                                    period = Integer.parseInt(addPeriod.getText().toString());
                                    if (period <= 0){
                                        throw new Exception();
                                    }
                                }
                                catch (Exception e){
                                    period = 30;
                                }
                                try {
                                    price = Integer.parseInt(addPrice.getText().toString());
                                }
                                catch (Exception e){
                                    tryAgain(Error.WRONG_PRICE);
                                }
                                try {
                                    comment = addComment.getText().toString();
                                }
                                catch (Exception ignored){}
                            }
                        }
                        catch (Exception e){
                            tryAgain(Error.WRONG_DATE_FORMAT);
                        }
                    }
                    catch (Exception e){
                        tryAgain(Error.WRONG_NAME);
                    }

                }
                catch (Exception e){
                    tryAgain(Error.WRONG_SURNAME);
                }

            }
            catch(Exception e){
                tryAgain(Error.WRONG_HOUSE);
            }
        }
        catch (Exception e){
            tryAgain(Error.WRONG_STREET);
        }

        if(!isDataError){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.set(Calendar.HOUR_OF_DAY, 12);
            calendar.set(Calendar.MINUTE, 0);

            Calendar current = Calendar.getInstance();
            current.setTimeInMillis(System.currentTimeMillis());
            if(calendar.compareTo(current) <= 0){
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.roll(Calendar.SECOND, 10);
                isDebt = true;
            }

            startAlarm(calendar.getTimeInMillis());


            addToDB();
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private void startAlarm(long startTime){

        Log.i("ALARM", "Start Alarm");
        AlarmManager alarmManager;

        PendingIntent alarmIntent;

        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        intent.putExtra("text", "Узнайте, кто должен внести оплату!");
        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), (int)startTime, intent, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, startTime, alarmIntent);
    }


    private void addToPaymentDB(int id){
        PaymentDBMS dbms = new PaymentDBMS(this);
        SQLiteDatabase db = dbms.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PaymentDB.PaymentTable._ID, id);
        values.put(PaymentDB.PaymentTable.STATUS, String.valueOf(PaymentStatus.NOT_PAID));

        if (isDebt){
            values.put(PaymentDB.PaymentTable.DEBT, 1);
        }
        else {
            values.put(PaymentDB.PaymentTable.DEBT, 0);
        }

        db.insert(PaymentDB.PaymentTable.TABLE_NAME, null, values);
    }

    private void addToHistoryDB(int id){
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
        values.put(HistoryDB.HistoryTable.TYPE, String.valueOf(HistoryType.ADDED_RESIDENT));

        db.insert(HistoryDB.HistoryTable.TABLE_NAME, null, values);
    }

    public void addToDB(){
        System.out.println("Добавление в Базу Данных...");
        DBMS dbms = new DBMS(this);
        SQLiteDatabase db = dbms.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBase.ResidentsTable.COLUMN_CITY, city);
        values.put(DataBase.ResidentsTable.COLUMN_STREET, street);
        values.put(DataBase.ResidentsTable.COLUMN_HOUSE, house);
        values.put(DataBase.ResidentsTable.COLUMN_LEVEL, level);
        values.put(DataBase.ResidentsTable.COLUMN_FLAT, flat);
        values.put(DataBase.ResidentsTable.COLUMN_SURNAME, surname);
        values.put(DataBase.ResidentsTable.COLUMN_NAME, name);
        values.put(DataBase.ResidentsTable.COLUMN_SECONDNAME, secondName);
        values.put(DataBase.ResidentsTable.COLUMN_PHONE, phone);
        values.put(DataBase.ResidentsTable.COLUMN_DATE, date);
        values.put(DataBase.ResidentsTable.COLUMN_PERIOD, period);
        values.put(DataBase.ResidentsTable.COLUMN_PRICE, price);
        values.put(DataBase.ResidentsTable.COLUMN_COMMENT, comment);

        long newRowId = db.insert(DataBase.ResidentsTable.TABLE_NAME, null, values);

        if (newRowId == -1) {
            // Если ID  -1, значит произошла ошибка
            Toast.makeText(this, "Ошибка при добавлении арендатора", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Добавление произошло успешно!", Toast.LENGTH_SHORT).show();

            String selectQuery = "SELECT  * FROM " + DataBase.ResidentsTable.TABLE_NAME;
            Cursor cursor = db.rawQuery(selectQuery, null);
            cursor.moveToLast();
            @SuppressLint("Range") int residentID = cursor.getInt(cursor.getColumnIndex(DataBase.ResidentsTable._ID));
            cursor.close();
            addToPaymentDB(residentID);

            addToHistoryDB(residentID);

            back();
        }

    }

    private boolean allIsNotEmpty(){
        try {
            city = addCity.getText().toString();
        }
        catch (Exception ignored){}
        try {
            street = addStreet.getText().toString();
            try {
                house = addHouse.getText().toString();
                try {
                    level = Integer.parseInt(addLevel.getText().toString());
                }
                catch (Exception ignored){}
                try {
                    flat = Integer.parseInt(addFlat.getText().toString());
                }
                catch (Exception ignored){}
                try {
                    surname = addSurname.getText().toString();
                    try {
                        name = addName.getText().toString();
                        try {
                            secondName = addSecondName.getText().toString();
                        }
                        catch (Exception ignored){}
                        try {
                            phone = addPhone.getText().toString();
                        }
                        catch (Exception ignored){}
                        try{
                            date = addDate.getText().toString();
                                try{
                                    period = Integer.parseInt(addPeriod.getText().toString());
                                }
                                catch (Exception ignored){}
                                try {
                                    price = Integer.parseInt(addPrice.getText().toString());
                                }
                                catch (Exception ignored){}
                                try {
                                    comment = addComment.getText().toString();
                                }
                                catch (Exception ignored){}
                        }
                        catch (Exception ignored){}
                    }
                    catch (Exception ignored){}
                }
                catch (Exception ignored){}
            }
            catch(Exception ignored){}
        }
        catch (Exception ignored){}

        return !city.equals("") || !street.equals("") || !house.equals("") || level != null ||
                flat != null || !surname.equals("") || !name.equals("") || !secondName.equals("") ||
                !phone.equals("") || !date.equals("") || period != 30 || price != null || !comment.equals("");
    }

    public void goBack(View view){
        if(allIsNotEmpty()){
            AcceptButton dialogButton = new BackButtonFromAdd(this);
            DialogFrame warning = new DialogFrame("Вы уверены, что хотите вернуться?",
                    "Все изменения будут отменены.", dialogButton);
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            warning.show(transaction, "dialog");
        }
        else{
            back();
        }
    }

    public void back(){
        Intent intent = new Intent(this, ResidentActivity.class);
        startActivity(intent);
        closeActivity();
    }

    private void closeActivity() {
        this.finish();
    }

}