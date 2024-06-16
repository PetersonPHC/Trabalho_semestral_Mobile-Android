package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.controller;

import java.sql.SQLException;
import java.util.List;

import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model.Aluguel;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.persistence.AluguelDao;

public class AluguelController implements IController<Aluguel>{
    private AluguelDao aluguelDao;
    public AluguelController (AluguelDao aluguel) {
        this.aluguelDao = aluguel;
    }

    @Override
    public void inserir(Aluguel aluguel) throws SQLException {
        if (aluguelDao.open() == null){
            aluguelDao.open();
        }
        aluguelDao.insert(aluguel);
        aluguelDao.close();
    }

    @Override
    public void atualizar(Aluguel aluguel) throws SQLException {
        if (aluguelDao.open() == null){
            aluguelDao.open();
        }
        aluguelDao.update(aluguel);
        aluguelDao.close();
    }

    @Override
    public void deletar(Aluguel aluguel) throws SQLException {
        if (aluguelDao.open() == null){
            aluguelDao.open();
        }
        aluguelDao.delete(aluguel);
        aluguelDao.close();
    }

    @Override
    public Aluguel buscar(Aluguel aluguel) throws SQLException {
        if (aluguelDao.open() == null){
            aluguelDao.open();
        }
        return aluguelDao.findOne(aluguel);
    }

    @Override
    public List<Aluguel> listar() throws SQLException {
        if (aluguelDao.open() == null){
            aluguelDao.open();
        }
        return aluguelDao.findAll();
    }
}
