package org.hibernate.presentation.type.registration;

import org.hibernate.presentation.type.FizzyWidget;

import org.hibernate.testing.orm.junit.DomainModel;
import org.hibernate.testing.orm.junit.SessionFactory;
import org.hibernate.testing.orm.junit.SessionFactoryScope;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Steve Ebersole
 */
@DomainModel( annotatedClasses = { FizzyWidget.class, RegistrationEntity .class } )
@SessionFactory
public class RegistrationExample {
	@Test
	public void demonstrateTypeComposition(SessionFactoryScope scope) {
		{
			// load it
			scope.inTransaction( (session) -> {
				final RegistrationEntity  entity = session.byId( RegistrationEntity .class ).load( 1 );
				assertThat( entity ).isNotNull();
				assertThat( entity.getWidget() ).isNotNull();
				assertThat( entity.getWidget().getSomeInternalState() ).isEqualTo( "abc" );
			} );
		}

		{
			// restrict based on it
			scope.inTransaction( (session) -> {
				final String hql = "select e from RegistrationEntity e where e.widget = :widget";
				final RegistrationEntity  entity = session
						.createSelectionQuery( hql, RegistrationEntity .class )
						.setParameter( "widget", new FizzyWidget( "abc" ) )
						.getSingleResultOrNull();
				assertThat( entity ).isNotNull();
				assertThat( entity.getWidget() ).isNotNull();
				assertThat( entity.getWidget().getSomeInternalState() ).isEqualTo( "abc" );
			} );
		}
	}

	@BeforeEach
	public void createData(SessionFactoryScope scope) {
		scope.inTransaction( (session) -> {
			session.persist( new RegistrationEntity ( 1, "my entity", new FizzyWidget( "abc" ) ) );
		} );
	}

	@AfterEach
	public void dropData(SessionFactoryScope scope) {
		scope.inTransaction( (session) -> {
			session.createMutationQuery( "delete RegistrationEntity" ).executeUpdate();
		} );
	}
}
