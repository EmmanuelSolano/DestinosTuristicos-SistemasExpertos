package cr.ucr.ac.destinosturisticos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class Acercade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);
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
                Intent intentMainActivity = new Intent(Acercade.this, MainActivity.class);
                Bundle bundleMainActivity = new Bundle();
                intentMainActivity.putExtras(bundleMainActivity);
                startActivity(intentMainActivity);
                return true;
            case R.id.mapadelSitio:
                Intent intent4 = new Intent(Acercade.this, Mapadelsitio.class);
                //pasamos el nombre de usuario y la actividad
                Bundle bundle4 = new Bundle();
                //coloca el mensaje que la actividad va a transmitir
                intent4.putExtras(bundle4);
                //hace el paso de actividades
                startActivity(intent4);
                return true;
            case R.id.destinosCaracteristicas:
                Intent intent = new Intent(Acercade.this, Caracteristicas.class);
                //pasamos el nombre de usuario y la actividad
                Bundle bundle = new Bundle();
                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);
                //hace el paso de actividades
                startActivity(intent);
                return true;
            case R.id.acerca:
                Intent intent3 = new Intent(Acercade.this, Acercade.class);
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
}
