/*******************************************************************************
 * Copyright (c) 2015, 2017  I3S Laboratory  and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     I3S Laboratory - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.example.moccml.tfsm.k3dsa;

import org.eclipse.gemoc.commons.eclipse.logging.backends.DefaultLoggingBackend;
import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystem;
import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystemManager;
import org.eclipse.gemoc.commons.eclipse.pde.ui.GemocUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends GemocUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.gemoc.example.moccml.tfsm.k3dsa"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	@Override
	public String getId() {
		return PLUGIN_ID;
	}

	private DefaultLoggingBackend _loggingBackend;
	@Override
	public DefaultLoggingBackend resolveLoggingBackend() {
		if (_loggingBackend == null)
		{
			_loggingBackend = new DefaultLoggingBackend(this);
			_loggingBackend.setMessagingSystem(getMessagingSystem());
		}
		return _loggingBackend;
	}

	protected static MessagingSystem messagingSystem = null;

	public static MessagingSystem getMessagingSystem() {
		if (messagingSystem == null) {
			MessagingSystemManager msm = new MessagingSystemManager();
			messagingSystem = msm.createBestPlatformMessagingSystem("org.gemoc.execution.engine", "Execution Engine");
			//messagingSystem = new EclipseMessagingSystem(PLUGIN_ID,
			//		"GEMOC Execution Engine");
			//((EclipseMessagingSystem) messagingSystem)
			//		.setConsoleLogLevel(ConsoleLogLevel.DEV_DEBUG);
		}
		return messagingSystem;
	}
	
	
}
