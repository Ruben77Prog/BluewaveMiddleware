package com.ruben.bluewave.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLUtils {
	
	public static final void addClause(Object criteriaParameter, List<String> conditions, String condition, List<Object> parameterValues, Object parameterValue ) {
		
		if(criteriaParameter!=null) {
			conditions.add(condition);
			parameterValues.add(parameterValue);
		}
		
	}
	
	
	public static final int getTotalRows(ResultSet resultSet) throws SQLException {
		int totalRows = 0;
		if(resultSet.last()) {
			totalRows = resultSet.getRow();
		}
		resultSet.beforeFirst();
		return totalRows;
	}

}
