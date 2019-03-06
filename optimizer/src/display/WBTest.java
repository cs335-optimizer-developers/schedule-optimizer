package display;

import org.eclipse.swt.widgets.Composite;

public class WBTest extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public WBTest(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
