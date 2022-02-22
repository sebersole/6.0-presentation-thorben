package org.hibernate.presentation.type.usertype;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.presentation.type.FizzyWidget;
import org.hibernate.usertype.UserType;

/**
 * @author Steve Ebersole
 */
public class FizzyWidgetUserType implements UserType<FizzyWidget> {
	@Override
	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}

	@Override
	public Class<FizzyWidget> returnedClass() {
		return FizzyWidget.class;
	}

	@Override
	public boolean equals(Object x, Object y) {
		return Objects.equals( x, y );
	}

	@Override
	public int hashCode(Object x) {
		return Objects.hashCode( x );
	}

	@Override
	public FizzyWidget nullSafeGet(
			ResultSet rs,
			int position,
			SharedSessionContractImplementor session,
			Object owner) throws SQLException {
		final String state = rs.getString( position );
		return state == null || rs.wasNull()
				? null
				: new FizzyWidget( state );
	}

	@Override
	public void nullSafeSet(
			PreparedStatement st,
			FizzyWidget value,
			int index,
			SharedSessionContractImplementor session) throws SQLException {
		if ( value == null ) {
			st.setString( index, null );
		}
		else {
			st.setString( index, value.getSomeInternalState() );
		}
	}

	@Override
	public Object deepCopy(Object value) {
		// the internal state is immutable
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) {
		return value == null ? null : ( (FizzyWidget) value ).getSomeInternalState();
	}

	@Override
	public Object assemble(Serializable cached, Object owner) {
		return cached == null ? null : new FizzyWidget( (String) cached );
	}

	@Override
	public Object replace(Object detached, Object managed, Object owner) {
		return detached;
	}
}
