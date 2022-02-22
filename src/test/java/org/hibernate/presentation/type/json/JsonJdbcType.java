package org.hibernate.presentation.type.json;

import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.ValueBinder;
import org.hibernate.type.descriptor.ValueExtractor;
import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.descriptor.jdbc.JdbcType;

/**
 * @author Steve Ebersole
 */
public class JsonJdbcType implements JdbcType {
	@Override
	public int getJdbcTypeCode() {
		return SqlTypes.JSON;
	}

	@Override
	public <X> ValueBinder<X> getBinder(JavaType<X> javaType) {
		return null;
	}

	@Override
	public <X> ValueExtractor<X> getExtractor(JavaType<X> javaType) {
		return null;
	}
}
