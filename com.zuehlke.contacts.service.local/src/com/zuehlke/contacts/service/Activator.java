package com.zuehlke.contacts.service;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class Activator extends Plugin {

	private static Activator instance;

	public static Activator getDefault() {
		return instance;
	}

	/** {@inheritDoc} */
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		instance = null;
		super.stop(bundleContext);
	}

	/** {@inheritDoc} */
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		super.start(bundleContext);
		instance = this;
		registerServices();
	}

	/**
	 * Registers this bundles local service implementations.
	 */
	private void registerServices() {
		BundleContext bundleContext = getBundle().getBundleContext();
		LocalContactService contactService = new LocalContactService();
		bundleContext.registerService(CustomerService.class,
				new LocalCustomerService(contactService), null);
		bundleContext.registerService(ContactService.class, contactService,
				null);
	}

}
