package database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import UI.AmountInput;
import UI.SelectUserActivity;
import UI.TransactionsFragment;
import UI.UserInfoActivity;
import UI.UsersFragment;
import database.UserContract.UserEntry;

public class UserHelper extends SQLiteOpenHelper {

    String TABLE_NAME = UserEntry.TABLE_NAME;

    /** Name of the database file */
    private static final String DATABASE_NAME = "customer.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.*/
    private static final int DATABASE_VERSION = 1;

    public UserHelper(AmountInput context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public UserHelper(UsersFragment context) {
        super(context.getActivity(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    public UserHelper(TransactionsFragment context) {
        super(context.getActivity(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    public UserHelper(SelectUserActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public UserHelper(UserInfoActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        + UserEntry.USER_ID + " INTEGER , "
        // Create a String that contains the SQL statement to create the customer table
        String SQL_CREATE_USER_TABLE =  "CREATE TABLE " + TABLE_NAME + " ("

                + UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " VARCHAR PRIMARY KEY, "
                + UserEntry.COLUMN_USER_NAME + " VARCHAR NOT NULL, "
                + UserEntry.COLUMN_GENDER + " INTEGER, "                // 0 - female and 1 - male
                + UserEntry.COLUMN_USER_EMAIL + " VARCHAR, "
                + UserEntry.COLUMN_USER_IFSC_CODE + " VARCHAR NOT NULL, "
                + UserEntry.COLUMN_USER_CONTACT + " VARCHAR, "
                + UserEntry.COLUMN_USER_ACCOUNT_BALANCE + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_USER_TABLE);

        String inputColumns = "(" + UserEntry.COLUMN_USER_ACCOUNT_NUMBER
                                + ", " + UserEntry.COLUMN_USER_NAME
                                + ", " + UserEntry.COLUMN_GENDER
                                + ", " + UserEntry.COLUMN_USER_EMAIL
                                + ", " + UserEntry.COLUMN_USER_IFSC_CODE
                                + ", " + UserEntry.COLUMN_USER_CONTACT
                                + ", " + UserEntry.COLUMN_USER_ACCOUNT_BALANCE + ")";

        // Insert Into Table
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('11111111111','sohal', 1,  'sohal@gmail.com','SBI1234','9087654321', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('22222222222','alamin', 1,  'alamin@gmail.com','HDFC5678','8097654321', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('33333333333','joidur', 1,  'joidur@gmail.com','BOI9012','7098654321', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('44444444444','radha', 0,  'radha@gmail.com','SBI3456','8098754321', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('55555555555','sumon', 1,'sumon@gmail.com','AXIS7890','9098764321', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('66666666666','laila', 0, 'laila@gmail.com','BOI1357','8998765321', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('77777777777','priyo', 1, 'priyo@gmail.com','AXIS9246','7098765421', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('88888888888','priya', 0, 'priya@gmail.com','HDFC8024','7098765431', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('99999999999','shubho', 0, 'shubho@gmail.com','SBI5790','9098765432', 10000)");
        db.execSQL("insert into " + TABLE_NAME + inputColumns + " values('32165498702','sahadat', 1, 'sahadat@gmail.com','BOI3142','8213141516', 10000)");

        Log.e("User Helper", "Inserted Dummy Data");

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
            onCreate(db);
        }
    }



    public Cursor readAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserEntry.TABLE_NAME, null);
        return cursor;
    }

    public Cursor readAllDataExcept(String accountNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserEntry.TABLE_NAME + " WHERE " +
                UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " != " + accountNo, null);
        return cursor;
    }

    public Cursor readParticularData (String accountNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserEntry.TABLE_NAME + " WHERE " +
                UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " = " + accountNo, null);
        return cursor;
    }

    public void updateAmount(String accountNo, int amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + UserEntry.TABLE_NAME + " SET " + UserEntry.COLUMN_USER_ACCOUNT_BALANCE + " = " + amount + " WHERE " +
                UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " = " + accountNo);
    }


}
