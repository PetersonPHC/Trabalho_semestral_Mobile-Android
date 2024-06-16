package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model.Aluguel;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model.Carro;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model.Cliente;

public class AluguelDao implements IAluguelDao, ICRUDDao<Aluguel> {
    private final Context context;
    private GenericDao genericDao;
    private SQLiteDatabase database;

    public AluguelDao(Context context) {
        this.context = context;
    }

    @Override
    public AluguelDao open() throws SQLException {
        genericDao = new GenericDao(context);
        database = genericDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        genericDao.close();
    }

    @Override
    public void insert(Aluguel aluguel) throws SQLException {
        ContentValues contentValues = getContentValues(aluguel);
        database.insert("ALUGUEL", null, contentValues);
    }

    @Override
    public int update(Aluguel aluguel) throws SQLException {
        ContentValues contentValues = getContentValues(aluguel);
        int retorno = database.update("ALUGUEL", contentValues, "numAluguel = " + aluguel.getNumero(), null);
        return retorno;
    }

    @Override
    public void delete(Aluguel aluguel) throws SQLException {
        ContentValues contentValues = getContentValues(aluguel);
        database.delete("ALUGUEL", "numAluguel = " + aluguel.getNumero(), null);
    }

    @SuppressLint("Range")
    @Override
    public Aluguel findOne(Aluguel aluguel) throws SQLException {

        String query = "SELECT CPF, nomeCliente, chassi, nomeCarro, montadora, numRodas, qtdPortas, numAluguel, dataInicio, dataFinal " +
                "FROM CLIENTE, CARRO, ALUGUEL WHERE (CLIENTE.CPF = ALUGUEL.clienteCPF) AND (CARRO.chassi = ALUGUEL.chassiCarro) AND " +
                "ALUGUEL.numAluguel = " + aluguel.getNumero();

        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()) {
            Cliente cliente = new Cliente(
                    cursor.getInt(cursor.getColumnIndex("CPF")),
                    cursor.getString(cursor.getColumnIndex("nomeCliente"))
            );

            Carro carro = new Carro(
                    cursor.getInt(cursor.getColumnIndex("chassi")),
                    cursor.getString(cursor.getColumnIndex("nomeCarro")),
                    cursor.getString(cursor.getColumnIndex("montadora")),
                    cursor.getInt(cursor.getColumnIndex("numRodas")),
                    cursor.getInt(cursor.getColumnIndex("qtdPortas"))
            );

            aluguel.setNumero(cursor.getInt(cursor.getColumnIndex("numAluguel")));
            aluguel.setDataInicio(cursor.getString(cursor.getColumnIndex("dataInicio")));
            aluguel.setDataFinal(cursor.getString(cursor.getColumnIndex("dataFinal")));
            aluguel.setCliente(cliente);
            aluguel.setCarro(carro);
        }

        cursor.close();
        return aluguel;
    }

    @SuppressLint("Range")
//    @Override
//    public List<Aluguel> findAll() throws SQLException {
//        List<Aluguel> alugueis = new ArrayList<>();
//        String query = "SELECT CPF, nomeCliente, chassi, nomeCarro, montadora, numRodas, qtdPortas, numAluguel dataInicio, dataFinal " +
//                "FROM CLIENTE, CARRO, ALUGUEL";
//        Cursor cursor = database.rawQuery(query, null);
//
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//        while (!cursor.isAfterLast()) {
//            Cliente cliente = new Cliente(
//                    cursor.getInt(cursor.getColumnIndex("CPF")),
//                    cursor.getString(cursor.getColumnIndex("nomeCliente"))
//            );
//
//            Carro carro = new Carro(
//                    cursor.getInt(cursor.getColumnIndex("chassi")),
//                    cursor.getString(cursor.getColumnIndex("nomeCarro")),
//                    cursor.getString(cursor.getColumnIndex("montadora")),
//                    cursor.getInt(cursor.getColumnIndex("numRodas")),
//                    cursor.getInt(cursor.getColumnIndex("qtdPortas"))
//            );
//
//            Aluguel aluguel = new Aluguel(
//                    cursor.getInt(cursor.getColumnIndex("numAluguel")),
//                    cursor.getString(cursor.getColumnIndex("dataInicio")),
//                    cursor.getString(cursor.getColumnIndex("dataFinal")),
//                    cliente,
//                    carro
//            );
//
//            alugueis.add(aluguel);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return alugueis;
//    }

    @Override
    public List<Aluguel> findAll() throws SQLException {
        List<Aluguel> alugueis = new ArrayList<>();
        String query = "SELECT ALUGUEL.numAluguel, ALUGUEL.dataInicio, ALUGUEL.dataFinal, CLIENTE.CPF, CLIENTE.nomeCliente, " +
                        "CARRO.chassi, CARRO.nomeCarro, CARRO.montadora, CARRO.numRodas, CARRO.qtdPortas " +
                        "FROM ALUGUEL " +
                        "INNER JOIN CLIENTE ON ALUGUEL.clienteCPF = CLIENTE.CPF " +
                        "INNER JOIN CARRO ON ALUGUEL.chassiCarro = CARRO.chassi";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Cliente cliente = new Cliente(
                        cursor.getInt(cursor.getColumnIndex("CPF")),
                        cursor.getString(cursor.getColumnIndex("nomeCliente"))
                );

                Carro carro = new Carro(
                        cursor.getInt(cursor.getColumnIndex("chassi")),
                        cursor.getString(cursor.getColumnIndex("nomeCarro")),
                        cursor.getString(cursor.getColumnIndex("montadora")),
                        cursor.getInt(cursor.getColumnIndex("numRodas")),
                        cursor.getInt(cursor.getColumnIndex("qtdPortas"))
                );

                Aluguel aluguel = new Aluguel(
                        cursor.getInt(cursor.getColumnIndex("numAluguel")),
                        cursor.getString(cursor.getColumnIndex("dataInicio")),
                        cursor.getString(cursor.getColumnIndex("dataFinal")),
                        cliente,
                        carro
                );

                alugueis.add(aluguel);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return alugueis;
    }


    private ContentValues getContentValues(Aluguel aluguel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("numAluguel", aluguel.getNumero());
        contentValues.put("dataInicio", aluguel.getDataInicio());
        contentValues.put("dataFinal", aluguel.getDataFinal());
        contentValues.put("clienteCPF", String.valueOf(aluguel.getCliente().getCPF()));
        contentValues.put("chassiCarro", String.valueOf(aluguel.getCarro().getChassi()));

        return contentValues;
    }
}
