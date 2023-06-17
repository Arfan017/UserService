package com.example.userprojek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.view.GravityCompat;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.example.userprojek.adapter.CitySpinnerAdapter;
import com.example.userprojek.adapter.ShalatAdapter;
import com.example.userprojek.modul.City;
import com.example.userprojek.modul.Shalat;
import com.example.userprojek.networking.ApiCity;
import com.example.userprojek.networking.ApiShalatSchedule;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import org.threeten.bp.Duration;
import org.threeten.bp.LocalTime;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  ApiShalatSchedule.OnResponse, ApiCity.OnResponse, TextView.OnEditorActionListener, View.OnClickListener {

    private TextInputEditText inputFindCitySetting;
    private AppCompatSpinner spinSetting;
    private Button btnOkSetting;
    private TextView tvNextShalatSchedule, tvNextShalatCountdown, tvDateToday, citt;
    private RelativeLayout rlMainContent;
    private ListView lvShalat;
    private ProgressBar pbMainContent;
    private ApiShalatSchedule apiShalatSchedule;
    private static City city;
    private ApiCity apiCity;
    BottomNavigationView botnavv;
    private static ArrayList<City> cities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNextShalatSchedule = findViewById(R.id.tv_next_shalat_schedule);
        tvNextShalatCountdown = findViewById(R.id.tv_next_shalat_countdown);
        tvDateToday = findViewById(R.id.tv_date_today);
        rlMainContent = findViewById(R.id.rl_main_content);
        lvShalat = findViewById(R.id.lv_shalat);
        pbMainContent = findViewById(R.id.pb_main_content);
        inputFindCitySetting = findViewById(R.id.input_find_city_setting);
        spinSetting = findViewById(R.id.spin_setting);
        btnOkSetting = findViewById(R.id.btn_ok_setting);
        citt = findViewById(R.id.cit);

        initt();
        checkPreferences();
        init();

        botnavv = findViewById(R.id.botjadwal);
        botnavv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hom:
                        Intent inten = new Intent(MainActivity.this, MenuDash.class);
                        startActivity(inten);
                        finish();
                        break;
                    case R.id.exi:
                        finish();
                        System.exit(0);
                        break;
                }
                return false;
            }
        });
    }

    private void checkPreferences() {
        SharedPreferences preferences = getApplication().getSharedPreferences("MyPref", MODE_PRIVATE);
        String cityCode = preferences.getString("cityCode", "600");
        String cityName = preferences.getString("cityName", "Bungo");

        if (getIntent().getParcelableExtra("City") != null) {
            city = getIntent().getParcelableExtra("City");
        } else {
            city = new City(cityCode, cityName);
        }
    }

    private void initt() {
        apiCity = new ApiCity(this, this);
        apiCity.getAllCity();
        inputFindCitySetting.setOnEditorActionListener(this);
        btnOkSetting.setOnClickListener(this);
    }

    private void init() {
        apiShalatSchedule = new ApiShalatSchedule(this, this);
        citt.setText("DAERAH "+city.getName());
        apiShalatSchedule.getShalat(city.getId());
    }
    @Override
    public void onResponse(Shalat shalat) {
        rlMainContent.setVisibility(View.VISIBLE);
        pbMainContent.setVisibility(View.INVISIBLE);
        tvDateToday.setText(shalat.getDateToday());
        ShalatAdapter shalatAdapter = new ShalatAdapter(this, shalat);
        lvShalat.setAdapter(shalatAdapter);
        setHeaderTime(shalat);
    }

    private void setHeaderTime(Shalat shalat) {
        ArrayList<String> names = shalat.getName();
        ArrayList<LocalTime> times = shalat.getTimes();
        int lastIndex = times.size() - 1;
        LocalTime now = LocalTime.now();
        if (now.isAfter(times.get(lastIndex))) {
            tvNextShalatSchedule.setText(names.get(0));
            long hour = 23 - Math.abs(durationHour(times.get(0)));
            long minute = 60 - Math.abs(durationMinute(times.get(0)));
            String duration = hour + " jam : " + minute + " menit lagi";
            tvNextShalatCountdown.setText(duration);
        } else {
            for (int i = 0; i < names.size(); i++) {
                if (durationMinute(times.get(i)) > 0) {
                    tvNextShalatSchedule.setText(names.get(i));
                    tvNextShalatCountdown.setText(format(times.get(i)));
                    break;
                }
            }
        }
    }

    private long durationHour(LocalTime localTime) {
        LocalTime now = LocalTime.now();
        return Duration.between(now, localTime).toHours();
    }

    private long durationMinute(LocalTime localTime) {
        LocalTime now = LocalTime.now();
        return Duration.between(now, localTime).toMinutes() % 60;
    }

    private String format(LocalTime localTime) {
        long durationHour = durationHour(localTime);
        long durationMinute = durationMinute(localTime);
        return durationHour + " jam : " + durationMinute + " menit lagi";
    }

    @Override
    public void errorResponse(VolleyError error) {
        error.printStackTrace();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            if (MainActivity.cities == null) {
                Toast.makeText(this, "Gagal ambil data", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!MainActivity.cities.isEmpty()) {
                String keyword = inputFindCitySetting.getText().toString().trim();
                ArrayList<City> citiesResult = new ArrayList<>();

                for (City city : MainActivity.cities) {
                    if (city.getName().toLowerCase().contains(keyword.toLowerCase())) {
                        citiesResult.add(city);
                        showSpinner(citiesResult);
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void showSpinner(final ArrayList<City> cities) {
        CitySpinnerAdapter citySpinnerAdapter = new CitySpinnerAdapter(this, cities);
        spinSetting.setAdapter(citySpinnerAdapter);
        spinSetting.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != -1) {
                    MainActivity.city = cities.get(i);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("cityCode", MainActivity.city.getId());
        editor.putString("cityName", MainActivity.city.getName());
        editor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Berhasil simpan perubahan", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(final ArrayList<City> cities) {
        MainActivity.cities = cities;
        showSpinner(cities);
    }

    @Override
    public void onSelectedItem(City city) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}