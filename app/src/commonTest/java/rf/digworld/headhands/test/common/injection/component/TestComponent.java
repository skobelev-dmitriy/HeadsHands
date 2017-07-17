package rf.digworld.headhands.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import rf.digworld.headhands.injection.component.ApplicationComponent;
import rf.digworld.headhands.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
