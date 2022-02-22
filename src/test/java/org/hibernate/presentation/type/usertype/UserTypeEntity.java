package org.hibernate.presentation.type.usertype;

import org.hibernate.annotations.Type;
import org.hibernate.presentation.type.FizzyWidget;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Demonstrates the use of `@Type` (`UserType`)
 *
 * @author Steve Ebersole
 */
@Entity
public class UserTypeEntity {
	@Id
	private Integer id;
	private String name;
	@Type( FizzyWidgetUserType.class )
	private FizzyWidget widget;

	private UserTypeEntity() {
		// for Hibernate use
	}

	public UserTypeEntity(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public UserTypeEntity(Integer id, String name, FizzyWidget widget) {
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