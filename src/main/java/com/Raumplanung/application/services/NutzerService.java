package com.Raumplanung.application.services;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.ws.rs.core.Response;

public class NutzerService {
	private DataSource dataSource;

	public NutzerService(DataSource dataSource){
		this.dataSource=dataSource;
	}

	private Response resultAsResponse(ResultSet resultSet){
		List<Map> entity = new ArrayList<>();
		try {
			ResultSetMetaData rsm = resultSet.getMetaData();
			int cC = rsm.getColumnCount();
			List<String> cN = new ArrayList<>();
			for (int i = 1; i<=cC; i++){
				cN.add(rsm.getColumnName(i).toUpperCase());
			}
			while (resultSet.next()){
				Map<String, Object> map = new HashMap<>();
				for (int i=1;i<=cC;i++) {
					map.put(cN.get(i-1),resultSet.getString(i));
				}
				entity.add(map);
			}
			resultSet.close();
			return Response.status(Response.Status.OK).entity(entity).build();
		}
		catch (Exception e){
			e.printStackTrace();
			Map<String, Object> err = new HashMap<>();
			err.put("message","Keine Ergebnisse gefunden");
			entity.add(err);
			return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
		}
	}

	public Response findPlatz(){
		Response r;
		try {
			String query="SELECT * FROM PLATZ";
			Connection connection=dataSource.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultSet=preparedStatement.executeQuery();
			r=resultAsResponse(resultSet);
		}
		catch (Exception e){
			e.printStackTrace();
			Map<String, Object> entity = new HashMap<>();
			entity.put("message", "Keine Ergebnisse gefunden" + e.getLocalizedMessage());
			r=Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
		}
		return r;
	}
}
