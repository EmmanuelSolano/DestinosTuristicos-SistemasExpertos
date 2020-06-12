package cr.ucr.ac.destinosturisticos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mysql.jdbc.Statement;
import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LugarElegido extends YouTubeBaseActivity {

    TextView tvNombreLugar;
    TextView tvTipoActividad;
    TextView tvTiempoViaje;
    TextView tvTipoCamino;
    TextView tvPrecioTiquete;
    TextView tvEstilo;

    ImageView ivLugar;

    YouTubePlayerView mYouTubePlayerView;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar_elegido);
        Intent intent = getIntent();

        String place = (String)intent.getSerializableExtra("place");
        String activity = (String)intent.getSerializableExtra("activity");
        String time = (String)intent.getSerializableExtra("time");
        String typeRoad = (String)intent.getSerializableExtra("road");
        String cost = (String)intent.getSerializableExtra("cost");
        String style = (String)intent.getSerializableExtra("style");

        String id= (String) traerId(place,activity,time,typeRoad,cost,style);
        String linkImagen = (String) traerImagenLink(id);
        final String linkVideo = (String) traerVideoLink(id);

        Button comeBackResults = (Button)findViewById(R.id.btn_back);
        comeBackResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });

        tvNombreLugar = (TextView) findViewById(R.id.tvNombreLugar);
        tvNombreLugar.setText("Nombre del lugar: "+place);

        tvTipoActividad = (TextView) findViewById(R.id.tvTipoActividad);
        tvTipoActividad.setText("Tipo de actividad: "+activity);

        tvTiempoViaje = (TextView) findViewById(R.id.tvTiempoViaje);
        tvTiempoViaje.setText("Tiempo aproximado de viaje: "+time);

        tvTipoCamino = (TextView) findViewById(R.id.tvTipoCamino);
        tvTipoCamino.setText("Tipo de camino: "+typeRoad);

        tvPrecioTiquete = (TextView) findViewById(R.id.tvPrecioTiquete);
        tvPrecioTiquete.setText("Precio del tiquete: "+cost+" colones");

        tvEstilo = (TextView) findViewById(R.id.tvEstiloLugar);
        tvEstilo.setText("Estilo del lugar: "+style);

        ivLugar = (ImageView) findViewById(R.id.ivImagenLugar);
        Picasso.get().load(""+linkImagen).resize(350,350).centerCrop().error(R.mipmap.ic_launcher_camino).into(ivLugar);

        btnPlay = (Button) findViewById(R.id.btn_playVideo);
        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlayer);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("FONMrA11gYs");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYouTubePlayerView.initialize(ConfiguracionYT.getApiKey(), mOnInitializedListener);
            }
        });
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
                Intent intentMainActivity = new Intent(LugarElegido.this, MainActivity.class);
                Bundle bundleMainActivity = new Bundle();
                intentMainActivity.putExtras(bundleMainActivity);
                startActivity(intentMainActivity);
                return true;
            case R.id.mapadelSitio:
                Intent intent4 = new Intent(LugarElegido.this, Mapadelsitio.class);
                //pasamos el nombre de usuario y la actividad
                Bundle bundle4 = new Bundle();
                //coloca el mensaje que la actividad va a transmitir
                intent4.putExtras(bundle4);
                //hace el paso de actividades
                startActivity(intent4);
                return true;
            case R.id.destinosCaracteristicas:
                Intent intent = new Intent(LugarElegido.this, Caracteristicas.class);
                //pasamos el nombre de usuario y la actividad
                Bundle bundle = new Bundle();
                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);
                //hace el paso de actividades
                startActivity(intent);
                return true;
            case R.id.acerca:
                Intent intent3 = new Intent(LugarElegido.this, Acercade.class);
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

    private String traerImagenLink(String id) {
        String link="";

        ConnectionClass connectionClass= new ConnectionClass();
        Connection connection = null;

        try {
            connection = connectionClass.createConnection("laboratorios", "UCRSA.118", "proyectos_expertos_b16213_b77436", "163.178.107.10");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (connection==null){

            Toast.makeText(LugarElegido.this, "Error de conexión con BD",
                    Toast.LENGTH_SHORT).show();
        }else {
            String query = "SELECT `imageneslinks`.`imagenes` FROM `proyectos_expertos_b16213_b77436`.`imageneslinks` " +
                    "WHERE `imageneslinks`.`id`="+id+";";

            try {
                //prepara la conección para luego consultarla
                Statement statement= (Statement) connection.createStatement();
                //ejecuta la consulta y obtiene resultado
                ResultSet resultSet = statement.executeQuery(query);
                int row=0, column =0;
                while(resultSet.next()){
                    link =((String)resultSet.getString(1));

                }


            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }

        return link;
    }

    private String traerVideoLink(String id) {
        String link="";

        ConnectionClass connectionClass= new ConnectionClass();
        Connection connection = null;

        try {
            connection = connectionClass.createConnection("laboratorios", "UCRSA.118", "proyectos_expertos_b16213_b77436", "163.178.107.10");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (connection==null){

            Toast.makeText(LugarElegido.this, "Error de conexión con BD",
                    Toast.LENGTH_SHORT).show();
        }else {
            String query = "SELECT `videoslinks`.`linkvideo` FROM `proyectos_expertos_b16213_b77436`.`videoslinks` " +
                    "WHERE `videoslinks`.`id`="+id+";";

            try {
                //prepara la conección para luego consultarla
                Statement statement= (Statement) connection.createStatement();
                //ejecuta la consulta y obtiene resultado
                ResultSet resultSet = statement.executeQuery(query);
                int row=0, column =0;
                while(resultSet.next()){
                    link =((String)resultSet.getString(1));

                }


            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }

        return link;
    }

    private String traerId(String place, String activity, String time, String typeRoad, String cost, String style) {

        String id="";

        ConnectionClass connectionClass= new ConnectionClass();
        Connection connection = null;

        try {
            connection = connectionClass.createConnection("laboratorios", "UCRSA.118", "proyectos_expertos_b16213_b77436", "163.178.107.10");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (connection==null){

            Toast.makeText(LugarElegido.this, "Error de conexión con BD",
                    Toast.LENGTH_SHORT).show();
        }else {
            String query = "SELECT `destinosturisticosse`.`Id` FROM `proyectos_expertos_b16213_b77436`.`destinosturisticosse` " +
                    "WHERE `destinosturisticosse`.`Nombre destino`='" + place + "' and `destinosturisticosse`.`Actividad`='"+ activity+
                        "' and `destinosturisticosse`.`Precio`='"+ cost + "' and `destinosturisticosse`.`Camino`='"+ typeRoad+
                    "' and `destinosturisticosse`.`Tiempo`='"+ time + "' and `destinosturisticosse`.`Estilo`='"+ style+"';";

            try {
                //prepara la conección para luego consultarla
                Statement statement= (Statement) connection.createStatement();
                //ejecuta la consulta y obtiene resultado
                ResultSet resultSet = statement.executeQuery(query);
                int row=0, column =0;
                while(resultSet.next()){
                    id =((String)resultSet.getString(1));

                }


            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }

        return id;
    }




}
