package org.hibernate.presentation.type;

/**
 * @author Steve Ebersole
 */
public class FizzyWidget {
	private final String someInternalState;

	public FizzyWidget(String someInternalState) {
		this.someInternalState = someInternalState;
	}

	public String getSomeInternalState() {
		return someInternalState;
	}
}
