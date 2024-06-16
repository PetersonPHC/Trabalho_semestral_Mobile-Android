package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.controller;

import java.sql.SQLException;
import java.util.List;

import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model.Carro;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.persistence.CarroDao;

public class CarroController implements IController<Carro> {
    private final CarroDao carroDao;

    public CarroController(CarroDao c){
        this.carroDao = c;
    }
    @Override
    public void inserir(Carro carro) throws SQLException {
        if(carroDao.open() == null){
            carroDao.open();
        }
        carroDao.insert(carro);
        carroDao.close();
    }

    @Override
    public void atualizar(Carro carro) throws SQLException {
        if(carroDao.open() == null){
            carroDao.open();
        }
        carroDao.update(carro);
        carroDao.close();
    }

    @Override
    public void deletar(Carro carro) throws SQLException {
        if(carroDao.open() == null){
            carroDao.open();
        }
        carroDao.delete(carro);
        carroDao.close();
    }

    @Override
    public Carro buscar(Carro carro) throws SQLException {
        if(carroDao.open() == null){
            carroDao.open();
        }
        return carroDao.findOne(carro);
    }

    @Override
    public List<Carro> listar() throws SQLException {
        if(carroDao.open() == null){
            carroDao.open();
        }
        return carroDao.findAll();
    }
}
