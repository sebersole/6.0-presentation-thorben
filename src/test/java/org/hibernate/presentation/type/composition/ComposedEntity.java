package org.hibernate.presentation.type.composition;

import org.hibernate.annotations.JavaType;
import org.hibernate.presentation.type.FizzyWidget;
import org.hibernate.presentation.type.FizzyWidgetJavaType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Demonstrates the use of `@JavaType`
 *
 * @author Steve Ebersole
 */
@Entity
public class ComposedEntity {
	@Id
	private Integer id;
	private String name;
	@JavaType( FizzyWidgetJavaType.class )
	private FizzyWidget widget;

	private ComposedEntity() {
		// for Hibernate use
	}

	public ComposedEntity(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public ComposedEntity(Integer id, String name, FizzyWidget widget) {
		this.id = id;
		this.name = name;
		this.widget = widget;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FizzyWidget getWidget() {
		return widget;
	}

	public void setWidget(FizzyWidget widget) {
		this.widget = widget;
	}
}