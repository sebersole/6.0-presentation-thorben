package org.hibernate.presentation.type.json;

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
@DomainModel( annotatedClasses = { FizzyWidget.class, JsonEntity.class } )
@SessionFactory
public class JsonExample {
	@Test
	public void demonstrateTypeComposition(SessionFactoryScope scope) {
		{
			// load it
			scope.inTransaction( (session) -> {
				final JsonEntity entity = session.byId( JsonEntity.class ).load( 1 );
				assertThat( entity ).isNotNull();
				assertThat( entity.getWidget() ).isNotNull();
				assertThat( entity.getWidget().getSomeInternalState() ).isEqualTo( "abc" );
			} );
		}

		{
			// restrict based on it
			scope.inTransaction( (session) -> {
				final String hql = "select e from JsonEntity e where e.widget = :widget";
				final JsonEntity entity = session
						.createSelectionQuery( hql, JsonEntity.class )
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
			session.persist( new JsonEntity( 1, "my entity", new FizzyWidget( "abc" ) ) );
		} );
	}

	@AfterEach
	public void dropData(SessionFactoryScope scope) {
		scope.inTransaction( (session) -> {
			session.createMutationQuery( "delete JsonEntity" ).executeUpdate();
		} );
	}
}
