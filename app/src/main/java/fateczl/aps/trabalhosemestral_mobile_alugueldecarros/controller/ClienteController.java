package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.controller;

import java.sql.SQLException;
import java.util.List;

import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.model.Cliente;
import fateczl.aps.trabalhosemestral_mobile_alugueldecarros.persistence.ClienteDao;

public class ClienteController implements IController<Cliente> {

    private final ClienteDao clienteDao;

    public ClienteController(ClienteDao cli){
        this.clienteDao = cli;
    }
    @Override
    public void inserir(Cliente cliente) throws SQLException {
        if(clienteDao.open() == null){
            clienteDao.open();
        }
        clienteDao.insert(cliente);
        clienteDao.close();
    }

    @Override
    public void atualizar(Cliente cliente) throws SQLException {
        if(clienteDao.open() == null){
            clienteDao.open();
        }
        clienteDao.update(cliente);
        clienteDao.close();
    }

    @Override
    public void deletar(Cliente cliente) throws SQLException {
        if(clienteDao.open() == null){
            clienteDao.open();
        }
        clienteDao.delete(cliente);
        clienteDao.close();
    }

    @Override
    public Cliente buscar(Cliente cliente) throws SQLException {
        if(clienteDao.open() == null){
            clienteDao.open();
        }
        return clienteDao.findOne(cliente);
    }

    @Override
    public List<Cliente> listar() throws SQLException {
        if(clienteDao.open() == null){
            clienteDao.open();
        }
        return clienteDao.findAll();
    }
}
