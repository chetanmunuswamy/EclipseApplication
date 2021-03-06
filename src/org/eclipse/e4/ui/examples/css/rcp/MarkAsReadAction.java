package org.eclipse.e4.ui.examples.css.rcp;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchWindow;


public class MarkAsReadAction extends Action {

	private final IWorkbenchWindow window;

	MarkAsReadAction(String text, IWorkbenchWindow window) {
		super(text);
		this.window = window;
		// The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.CMD_MARK_AS_READ);
		// Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.CMD_MARK_AS_READ);
		setImageDescriptor(org.eclipse.e4.ui.examples.css.rcp.Activator.getImageDescriptor("/icons/sample3.gif"));
	}

	@Override
	public void run() {
		//Mark the message as read

		IViewReference[] viewRefs = window.getActivePage().getViewReferences();

		for (IViewReference viewReference : viewRefs) {
			if(viewReference.getId().equals(View.ID)) {
				View messageView = (View) viewReference.getPart(false);
				if(messageView.isTopMost()) {
					messageView.markAsRead();
					return;
				}
			}
		}
	}
}