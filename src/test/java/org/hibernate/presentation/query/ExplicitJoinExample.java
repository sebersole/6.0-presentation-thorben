package org.hibernate.presentation.query;

import java.util.List;

import org.hibernate.presentation.model.Forum;
import org.hibernate.presentation.model.Post;
import org.hibernate.query.SelectionQuery;

import org.hibernate.testing.orm.junit.DomainModel;
import org.hibernate.testing.orm.junit.SessionFactory;
import org.hibernate.testing.orm.junit.SessionFactoryScope;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Demonstrate implicit selection of multiple roots
 *
 * @author Steve Ebersole
 */
@DomainModel( annotatedClasses = { Forum.class, Post.class } )
@SessionFactory
public class ExplicitJoinExample {
	@Test
	public void demonstrateMultipleQueryRoot(SessionFactoryScope scope) {
		scope.inTransaction( (session) -> {
			final SelectionQuery<Post> query = session.createSelectionQuery( "from Post p join p.forum f", Post.class );
			final List<Post> results = query.list();
			assertThat( results ).hasSize( 1 );
		} );

		// achieve legacy behavior - now need explicit select-clause
		scope.inTransaction( (session) -> {
			final SelectionQuery<Object[]> query = session.createSelectionQuery( "select p, f from Post p join p.forum f", Object[].class );
			final List<Object[]> results = query.list();
			assertThat( results ).hasSize( 1 );
			assertThat( results.get( 0 ) ).isInstanceOf( Object[].class );
			final Object[] row = results.get( 0 );
			assertThat( row[0] ).isInstanceOf( Post.class );
			assertThat( row[1] ).isInstanceOf( Forum.class );
		} );
	}

	@BeforeEach
	public void createData(SessionFactoryScope scope) {
		scope.inTransaction( (session) -> {
			final Forum forum = new Forum( 1, "first forum" );
			session.persist( forum );

			final Post post = new Post( 1, "Welcome!", forum );
			session.persist( post );
		} );
	}

	@AfterEach
	public void dropData(SessionFactoryScope scope) {
		scope.inTransaction( (session) -> {
			session.createMutationQuery( "delete Post" ).executeUpdate();
			session.createMutationQuery( "delete Forum" ).executeUpdate();
		} );
	}
}
