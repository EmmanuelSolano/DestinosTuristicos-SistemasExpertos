package cr.ucr.ac.destinosturisticos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mysql.jdbc.Statement;
import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class Resultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Button mostrarResultado = (Button)findViewById(R.id.btn_showResult);
        mostrarResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resultados.this, LugarElegido.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        getCalculateEuclides();
        ImageView image = (ImageView)findViewById(R.id.imageView3);

        Picasso.get().load("http://caturgua.com/images/donde-ir/Rincon-de-la-Vieja/Volcan-Rincon-de-la-Vieja.jpg").resize(300,250).centerCrop().error(R.mipmap.ic_launcher_camino).into(image);
        //image.setImageURI(Uri.parse("https://caturgua.com/images/donde-ir/Rincon-de-la-Vieja/Volcan-Rincon-de-la-Vieja.jpg"));
        //image.setVisibility(View.VISIBLE);
    }

    private void getCalculateEuclides(){
        Intent intent = getIntent();
        Euclides euclides = new Euclides();

        String [] getData = new String[9];
        String place = (String)intent.getSerializableExtra("place");
        getData[1]=place;
        String time = (String)intent.getSerializableExtra("time");
        getData[6]=time;
        String typeRoad = (String)intent.getSerializableExtra("typeRoad");
        getData[5]=typeRoad;
        String cost = (String)intent.getSerializableExtra("cost");
        getData[4]=cost;
        String style = (String)intent.getSerializableExtra("style");
        getData[7]=style;
        getData[0]="0";
        getData[2]="0";
        getData[3]="0";
        getData[8]="0";
        int [] data = new int[9];
        data = euclides.convertStringtoInt(getData);
        ArrayList<Double> getResult = results(data);
        if(getResult==null)
            Toast.makeText(Resultados.this, "Error al calcular euclides",
                    Toast.LENGTH_SHORT).show();
        else{
            double aux=0,aux2=0;
            for(int i=0; i < getResult.size();i++){
                for(int f=i+1;f<getResult.size()-1;f++){
                    if(getResult.get(i)>getResult.get(f)){
                        aux = getResult.get(i);
                        aux2 = getResult.get(f);
                        getResult.add(i,aux2);
                        getResult.add(f,aux);
                    }
                    aux=0;
                    aux2=0;
                }
            }
            for(int c=0;c<getResult.size();c++)
                System.out.println(getResult.get(c));
        }

    }


    private ArrayList<Double> results(int [] getData){
        ArrayList<Double> getDataEuclides = new ArrayList<>();
        ConnectionClass connectionClass= new ConnectionClass();
        Connection connection=null;
        Euclides euclides = new Euclides();
        try {
            connection = connectionClass.createConnection("laboratorios", "UCRSA.118", "proyectos_expertos_b16213_b77436", "163.178.107.10");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (connection==null){

            Toast.makeText(Resultados.this, "Error de conección con BD",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(Resultados.this, "Yeah",
                    Toast.LENGTH_SHORT).show();
            String query= "SELECT `destinosturisticosse`.`Id`,`destinosturisticosse`.`Lugar`,`destinosturisticosse`.`Nombre destino`,`destinosturisticosse`.`Actividad`,`destinosturisticosse`.`Precio`,`destinosturisticosse`.`Camino`,`destinosturisticosse`.`Tiempo`,`destinosturisticosse`.`Estilo`,`destinosturisticosse`.`Latitud` FROM `proyectos_expertos_b16213_b77436`.`destinosturisticosse`;";

            try{
                //prepara la conección para luego consultarla
                Statement statement= (Statement) connection.createStatement();
                //ejecuta la consulta y obtiene resultado
                ResultSet resultSet = statement.executeQuery(query);
                //id_Role=((Number)resultSetRol.getObject(1)).intValue();
                //pregunta si la consulta trajo resultados
                String[][] data = new String[100][100];
                int row=0, column =0;
                while(resultSet.next()){
                    String id =((String)resultSet.getString(1));
                    data[row][column++]=id;
                    String place =((String)resultSet.getString(2));
                    data[row][column++]=place;
                    String nameDestiny = ((String) resultSet.getString(3));
                    data[row][column++]=nameDestiny;
                    String nameActivity = ((String) resultSet.getString(4));
                    data[row][column++]=nameActivity;
                    String cost = ((String) resultSet.getString(5));
                    data[row][column++]=cost;
                    String road = ((String) resultSet.getString(6));
                    data[row][column++]=road;
                    String time = ((String) resultSet.getString(7));
                    data[row][column++]=time;
                    String style = ((String) resultSet.getString(8));
                    data[row][column++]=style;
                    String latitud = ((String) resultSet.getString(9));
                    data[row][column++]=latitud;
                    row++;
                    column = 0;
                }

                int [][] dataReset = euclides.convertStringtoInt(data);
                getDataEuclides = euclides.encontrarDestinosCercanos(dataReset, getData);

                return getDataEuclides;

            }catch (Exception ex){

                ex.printStackTrace();
            }
        }
        return null;
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
                Intent intentMainActivity = new Intent(Resultados.this, MainActivity.class);
                Bundle bundleMainActivity = new Bundle();
                intentMainActivity.putExtras(bundleMainActivity);
                startActivity(intentMainActivity);
                return true;
            case R.id.mapadelSitio:
                Intent intent4 = new Intent(Resultados.this, Mapadelsitio.class);
                //pasamos el nombre de usuario y la actividad
                Bundle bundle4 = new Bundle();
                //coloca el mensaje que la actividad va a transmitir
                intent4.putExtras(bundle4);
                //hace el paso de actividades
                startActivity(intent4);
                return true;
            case R.id.destinosCaracteristicas:
                Intent intent = new Intent(Resultados.this, Caracteristicas.class);
                //pasamos el nombre de usuario y la actividad
                Bundle bundle = new Bundle();
                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);
                //hace el paso de actividades
                startActivity(intent);
                return true;
            case R.id.acerca:
                Intent intent3 = new Intent(Resultados.this, Acercade.class);
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
