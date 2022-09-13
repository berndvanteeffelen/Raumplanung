package com.Raumplanung.repositories;

import com.Raumplanung.model.Nutzer;
import com.Raumplanung.model.NutzerRepository;
import com.Raumplanung.model.Rolle;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SQLiteNutzerRepository implements NutzerRepository {
	@Inject
	private DataSource dataSource;

	@Override
	public Optional<Nutzer> findByPersonalnummer(int personalnummer) {
		Optional<Nutzer> nutzer = Optional.empty();
		try (Connection connection = dataSource.getConnection()) {
			String sql = "SELECT Personalnummer, 'ADMIN' FROM ADMIN WHERE Personalnummer = ? UNION "
					+ "SELECT Personalnummer, 'NUTZER' FROM NUTZER WHERE Personalnummer = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.closeOnCompletion();
			preparedStatement.setObject(1, personalnummer);
			preparedStatement.setObject(2, personalnummer);
			ResultSet resultSet = preparedStatement.executeQuery();
			nutzer = Optional.ofNullable(extractNutzer(resultSet));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return nutzer;
	}

	@Override
	public long countByPersonalnummerAndPasswort(int personalnummer, String passwort) {
		try (Connection connection = dataSource.getConnection()) {
			String sql = "SELECT count(*) FROM (SELECT * FROM NUTZER WHERE Personalnummer = ? AND Passwort = ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.closeOnCompletion();
			preparedStatement.setObject(1, personalnummer);
			preparedStatement.setObject(2, passwort);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getLong(1);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return 0;
	}

	private Nutzer extractNutzer(ResultSet resultSet) {
		try {
			if (!resultSet.next()) return null;
			int personalnummer = resultSet.getInt(1);
			Set<Rolle> roles = new HashSet<>();
			do {
				roles.add(Rolle.valueOf(resultSet.getString(2)));
			} while (resultSet.next());
			return new Nutzer(personalnummer, roles);
		} catch (SQLException exception) {
			return null;
		}
	}
}
