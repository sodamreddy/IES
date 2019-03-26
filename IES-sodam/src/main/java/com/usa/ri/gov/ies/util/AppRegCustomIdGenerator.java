package com.usa.ri.gov.ies.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppRegCustomIdGenerator implements IdentifierGenerator {
	Logger logger=LoggerFactory.getLogger(AppRegCustomIdGenerator.class);
	public static final String QUERY="SELECT APP_ID_SEQ.NEXTVAL FROM DUAL";

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		logger.debug("Cusom id gerator started");
		try {
			Connection con=session.connection();
			PreparedStatement ps= con.prepareStatement(QUERY);
			ResultSet rs= ps.executeQuery();
			int val=0;
			if(rs.next()) {
			 	val=rs.getInt(1);
			}
			if(val<10) {
				return "AR00"+val;
			}
			else if(val<100) {
				return "AR0"+val;
			}
			else 
				return "AR"+val;
		}catch(Exception e) {
			logger.error("custom Id generation failed");
			return "failied to generate id value";
		}
	}//generator
}//class
