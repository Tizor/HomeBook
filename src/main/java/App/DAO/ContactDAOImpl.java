package App.DAO;

import App.Model.Contact;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContactDAOImpl implements ContactDAO {

    private JdbcTemplate jdbcTemplate;

    public ContactDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(Contact contact) {
        if (contact.getId() > 0) {

            String sql = "UPDATE contacts SET name=?, city=?, phone=? "
                    + " WHERE id=?";
            jdbcTemplate.update(sql, contact.getName(), contact.getCity(),
                    contact.getPhone(), contact.getId());
        } else {

            String sql = "INSERT INTO contacts ( name, city, phone)"
                    + " VALUES ( ?, ?, ?)";
            jdbcTemplate.update(sql, contact.getName(), contact.getCity(),
                    contact.getPhone());
        }

    }

    public void delete(int contactId) {
        String sql = "DELETE FROM contacts WHERE id=?";
        jdbcTemplate.update(sql, contactId);
    }

    public List<Contact> list() {
        String sql = "SELECT * FROM contacts";
        List<Contact> listContact = jdbcTemplate.query(sql, new RowMapper<Contact>() {

            public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
                Contact aContact = new Contact();

                aContact.setId(rs.getInt("id"));
                aContact.setName(rs.getString("name"));
                aContact.setCity(rs.getString("city"));
                aContact.setPhone(rs.getString("phone"));

                return aContact;
            }

        });

        return listContact;
    }

    public Contact get(int contactId) {
        String sql = "SELECT * FROM contacts WHERE id=" + contactId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {

            public Contact extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Contact contact = new Contact();
                    contact.setId(rs.getInt("id"));
                    contact.setName(rs.getString("name"));
                    contact.setCity(rs.getString("city"));
                    contact.setPhone(rs.getString("phone"));

                    return contact;
                }

                return null;
            }

        });
    }

}