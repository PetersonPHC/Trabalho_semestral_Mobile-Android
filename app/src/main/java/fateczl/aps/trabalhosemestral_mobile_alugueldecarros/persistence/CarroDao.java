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

public class CarroDao implements ICarroDao, ICRUDDao<Carro> {
    private final Context context;
    private GenericDao genericDao;
    private SQLiteDatabase database;

    public CarroDao(Context context) {
        this.context = context;
    }

    @Override
    public CarroDao open() throws SQLException {
        genericDao = new GenericDao(context);
        database = genericDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        genericDao.close();
    }

    @Override
    public void insert(Carro carro) throws SQLException {
        ContentValues contentValues = getContentValues(carro);
        database.insert("CARRO", null, contentValues);
    }

    @Override
    public int update(Carro carro) throws SQLException {
        ContentValues contentValues = getContentValues(carro);
        int retorno = database.update("CARRO", contentValues, "chassi = " + carro.getChassi(), null);
        return retorno;
    }

    @Override
    public void delete(Carro carro) throws SQLException {
        database.delete("CARRO", "chassi = " + carro.getChassi(), null);
    }

    @SuppressLint("Range")
    @Override
    public Carro findOne(Carro carro) throws SQLException {
        String query = "SELECT chassi, nomeCarro, montadora, numRodas, qtdPortas FROM CARRO WHERE chassi = " + carro.getChassi();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()) {
            carro.setChassi(cursor.getInt(cursor.getColumnIndex("chassi")));
            carro.setNome(cursor.getString(cursor.getColumnIndex("nomeCarro")));
            carro.setMontadora(cursor.getString(cursor.getColumnIndex("montadora")));
            carro.setNumeroDeRodas(cursor.getInt(cursor.getColumnIndex("numRodas")));
            carro.setQtdPortas(cursor.getInt(cursor.getColumnIndex("qtdPortas")));
        }

        cursor.close();
        return carro;
    }

    @SuppressLint("Range")
    @Override
    public List<Carro> findAll() throws SQLException {
        List<Carro> carros = new ArrayList<>();
        String query = "SELECT chassi, nomeCarro, montadora, numRodas, qtdPortas FROM CARRO";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            Carro carro = new Carro();
            carro.setChassi(cursor.getInt(cursor.getColumnIndex("chassi")));
            carro.setNome(cursor.getColumnName(cursor.getColumnIndex("nomeCarro")));
            carro.setMontadora(cursor.getString(cursor.getColumnIndex("montadora")));
            carro.setNumeroDeRodas(cursor.getInt(cursor.getColumnIndex("numRodas")));
            carro.setQtdPortas(cursor.getInt(cursor.getColumnIndex("qtdPortas")));

            carros.add(carro);
            cursor.moveToNext();
        }
        cursor.close();
        return carros;
    }

    private ContentValues getContentValues(Carro carro) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("chassi", String.valueOf(carro.getChassi()));
        contentValues.put("nomeCarro", carro.getNome());
        contentValues.put("montadora", carro.getMontadora());
        contentValues.put("numRodas", carro.getNumeroDeRodas());
        contentValues.put("qtdPortas", carro.getQtdPortas());

        return contentValues;
    }
}
