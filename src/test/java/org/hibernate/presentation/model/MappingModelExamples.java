package org.hibernate.presentation.model;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metamodel.mapping.EntityMappingType;
import org.hibernate.metamodel.spi.RuntimeMetamodelsImplementor;

import org.hibernate.testing.orm.junit.DomainModel;
import org.hibernate.testing.orm.junit.SessionFactory;
import org.hibernate.testing.orm.junit.SessionFactoryScope;
import org.junit.jupiter.api.Test;

/**
 * @author Steve Ebersole
 */
@DomainModel( annotatedClasses = { Forum.class, Post.class } )
@SessionFactory
public class MappingModelExamples {

	@Test
	public void demonstrateMappingModelAccess(SessionFactoryScope scope) {
		final SessionFactoryImplementor sessionFactory = scope.getSessionFactory();
		final RuntimeMetamodelsImplementor runtimeMetamodels = sessionFactory.getRuntimeMetamodels();

		final EntityMappingType postMapping = runtimeMetamodels.getEntityMappingType( Post.class );
		System.out.println( "Entity - " + postMapping.getEntityName() );
		postMapping.forEachAttributeMapping( (position, attribute) -> {
			System.out.println( "   - Attribute" );
			System.out.println( "      - position : " + position );
			System.out.println( "      - name : " + attribute.getAttributeName() );
			System.out.println( "      - java-type : " + attribute.getJavaType().getJavaTypeClass().getName() );
			System.out.println( "      - fetch-options" );
			System.out.println( "         - timing : " + attribute.getMappedFetchOptions().getTiming() );
			System.out.println( "         - style : " + attribute.getMappedFetchOptions().getStyle() );
		} );
	}

}
