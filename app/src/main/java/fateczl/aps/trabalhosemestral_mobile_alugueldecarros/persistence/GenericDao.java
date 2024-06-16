package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDao extends SQLiteOpenHelper {

    //Variáveis
    private static final String DATABASE = "BancoTrabalho_AluguelCarros";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_CLIENTE =
            "CREATE TABLE CLIENTE("+
            "CPF INT NOT NULL PRIMARY KEY,"+
            "nomeCliente VARCHAR(30) NOT NULL);";
    private static final String CREATE_TABLE_CARRO =
            "CREATE TABLE CARRO("+
            "chassi INT NOT NULL PRIMARY KEY,"+
            "nomeCarro VARCHAR(30) NOT NULL,"+
            "montadora VARCHAR(30) NOT NULL,"+
            "numRodas INT NOT NULL,"+
            "qtdPortas INT NOT NULL);";
    private static final String CREATE_TABLE_ALUGUEL = "CREATE TABLE ALUGUEL("+
            "numAluguel INT NOT NULL PRIMARY KEY,"+
            "dataInicio VARCHAR(10) NOT NULL,"+
            "dataFinal VARCHAR(10) NOT NULL,"+
            "clienteCPF CHAR(11) NOT NULL,"+
            "chassiCarro CHAR(11) NOT NULL,"+
            "FOREIGN KEY(clienteCPF) REFERENCES CLIENTE(CPF),"+
            "FOREIGN KEY(chassiCarro) REFERENCES CARRO(chassi));";

    //Construtor
    public GenericDao(Context context){
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    //Métodos
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CLIENTE);
        sqLiteDatabase.execSQL(CREATE_TABLE_CARRO);
        sqLiteDatabase.execSQL(CREATE_TABLE_ALUGUEL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CLIENTE");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CARRO");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ALUGUEL");
            onCreate(sqLiteDatabase);
        }
    }
}
