package cr.ucr.ac.destinosturisticos;

import java.util.ArrayList;

public class Euclides {

    public ArrayList<Double> encontrarDestinosCercanos(int destinos[][], int datosUsuario[]){//la función recibe dos arreglos, el primero contiene todos los registros de la tabla correspondiente, el segundo arreglo los datos ingresados por el usuario
        double sumaDeDiferencias = 0;//almacena la diferencia de cada campo, cuando se compara los datos del usuario con el de cada registro de la tabla de la base de datos.
        ArrayList<Double> results = new ArrayList<>();//se declara arreglo para almaenar la suma total de la diferencia de cada registro de la tabla de base de datos que se compara, con los datos que el usuario ingreso.
        //se aplica el algoritmo de euclides, recorriendo dato por dato de la tabla de la base de datos con el dato del usuario
        for(int i=0;i<destinos.length;i++){
            for(int f=0;f<8;f++){
                if (destinos[i][f]!= 0)

                    sumaDeDiferencias += Math.pow((double)destinos[i][f]-(double)datosUsuario[f],2);//se aplica la potencia de 2, a la diferencia de un dato de cada registro, con dato del usuario. Al final se obtiene la suma de diferencias de cada registro de la tabla, con la del usuario

            }

            results.add(1/(1+Math.sqrt(sumaDeDiferencias)));// se le asigna al arreglo la suma de diferencia de cada registro, pero se le aplica la 1 sobre raíz de la sumadediferencias
            sumaDeDiferencias = 0;//se inicializa la variable para almacenar la nueva suma de diferencia de cada registro.
        }
        return results;// al final retorna la similitud que hay de cada registro de la tabla con los datos del usuario.
    }

    //función que se implementa en el formulario 5, para convertir los string a int, para luego calcular la similitud
    public int[][] convertStringtoInt(String [][] data){//recibe arreglo con los registros de la tabla profesores con los campos de tipo string
        int [][] results = new int[100][100];//se declara arreglo para devolver los string convertidos a int
        //se recorre el arreglo campo por campo
        for(int i=0;i<data.length;i++){
            for(int f=0;f<8;f++){
                //condiciones para convertir de acuerdo al valor de string
                if(f==1){
                    if(data[i][f].equals("Cartago"))
                        results[i][f]=1;
                    else if(data[i][f].equals("Alajuela"))
                        results[i][f]=2;
                    else if (data[i][f].equals("San José"))
                        results[i][f]=3;
                    else if (data[i][f].equals("Heredia"))
                        results[i][f]=4;
                    else if(data[i][f].equals("Guanacaste"))
                        results[i][f]=5;
                    else if(data[i][f].equals("Limón"))
                        results[i][f]=6;
                    else
                        results[i][f]=7;
                }else if (f==4){
                    if(data[i][f].equals("1000"))
                        results[i][f]=1;
                    else if(data[i][f].equals("1500"))
                        results[i][f]=2;
                    else if (data[i][f].equals("2500"))
                        results[i][f]=3;
                    else if (data[i][f].equals("3000"))
                        results[i][f]=4;
                    else if(data[i][f].equals("5000"))
                        results[i][f]=5;
                    else if(data[i][f].equals("8000"))
                        results[i][f]=6;
                    else if(data[i][f].equals("11000"))
                        results[i][f]=7;
                    else if (data[i][f].equals("15000"))
                        results[i][f]=8;
                    else if (data[i][f].equals("20000"))
                        results[i][f]=9;
                    else if(data[i][f].equals("23000"))
                        results[i][f]=10;
                    else if(data[i][f].equals("30000"))
                        results[i][f]=11;
                    else if(data[i][f].equals("50000"))
                        results[i][f]=12;
                    else if (data[i][f].equals("80000"))
                        results[i][f]=13;
                    else if (data[i][f].equals("100000"))
                        results[i][f]=14;
                    else
                        results[i][f]=15;
                }else if(f==5){
                    if(data[i][f].equals("Lastre"))
                        results[i][f]=1;
                    else if(data[i][f].equals("Asfalto"))
                        results[i][f]=2;
                    else if (data[i][f].equals("Empinado"))
                        results[i][f]=3;
                    else if (data[i][f].equals("Llano"))
                        results[i][f]=4;
                    else if(data[i][f].equals("Con curvas"))
                        results[i][f]=5;
                    else
                        results[i][f]=6;
                }else if(f==6){
                    if(data[i][f].equals("menor a 30 min"))
                        results[i][f]=1;
                    else if(data[i][f].equals("30 min"))
                        results[i][f]=2;
                    else if(data[i][f].equals("1 h 30 min"))
                        results[i][f]=3;
                    else if (data[i][f].equals("1 h 30 min"))
                        results[i][f]=4;
                    else if (data[i][f].equals("2 h"))
                        results[i][f]=5;
                    else if(data[i][f].equals("2 h 30 min"))
                        results[i][f]=6;
                    else if(data[i][f].equals("3 h"))
                        results[i][f]=7;
                    else if(data[i][f].equals("3 h 30 min"))
                        results[i][f]=8;
                    else if (data[i][f].equals("4 h"))
                        results[i][f]=9;
                    else if (data[i][f].equals("4 h 30 min"))
                        results[i][f]=10;
                    else if(data[i][f].equals("5 h"))
                        results[i][f]=11;
                    else if(data[i][f].equals("5 h 30 min"))
                        results[i][f]=12;
                    else
                        results[i][f]=13;
                }else if(f==7){
                    if(data[i][f].equals("Montaña"))
                        results[i][f]=1;
                    else if(data[i][f].equals("Campo"))
                        results[i][f]=2;
                    else if (data[i][f].equals("Ciudad"))
                        results[i][f]=3;
                    else
                        results[i][f]=4;
                }else
                    results[i][f]=0;
            }
        }
        return results;//retorna los valores conveertidos
    }

