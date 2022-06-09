package space.onepantsu.oneresident.payment;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import space.onepantsu.oneresident.payment.database.PaymentDB;
import space.onepantsu.oneresident.payment.database.PaymentDBMS;
import space.onepantsu.oneresident.payment.database.PaymentStatus;
import space.onepantsu.oneresident.residents.database.DBMS;
import space.onepantsu.oneresident.residents.database.DataBase;

public class DebtSearcher {

    private Context context;

    public DebtSearcher(Context context){
        this.context = context;
    }

    public int checkDebtByPaymentInfo(PaymentActivity.PaymentInfo paymentInfo) throws ParseException {

        DBMS residentDBMS = new DBMS(context);
        SQLiteDatabase db = residentDBMS.getWritableDatabase();
        String[] projection = {DataBase.ResidentsTable._ID, DataBase.ResidentsTable.COLUMN_DATE,
                                DataBase.ResidentsTable.COLUMN_PERIOD};
        Cursor cursor = db.query(
                DataBase.ResidentsTable.TABLE_NAME,   // таблица
                projection,            // столбцы
                DataBase.ResidentsTable._ID + " = ?",                  // столбцы для условия WHERE
                new String[] {String.valueOf(paymentInfo.currentID)},                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);
        int dateColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_DATE);
        int periodColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_PERIOD);
        if(cursor.moveToNext()) {
            String residentsDate = cursor.getString(dateColumnIndex);
            int residentsPeriod = cursor.getInt(periodColumnIndex);
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            Calendar currentCalendar = Calendar.getInstance();
            Calendar paymentDay = new GregorianCalendar();
            paymentDay.setTime(format.parse(residentsDate));

            Calendar previousDay = paymentDay;
            paymentDay.roll(Calendar.DAY_OF_YEAR, residentsPeriod);

            if(previousDay.get(Calendar.MONTH) == Calendar.DECEMBER &&
                    paymentDay.get(Calendar.MONTH) == Calendar.JANUARY){
                paymentDay.roll(Calendar.YEAR, 1);
            }

            if(currentCalendar.get(Calendar.YEAR) == paymentDay.get(Calendar.YEAR) &&
                    currentCalendar.get(Calendar.MONTH) == paymentDay.get(Calendar.MONTH) &&
                    currentCalendar.get(Calendar.DAY_OF_MONTH) == paymentDay.get(Calendar.DAY_OF_MONTH)){

                return increaseDebt(paymentInfo);
            }
        }
        return paymentInfo.currentDebt;
    }

    @SuppressLint("Range")
    public int increaseDebt(PaymentActivity.PaymentInfo paymentInfo) throws ParseException {
        PaymentDBMS paymentDBMS = new PaymentDBMS(context);
        SQLiteDatabase db = paymentDBMS.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        int debt = 0;
        String selectQuery = "SELECT  * FROM " + PaymentDB.PaymentTable.TABLE_NAME +
                " WHERE " + PaymentDB.PaymentTable._ID + " = " + paymentInfo.currentID;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToNext()) {
            debt = cursor.getInt(cursor.getColumnIndex(PaymentDB.PaymentTable.DEBT));
            debt += 1;
            newValues.put(PaymentDB.PaymentTable.DEBT, debt);
            @SuppressLint("Range") String status = cursor.getString(cursor.getColumnIndex(PaymentDB.PaymentTable.STATUS));
            if (status.equals(String.valueOf(PaymentStatus.PAID))) {
                newValues.put(PaymentDB.PaymentTable.STATUS, String.valueOf(PaymentStatus.NOT_PAID));
            }
            String where = PaymentDB.PaymentTable._ID + "=" + paymentInfo.currentID;
            db.update(PaymentDB.PaymentTable.TABLE_NAME, newValues, where, null);

            changeDate(paymentInfo);

            checkDebtByPaymentInfo(paymentInfo);
        }
        return debt;

    }

    public void changeDate(PaymentActivity.PaymentInfo paymentInfo) throws ParseException {
        DBMS residentDBMS = new DBMS(context);
        SQLiteDatabase db = residentDBMS.getWritableDatabase();
        String[] projection = {DataBase.ResidentsTable._ID, DataBase.ResidentsTable.COLUMN_DATE,
                DataBase.ResidentsTable.COLUMN_PERIOD};
        Cursor cursor = db.query(
                DataBase.ResidentsTable.TABLE_NAME,   // таблица
                projection,            // столбцы
                DataBase.ResidentsTable._ID + " = ?",                  // столбцы для условия WHERE
                new String[] {String.valueOf(paymentInfo.currentID)},                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);
        int dateColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_DATE);
        int periodColumnIndex = cursor.getColumnIndex(DataBase.ResidentsTable.COLUMN_PERIOD);
        if(cursor.moveToNext()) {
            String residentsDate = cursor.getString(dateColumnIndex);
            int residentsPeriod = cursor.getInt(periodColumnIndex);
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            Calendar paymentDay = new GregorianCalendar();
            paymentDay.setTime(format.parse(residentsDate));

            Calendar previousDay = paymentDay;
            paymentDay.roll(Calendar.DAY_OF_YEAR, residentsPeriod);

            if(previousDay.get(Calendar.MONTH) == Calendar.DECEMBER &&
                    paymentDay.get(Calendar.MONTH) == Calendar.JANUARY){
                paymentDay.roll(Calendar.YEAR, 1);
            }

            String newDate = dateParcing(paymentDay);

            ContentValues newValues = new ContentValues();
            newValues.put(DataBase.ResidentsTable.COLUMN_DATE, newDate);
            String where = DataBase.ResidentsTable._ID + "=" + paymentInfo.currentID;
            db.update(DataBase.ResidentsTable.TABLE_NAME, newValues, where, null);

        }
    }

    private String dateParcing(Calendar date){
        StringBuilder parsedBuilder = new StringBuilder();
        int day = date.get(Calendar.DATE);
        int month = date.get(Calendar.MONTH) + 1;
        int year = date.get(Calendar.YEAR);
        if(day < 10){
            parsedBuilder.append("0" + day + ".");
        }
        else{
            parsedBuilder.append(day + ".");
        }
        if(month < 10){
            parsedBuilder.append("0" + month + ".");
        }
        else{
            parsedBuilder.append(month + ".");
        }
        parsedBuilder.append(year);

        return parsedBuilder.toString();
    }

}
