package UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.Shubhobank.R;

public class TransactionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new TransactionsFragment())
                .commit();
    }

}