    public int[] convertStringtoInt(String [] data){//recibe arreglo con los registros de la tabla profesores con los campos de tipo string
        int [] results = new int[9];//se declara arreglo para devolver los string convertidos a int
        //se recorre el arreglo campo por campo
        for(int f=0;f<data.length;f++){
            if(f==1){
                if(data[f].equals("Cartago"))
                    results[f]=1;
                else if(data[f].equals("Alajuela"))
                    results[f]=2;
                else if (data[f].equals("San José"))
                    results[f]=3;
                else if (data[f].equals("Heredia"))
                    results[f]=4;
                else if(data[f].equals("Guanacaste"))
                    results[f]=5;
                else if(data[f].equals("Limón"))
                    results[f]=6;
                else
                    results[f]=7;
            }else if (f==4){
                if(data[f].equals("1000"))
                    results[f]=1;
                else if(data[f].equals("1500"))
                    results[f]=2;
                else if (data[f].equals("2500"))
                    results[f]=3;
                else if (data[f].equals("3000"))
                    results[f]=4;
                else if(data[f].equals("5000"))
                    results[f]=5;
                else if(data[f].equals("8000"))
                    results[f]=6;
                else if(data[f].equals("11000"))
                    results[f]=7;
                else if (data[f].equals("15000"))
                    results[f]=8;
                else if (data[f].equals("20000"))
                    results[f]=9;
                else if(data[f].equals("23000"))
                    results[f]=10;
                else if(data[f].equals("30000"))
                    results[f]=11;
                else if(data[f].equals("50000"))
                    results[f]=12;
                else if (data[f].equals("80000"))
                    results[f]=13;
                else if (data[f].equals("100000"))
                    results[f]=14;
                else
                    results[f]=15;
            }else if(f==5){
                if(data[f].equals("Lastre"))
                    results[f]=1;
                else if(data[f].equals("Asfalto"))
                    results[f]=2;
                else if (data[f].equals("Empinado"))
                    results[f]=3;
                else if (data[f].equals("Llano"))
                    results[f]=4;
                else if(data[f].equals("Con curvas"))
                    results[f]=5;
                else
                    results[f]=6;
            }else if(f==6){
                if(data[f].equals("menor a 30 min"))
                    results[f]=1;
                else if(data[f].equals("30 min"))
                    results[f]=2;
                else if(data[f].equals("1 h 30 min"))
                    results[f]=3;
                else if (data[f].equals("1 h 30 min"))
                    results[f]=4;
                else if (data[f].equals("2 h"))
                    results[f]=5;
                else if(data[f].equals("2 h 30 min"))
                    results[f]=6;
                else if(data[f].equals("3 h"))
                    results[f]=7;
                else if(data[f].equals("3 h 30 min"))
                    results[f]=8;
                else if (data[f].equals("4 h"))
                    results[f]=9;
                else if (data[f].equals("4 h 30 min"))
                    results[f]=10;
                else if(data[f].equals("5 h"))
                    results[f]=11;
                else if(data[f].equals("5 h 30 min"))
                    results[f]=12;
                else
                    results[f]=13;
            }else if(f==7){
                if(data[f].equals("Montaña"))
                    results[f]=1;
                else if(data[f].equals("Campo"))
                    results[f]=2;
                else if (data[f].equals("Ciudad"))
                    results[f]=3;
                else
                    results[f]=4;
            }else
                results[f]=0;
        }

        return results;//retorna los valores conveertidos
    }


    public String getUrlImage(String [] data, int position){
        String urlImage="";

        for(int i=0; i<data.length;i++){
            if(i==position)
                urlImage=data[i];
        }

        return urlImage;
    }
}
