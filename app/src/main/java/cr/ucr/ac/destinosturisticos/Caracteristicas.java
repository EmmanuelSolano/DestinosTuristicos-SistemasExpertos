package cr.ucr.ac.destinosturisticos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Caracteristicas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caracteristicas);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lugares, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        

        this.showPopupTime();
        this.showPopupTypeRoad();
        this.showPopupCost();
        this.showPopupStylePlace();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.principal, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.inicio:
                Intent intentMainActivity = new Intent(Caracteristicas.this, MainActivity.class);
                Bundle bundleMainActivity = new Bundle();
                intentMainActivity.putExtras(bundleMainActivity);
                startActivity(intentMainActivity);
                return true;
            case R.id.mapadelSitio:
                Intent intent4 = new Intent(Caracteristicas.this, Mapadelsitio.class);
                //pasamos el nombre de usuario y la actividad
                Bundle bundle4 = new Bundle();
                //coloca el mensaje que la actividad va a transmitir
                intent4.putExtras(bundle4);
                //hace el paso de actividades
                startActivity(intent4);
                return true;
            case R.id.destinosCaracteristicas:
                Intent intent = new Intent(Caracteristicas.this, Caracteristicas.class);
                //pasamos el nombre de usuario y la actividad
                Bundle bundle = new Bundle();
                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);
                //hace el paso de actividades
                startActivity(intent);
                return true;
            case R.id.acerca:
                Intent intent3 = new Intent(Caracteristicas.this, Acercade.class);
                //pasamos el nombre de usuario y la actividad
                Bundle bundle3 = new Bundle();
                //coloca el mensaje que la actividad va a transmitir
                intent3.putExtras(bundle3);
                //hace el paso de actividades
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void chargeComboTime(View popUpTimeView){
        Spinner spinner = (Spinner) popUpTimeView.findViewById(R.id.spinnerTime);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Caracteristicas.this, R.array.horas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void chargeComboTypeRoad(View popUpTimeView){
        Spinner spinner = (Spinner) popUpTimeView.findViewById(R.id.spinner_typeRoad);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Caracteristicas.this, R.array.tipo, android.R.layout.simple_selectable_list_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void chargeComboCost(View popUpTimeView){
        Spinner spinner = (Spinner) popUpTimeView.findViewById(R.id.spinner_cost);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Caracteristicas.this, R.array.precio, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void chargeComboStyle(View popUpTimeView){
        Spinner spinner = (Spinner) popUpTimeView.findViewById(R.id.spinner_style);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Caracteristicas.this, R.array.estilo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void showPopupTime()
    {

        ImageButton imageButton = (ImageButton) findViewById(R.id.btn_time);

        imageButton.setOnClickListener(new View.OnClickListener() {
            private AlertDialog alertDialog = null;

            @Override
            public void onClick(View view) {
                // Create a alert dialog builder.
                final AlertDialog.Builder builder = new AlertDialog.Builder(Caracteristicas.this);
                // Set title value.
                builder.setTitle("Duracción de traslado al destino");

                // Get custom login form view.
                final View popUpTimeView = getLayoutInflater().inflate(R.layout.popup, null);
                // Set above view in alert dialog.
                chargeComboTime(popUpTimeView);
                builder.setView(popUpTimeView);
                builder.setCancelable(true);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void showPopupTypeRoad()
    {

        ImageButton imageButton = (ImageButton) findViewById(R.id.btn_typeRoad);

        imageButton.setOnClickListener(new View.OnClickListener() {
            private AlertDialog alertDialog = null;

            @Override
            public void onClick(View view) {
                // Create a alert dialog builder.
                final AlertDialog.Builder builder = new AlertDialog.Builder(Caracteristicas.this);
                // Set title value.
                builder.setTitle("Tipo de camino");

                // Get custom login form view.
                final View popUpTimeView = getLayoutInflater().inflate(R.layout.popup_tipo_camino, null);
                // Set above view in alert dialog.
                chargeComboTypeRoad(popUpTimeView);
                builder.setView(popUpTimeView);
                builder.setCancelable(true);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }


    private void showPopupCost()
    {

        ImageButton imageButton = (ImageButton) findViewById(R.id.btn_cost);

        imageButton.setOnClickListener(new View.OnClickListener() {
            private AlertDialog alertDialog = null;

            @Override
            public void onClick(View view) {
                // Create a alert dialog builder.
                final AlertDialog.Builder builder = new AlertDialog.Builder(Caracteristicas.this);
                // Set title value.
                builder.setTitle("Precio de destino turístico");

                // Get custom login form view.
                final View popUpTimeView = getLayoutInflater().inflate(R.layout.popup_precio, null);
                // Set above view in alert dialog.
                chargeComboCost(popUpTimeView);
                builder.setView(popUpTimeView);
                builder.setCancelable(true);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void showPopupStylePlace()
    {

        ImageButton imageButton = (ImageButton) findViewById(R.id.btn_style);

        imageButton.setOnClickListener(new View.OnClickListener() {
            private AlertDialog alertDialog = null;

            @Override
            public void onClick(View view) {
                // Create a alert dialog builder.
                final AlertDialog.Builder builder = new AlertDialog.Builder(Caracteristicas.this);
                // Set title value.
                builder.setTitle("Tipo de destino turístico");

                // Get custom login form view.
                final View popUpTimeView = getLayoutInflater().inflate(R.layout.popup_estilo_destino, null);
                // Set above view in alert dialog.
                chargeComboStyle(popUpTimeView);
                builder.setView(popUpTimeView);
                builder.setCancelable(true);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}
