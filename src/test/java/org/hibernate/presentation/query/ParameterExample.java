package org.hibernate.presentation.query;

import java.util.List;

import org.hibernate.presentation.model.Forum;
import org.hibernate.presentation.model.Post;
import org.hibernate.query.SelectionQuery;
import org.hibernate.type.StandardBasicTypes;

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
@DomainModel( annotatedClasses = { Forum.class, Post.class } )
@SessionFactory
public class ParameterExample {
	@Test
	public void demonstrateParameterSetting(SessionFactoryScope scope) {
		scope.inTransaction( (session) -> {
			final String hql = "from Post p where p.forum.name = :name";
			final List<Post> posts = session
					.createSelectionQuery( hql, Post.class )
					.setParameter( "name", "Hibernate Users" )
					.list();
			assertThat( posts ).hasSize( 1 );
		} );

		scope.inTransaction( (session) -> {
			final String hql = "from Post p where p.forum.name = :name";
			final List<Post> posts = session
					.createSelectionQuery( hql, Post.class )
					.setParameter( "name", "Hibernate Users", StandardBasicTypes.NSTRING )
					.list();
			assertThat( posts ).hasSize( 1 );
		} );
	}

	@BeforeEach
	public void createData(SessionFactoryScope scope) {
		scope.inTransaction( (session) -> {
			final Forum forum = new Forum( 1, "Hibernate Users" );
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
