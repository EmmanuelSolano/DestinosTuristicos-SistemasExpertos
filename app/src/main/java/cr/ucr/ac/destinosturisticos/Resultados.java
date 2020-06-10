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

import com.mysql.jdbc.Statement;
import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import androidx.appcompat.app.AppCompatActivity;

public class Resultados extends AppCompatActivity {

    private String placeDestination1;
    private String activityDestination1;
    private String timeDestination1;
    private String roadDestination1;
    private String costDestination1;
    private String styleDestination1;

    private String placeDestination2;
    private String activityDestination2;
    private String timeDestination2;
    private String roadDestination2;
    private String costDestination2;
    private String styleDestination2;

    private String placeDestination3;
    private String activityDestination3;
    private String timeDestination3;
    private String roadDestination3;
    private String costDestination3;
    private String styleDestination3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Button showDestination1 = (Button)findViewById(R.id.btn_showResult1);
        showDestination1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resultados.this, LugarElegido.class);
                Bundle bundle = new Bundle();
                intent.putExtra("place",placeDestination1);
                intent.putExtra("activity",activityDestination1);
                intent.putExtra("time",timeDestination1);
                intent.putExtra("road",roadDestination1);
                intent.putExtra("cost",costDestination1);
                intent.putExtra("style",styleDestination1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Button showDestination2 = (Button)findViewById(R.id.btn_showResult2);
        showDestination2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resultados.this, LugarElegido.class);
                Bundle bundle = new Bundle();
                intent.putExtra("place",placeDestination2);
                intent.putExtra("activity",activityDestination2);
                intent.putExtra("time",timeDestination2);
                intent.putExtra("road",roadDestination2);
                intent.putExtra("cost",costDestination2);
                intent.putExtra("style",styleDestination2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Button showDestination3 = (Button)findViewById(R.id.btn_showResult3);
        showDestination3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resultados.this, LugarElegido.class);
                Bundle bundle = new Bundle();
                intent.putExtra("place",placeDestination3);
                intent.putExtra("activity",activityDestination3);
                intent.putExtra("time",timeDestination3);
                intent.putExtra("road",roadDestination3);
                intent.putExtra("cost",costDestination3);
                intent.putExtra("style",styleDestination3);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        getCalculateEuclides();
    }

    //se pasan los datos de la base de datos y los datos del usuario para realizar el calculo de euclides
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
        ArrayList<Double> auxResultList = results(data);
        if(getResult==null)
            Toast.makeText(Resultados.this, "Error al calcular euclides",
                    Toast.LENGTH_SHORT).show();
        else{
            ArrayList<String> similarityOfPlace = getDestinationAboutPlace(place);
            ArrayList<Double> probabilityOfPlace = new ArrayList<>();
            ArrayList<Double> auxprobabilityOfPlace = new ArrayList<>();
            int columnNumberSimiliratyPlace=0;
            ArrayList<Integer> placesMostSimilarity = new ArrayList<>();
            for(int i=1;i<=getResult.size();i++){
                int position = Integer.parseInt(similarityOfPlace.get(columnNumberSimiliratyPlace));
                if(i==position){
                    placesMostSimilarity.add(i);
                    columnNumberSimiliratyPlace++;
                }
                if(columnNumberSimiliratyPlace==similarityOfPlace.size()){
                    i=getResult.size();
                }
            }
            int row=0;
            if(!place.equals("Limón")) {
                for (int f = 0; f <= getResult.size(); f++) {
                    if (f == placesMostSimilarity.get(row)) {
                        probabilityOfPlace.add(getResult.get(f));
                        auxprobabilityOfPlace.add(getResult.get(f));
                        row++;
                    }
                    if (row == placesMostSimilarity.size())
                        f = getResult.size();
                }
            }else{
                for (int f = 0; f <= getResult.size(); f++) {
                    if (f == placesMostSimilarity.get(row)) {
                        if(f==100){
                            int ret=row+1;
                            probabilityOfPlace.add(getResult.get(ret));
                            auxprobabilityOfPlace.add(getResult.get(ret));
                        }else {

                            probabilityOfPlace.add(getResult.get(f));
                            auxprobabilityOfPlace.add(getResult.get(f));
                            row++;
                        }
                    }
                    if (row == placesMostSimilarity.size())
                        f = getResult.size();
                }
            }
            //Collections.sort(getResult);
            Collections.sort(probabilityOfPlace);
            int place1=0,place2=0,place3=0;
            double placeMostSimilarity1 = probabilityOfPlace.get(0);
            double placeMostSimilarity2 = probabilityOfPlace.get(1);
            double placeMostSimilarity3 = probabilityOfPlace.get(2);
            for(int i=0;i<auxResultList.size();i++) {
                if (auxResultList.get(i) == placeMostSimilarity1) {
                    place1 = i;
                }
                if (auxResultList.get(i) == placeMostSimilarity2) {
                    place2 = i;
                }
                if (auxResultList.get(i) == placeMostSimilarity3) {
                    place3 = i;
                }
            }
            getDestinations(place1,place2,place3);
        }

    }

    private ArrayList<String> getDestinationAboutPlace(String placeSearch){
        ArrayList<String> rowPlace = new ArrayList<>();
        ConnectionClass connectionClass= new ConnectionClass();
        Connection connection=null;
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
        }else {
            String query = "SELECT `destinosturisticosse`.`Id` FROM `proyectos_expertos_b16213_b77436`.`destinosturisticosse` WHERE `destinosturisticosse`.`Lugar`='"+placeSearch+"';";

            try {
                //prepara la conección para luego consultarla
                Statement statement= (Statement) connection.createStatement();
                //ejecuta la consulta y obtiene resultado
                ResultSet resultSet = statement.executeQuery(query);
                int row=0, column =0;
                while(resultSet.next()){
                    String id =((String)resultSet.getString(1));
                    rowPlace.add(id);

                }

                return rowPlace;
            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
        return null;
    }


    //imprime los destinos turisticos encontrados
    private void getDestinations(int place1, int place2, int place3){
        ConnectionClass connectionClass= new ConnectionClass();
        Connection connection=null;
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
            String queryPlace1= "SELECT * FROM `proyectos_expertos_b16213_b77436`.`destinosturisticosse` WHERE `destinosturisticosse`.`Id`="+place1+";";
            String queryPlace2= "SELECT * FROM `proyectos_expertos_b16213_b77436`.`destinosturisticosse` WHERE `destinosturisticosse`.`Id`="+place2+";";
            String queryPlace3= "SELECT * FROM `proyectos_expertos_b16213_b77436`.`destinosturisticosse` WHERE `destinosturisticosse`.`Id`="+place3+";";

            String urlDestinations1 = "SELECT `imageneslinks`.`imagenes` FROM `proyectos_expertos_b16213_b77436`.`imageneslinks` WHERE `imageneslinks`.`id`="+place1+";";
            String urlDestinations2 = "SELECT `imageneslinks`.`imagenes` FROM `proyectos_expertos_b16213_b77436`.`imageneslinks` WHERE `imageneslinks`.`id`="+place2+";";
            String urlDestinations3 = "SELECT `imageneslinks`.`imagenes` FROM `proyectos_expertos_b16213_b77436`.`imageneslinks` WHERE `imageneslinks`.`id`="+place3+";";
            try{
                //prepara la conección para luego consultarla
                Statement statementDataDestination1= (Statement) connection.createStatement();
                Statement statementDataDestination2= (Statement) connection.createStatement();
                Statement statementDataDestination3= (Statement) connection.createStatement();
                Statement statementImage1= (Statement) connection.createStatement();
                Statement statementImage2= (Statement) connection.createStatement();
                Statement statementImage3= (Statement) connection.createStatement();
                //ejecuta la consulta y obtiene resultado
                ResultSet resultSetDestination1 = statementDataDestination1.executeQuery(queryPlace1);
                ResultSet resultSetDestination2 = statementDataDestination2.executeQuery(queryPlace2);
                ResultSet resultSetDestination3 = statementDataDestination3.executeQuery(queryPlace3);
                ResultSet imageSetDestination1 = statementImage1.executeQuery(urlDestinations1);
                ResultSet imageSetDestination2 = statementImage2.executeQuery(urlDestinations2);
                ResultSet imageSetDestination3 = statementImage3.executeQuery(urlDestinations3);
                //pregunta si la consulta trajo resultados


                //cargamos imagenes a la página
                ImageView image1 = (ImageView)findViewById(R.id.imageView3);
                ImageView image2 = (ImageView)findViewById(R.id.imageView5);
                ImageView image3 = (ImageView)findViewById(R.id.imageView9);
                TextView dataDestination1 = (TextView)findViewById(R.id.txt_result1);
                TextView dataDestination2 = (TextView)findViewById(R.id.txt_result2);
                TextView dataDestination3 = (TextView)findViewById(R.id.txt_result3);
                String textDestinations1="", textDestinations2="", textDestinations3="", imageDestinations1="", imageDestinations2="", imageDestinations3="";
                if(imageSetDestination1.next())
                    imageDestinations1 = (String) imageSetDestination1.getString(1);
                if(imageSetDestination2.next())
                    imageDestinations2 = (String) imageSetDestination2.getString(1);
                if(imageSetDestination3.next())
                    imageDestinations3 = (String) imageSetDestination3.getString(1);
                if(resultSetDestination1.next()) {
                    placeDestination1 = (String) resultSetDestination1.getString(3);
                    activityDestination1 = (String) resultSetDestination1.getString(4);
                    timeDestination1 = (String) resultSetDestination1.getString(7);
                    roadDestination1 = (String) resultSetDestination1.getString(6);
                    costDestination1 = (String) resultSetDestination1.getString(5);
                    styleDestination1 = (String) resultSetDestination1.getString(8);
                    textDestinations1 = placeDestination1+". La actividad a realizar es: "+activityDestination1+". Duracción: "+timeDestination1+". Camino: "+roadDestination1+". Precio: "+costDestination1+". Estilo: "+styleDestination1;
                    dataDestination1.setText(textDestinations1);
                }
                if(resultSetDestination2.next()) {
                    placeDestination2 = (String) resultSetDestination2.getString(3);
                    activityDestination2 = (String) resultSetDestination2.getString(4);
                    timeDestination2 = (String) resultSetDestination2.getString(7);
                    roadDestination2 = (String) resultSetDestination2.getString(6);
                    costDestination2 = (String) resultSetDestination2.getString(5);
                    styleDestination2 = (String) resultSetDestination2.getString(8);
                    textDestinations2 = placeDestination2+". La actividad a realizar es: "+activityDestination2+". Duracción: "+timeDestination2+". Camino: "+roadDestination2+". Precio: "+costDestination2+". Estilo: "+styleDestination2;
                    dataDestination2.setText(textDestinations2);
                }

                if(resultSetDestination3.next()) {
                    placeDestination3 = (String) resultSetDestination3.getString(3);
                    activityDestination3 = (String) resultSetDestination3.getString(4);
                    timeDestination3 = (String) resultSetDestination3.getString(7);
                    roadDestination3 = (String) resultSetDestination3.getString(6);
                    costDestination3 = (String) resultSetDestination3.getString(5);
                    styleDestination3 = (String) resultSetDestination3.getString(8);
                    textDestinations3 = placeDestination3+". La actividad a realizar es: "+activityDestination3+". Duracción: "+timeDestination3+". Camino: "+roadDestination3+". Precio: "+costDestination3+". Estilo: "+styleDestination3;
                    dataDestination3.setText(textDestinations3);
                }

                Picasso.get().load(""+imageDestinations1).resize(300,250).centerCrop().error(R.mipmap.ic_launcher_camino).into(image1);
                Picasso.get().load(""+imageDestinations2).resize(300,250).centerCrop().error(R.mipmap.ic_launcher_camino).into(image2);
                Picasso.get().load(""+imageDestinations3).resize(300,250).centerCrop().error(R.mipmap.ic_launcher_camino).into(image3);


            }catch (Exception ex){

                ex.printStackTrace();
            }
        }
    }

    //obtiene todos los registros de la base de datos y los convierte a un valor entero para realizar el calculo de euclides
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
            String query= "SELECT `destinosturisticosse`.`Id`,`destinosturisticosse`.`Lugar`,`destinosturisticosse`.`Nombre destino`,`destinosturisticosse`.`Actividad`,`destinosturisticosse`.`Precio`,`destinosturisticosse`.`Camino`,`destinosturisticosse`.`Tiempo`,`destinosturisticosse`.`Estilo`,`destinosturisticosse`.`Latitud` FROM `proyectos_expertos_b16213_b77436`.`destinosturisticosse`";

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
