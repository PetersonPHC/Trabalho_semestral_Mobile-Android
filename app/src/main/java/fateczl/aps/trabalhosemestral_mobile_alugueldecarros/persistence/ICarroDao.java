package fateczl.aps.trabalhosemestral_mobile_alugueldecarros.persistence;

import java.sql.SQLException;

public interface ICarroDao {
    public CarroDao open() throws SQLException;
    public void close();
}
