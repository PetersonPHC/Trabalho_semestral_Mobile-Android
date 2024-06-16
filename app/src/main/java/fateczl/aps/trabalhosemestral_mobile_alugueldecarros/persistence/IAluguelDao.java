package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.persistence;

import java.sql.SQLException;

public interface IAluguelDao {
    public AluguelDao open() throws SQLException;
    public void close();
}
