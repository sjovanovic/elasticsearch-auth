package org.codelibs.elasticsearch.auth;

import org.codelibs.elasticsearch.auth.module.AuthModule;
import org.codelibs.elasticsearch.auth.rest.AccountRestAction;
import org.codelibs.elasticsearch.auth.rest.ReloadRestAction;
import org.codelibs.elasticsearch.auth.security.IndexAuthenticator;
import org.codelibs.elasticsearch.auth.service.AuthService;
import org.elasticsearch.common.component.LifecycleComponent;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.rest.RestModule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AuthPlugin extends Plugin {
    @Override
    public String name() {
        return "AuthPlugin";
    }

    @Override
    public String description() {
        return "This is a elasticsearch-auth plugin.";
    }

    // for Rest API

    public void onModule(final RestModule module) {
        module.addRestAction(AccountRestAction.class);
        module.addRestAction(ReloadRestAction.class);
    }

    // for Service
    /*
    @Override
    public Collection<Class<? extends Module>> modules() {
        final Collection<Class<? extends Module>> modules = Lists
                .newArrayList();
        modules.add(AuthModule.class);
        return modules;
    }
    */

    @Override
    public Collection<Module> nodeModules() {

        return Collections.<Module>singletonList(new AuthModule());
    }

    // for Service
    /*
    @SuppressWarnings("rawtypes")
    @Override
    public Collection<Class<? extends LifecycleComponent>> services() {
        final Collection<Class<? extends LifecycleComponent>> services = Lists
                .newArrayList();
        services.add(AuthService.class);
        services.add(IndexAuthenticator.class);
        return services;
    }
    */

    @Override
    @SuppressWarnings("rawtypes") // Plugin use a rawtype
    public Collection<Class<? extends LifecycleComponent>> nodeServices() {
        Collection<Class<? extends LifecycleComponent>> services = new ArrayList<>();
        services.add(AuthService.class);
        services.add(IndexAuthenticator.class);
        return services;
    }

    @Override
    public Settings additionalSettings() {
        return Settings.EMPTY;
    }
}
