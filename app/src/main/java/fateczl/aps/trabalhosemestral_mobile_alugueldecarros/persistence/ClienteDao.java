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

public class ClienteDao implements IClienteDao, ICRUDDao<Cliente> {
    private final Context context;
    private GenericDao genericDao;
    private SQLiteDatabase database;

    public ClienteDao(Context context) {
        this.context = context;
    }

    @Override
    public ClienteDao open() throws SQLException {
        genericDao = new GenericDao(context);
        database = genericDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        genericDao.close();
    }

    @Override
    public void insert(Cliente cliente) throws SQLException {
        ContentValues contentValues = getContentValues(cliente);
        database.insert("CLIENTE", null, contentValues);
    }

    @Override
    public int update(Cliente cliente) throws SQLException {
        ContentValues contentValues = getContentValues(cliente);
        int retorno = database.update("CLIENTE", contentValues, "CPF = " + cliente.getCPF(),null);
        return retorno;
    }

    @Override
    public void delete(Cliente cliente) throws SQLException {
        database.delete("CLIENTE", "CPF = " + cliente.getCPF(),null);
    }

    @SuppressLint("Range")
    @Override
    public Cliente findOne(Cliente cliente) throws SQLException {
       String query = "SELECT CPF, nomeCliente FROM CLIENTE WHERE CPF = " + cliente.getCPF();

       Cursor cursor = database.rawQuery(query, null);
       if (cursor != null){
           cursor.moveToFirst();
       }
       if (!cursor.isAfterLast()){
           cliente.setCPF(cursor.getInt(cursor.getColumnIndex("CPF")));
           cliente.setNome(cursor.getString(cursor.getColumnIndex("nomeCliente")));
       }

        cursor.close();
        return cliente;
    }

    @SuppressLint("Range")
    @Override
    public List<Cliente> findAll() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT CPF, nomeCliente FROM CLIENTE";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()){
            Cliente cliente = new Cliente(cursor.getInt(cursor.getColumnIndex("CPF")),
                    cursor.getString(cursor.getColumnIndex("nomeCliente")));

            clientes.add(cliente);
            cursor.moveToNext();
        }
        cursor.close();
        return clientes;
    }
    private ContentValues getContentValues(Cliente cliente) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("CPF", String.valueOf(cliente.getCPF()));
        contentValues.put("nomeCliente", cliente.getNome());

        return contentValues;
    }
}
