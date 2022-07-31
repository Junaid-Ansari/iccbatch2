package com.wellsfargo.pad.iccbatch.batch.reader;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.wellsfargo.pad.iccbatch.domain.People;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ODSReader extends JdbcCursorItemReader<People> implements ItemReader<People>{

    public ODSReader(@Autowired DataSource odsDataSource) {
        setDataSource(odsDataSource);
        setSql("SELECT id, first_name, last_name, email, mobileNumber FROM people");
        setFetchSize(100);
        setRowMapper(new PeopleRowMapper());
    }

    public class PeopleRowMapper implements RowMapper<People> {
        @Override
        public People mapRow(ResultSet rs, int rowNum) throws SQLException {
            People employee  = new People(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("mobileNumber"));
            return employee;
        }
    }
}