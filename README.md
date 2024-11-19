## Micronaut 4.7.0 Bug

Reproduction. Start the test via ```./gradlew clean test```

It fails because the disabled bean (ClientMetricsFilter) is been used.
If you change the @Context on the Controller to @Singleton it works again.

Seems that on the @Context initialization, also disabled beans are used for instantiation.


Trace:

```
 io.micronaut.context.exceptions.BeanInstantiationException: Bean definition [com.example.Controller] could not be loaded: Error instantiating bean of type  [io.micronaut.http.client.interceptor.HttpClientIntroductionAdvice]

Message: Error processing bean [Definition: io.micronaut.configuration.metrics.binder.web.ClientMetricsFilter] method definition [void onRequest(HttpRequest<Object B> request)]: Bean [class io.micronaut.configuration.metrics.binder.web.ClientMetricsFilter] is disabled since bean property [enabled] value is not equal to [true]
Path Taken: new Controller(TestClient client) --> new Controller([TestClient client]) --> new TestClient$Intercepted(BeanResolutionContext $beanResolutionContext,BeanContext $beanContext,Qualifier $qualifier,[List<BeanRegistration<Interceptor T> E> $interceptors],InterceptorRegistry $interceptorRegistry) --> new HttpClientIntroductionAdvice([HttpClientRegistry<HttpClient T> clientFactory],JsonMediaTypeCodec jsonMediaTypeCodec,List<ReactiveClientResultTransformer E> transformers,HttpClientBinderRegistry binderRegistry,ConversionService conversionService)
	at app//io.micronaut.context.DefaultBeanContext.initializeContext(DefaultBeanContext.java:2000)
	at app//io.micronaut.context.DefaultApplicationContext.initializeContext(DefaultApplicationContext.java:314)
	at app//io.micronaut.context.DefaultBeanContext.configureAndStartContext(DefaultBeanContext.java:3318)
	at app//io.micronaut.context.DefaultBeanContext.start(DefaultBeanContext.java:345)
	at app//io.micronaut.context.DefaultApplicationContext.start(DefaultApplicationContext.java:216)
	at app//io.micronaut.test.extensions.AbstractMicronautExtension.startApplicationContext(AbstractMicronautExtension.java:507)
	at app//io.micronaut.test.extensions.AbstractMicronautExtension.beforeClass(AbstractMicronautExtension.java:346)
	at app//io.micronaut.test.extensions.junit5.MicronautJunit5Extension.beforeAll(MicronautJunit5Extension.java:84)
	at java.base@21.0.1/java.util.ArrayList.forEach(ArrayList.java:1596)
Caused by: io.micronaut.context.exceptions.BeanInstantiationException: Error instantiating bean of type  [io.micronaut.http.client.interceptor.HttpClientIntroductionAdvice]

Message: Error processing bean [Definition: io.micronaut.configuration.metrics.binder.web.ClientMetricsFilter] method definition [void onRequest(HttpRequest<Object B> request)]: Bean [class io.micronaut.configuration.metrics.binder.web.ClientMetricsFilter] is disabled since bean property [enabled] value is not equal to [true]
Path Taken: new Controller(TestClient client) --> new Controller([TestClient client]) --> new TestClient$Intercepted(BeanResolutionContext $beanResolutionContext,BeanContext $beanContext,Qualifier $qualifier,[List<BeanRegistration<Interceptor T> E> $interceptors],InterceptorRegistry $interceptorRegistry) --> new HttpClientIntroductionAdvice([HttpClientRegistry<HttpClient T> clientFactory],JsonMediaTypeCodec jsonMediaTypeCodec,List<ReactiveClientResultTransformer E> transformers,HttpClientBinderRegistry binderRegistry,ConversionService conversionService)
	at app//io.micronaut.context.DefaultBeanContext.resolveByBeanFactory(DefaultBeanContext.java:2349)
	at app//io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2304)
	at app//io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2316)
	at app//io.micronaut.context.DefaultBeanContext.createRegistration(DefaultBeanContext.java:3127)
	at app//io.micronaut.context.SingletonScope.getOrCreate(SingletonScope.java:80)
	at app//io.micronaut.context.DefaultBeanContext.findOrCreateSingletonBeanRegistration(DefaultBeanContext.java:3029)
	at app//io.micronaut.context.DefaultBeanContext.resolveBeanRegistration(DefaultBeanContext.java:2990)
	at app//io.micronaut.context.DefaultBeanContext.resolveBeanRegistration(DefaultBeanContext.java:2756)
	at app//io.micronaut.context.DefaultBeanContext.getBean(DefaultBeanContext.java:1745)
	at app//io.micronaut.context.AbstractBeanResolutionContext.getBean(AbstractBeanResolutionContext.java:89)
	at app//io.micronaut.context.AbstractInitializableBeanDefinition.resolveBean(AbstractInitializableBeanDefinition.java:2188)
	at app//io.micronaut.context.AbstractInitializableBeanDefinition.getBeanForConstructorArgument(AbstractInitializableBeanDefinition.java:1350)
	at app//io.micronaut.http.client.interceptor.$HttpClientIntroductionAdvice$Definition.instantiate(Unknown Source)
	at app//io.micronaut.context.DefaultBeanContext.resolveByBeanFactory(DefaultBeanContext.java:2334)
	at app//io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2304)
	at app//io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2316)
	at app//io.micronaut.context.DefaultBeanContext.createRegistration(DefaultBeanContext.java:3127)
	at app//io.micronaut.context.SingletonScope.getOrCreate(SingletonScope.java:80)
	at app//io.micronaut.context.DefaultBeanContext.findOrCreateSingletonBeanRegistration(DefaultBeanContext.java:3029)
	at app//io.micronaut.context.DefaultBeanContext.resolveBeanRegistration(DefaultBeanContext.java:2990)
	at app//io.micronaut.context.DefaultBeanContext.resolveBeanRegistration(DefaultBeanContext.java:2963)
	at app//io.micronaut.context.DefaultBeanContext.addCandidateToList(DefaultBeanContext.java:3581)
	at app//io.micronaut.context.DefaultBeanContext.resolveBeanRegistrations(DefaultBeanContext.java:3536)
	at app//io.micronaut.context.DefaultBeanContext.getBeanRegistrations(DefaultBeanContext.java:3510)
	at app//io.micronaut.context.AbstractBeanResolutionContext.getBeanRegistrations(AbstractBeanResolutionContext.java:118)
	at app//io.micronaut.context.AbstractInitializableBeanDefinition.resolveBeanRegistrations(AbstractInitializableBeanDefinition.java:2344)
	at app//io.micronaut.context.AbstractInitializableBeanDefinition.getBeanRegistrationsForConstructorArgument(AbstractInitializableBeanDefinition.java:1522)
	at app//com.example.$TestClient$Intercepted$Definition.instantiate(Unknown Source)
	at app//io.micronaut.context.DefaultBeanContext.resolveByBeanFactory(DefaultBeanContext.java:2334)
	at app//io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2304)
	at app//io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2316)
	at app//io.micronaut.context.DefaultBeanContext.createRegistration(DefaultBeanContext.java:3127)
	at app//io.micronaut.context.SingletonScope.getOrCreate(SingletonScope.java:80)
	at app//io.micronaut.context.DefaultBeanContext.findOrCreateSingletonBeanRegistration(DefaultBeanContext.java:3029)
	at app//io.micronaut.context.DefaultBeanContext.resolveBeanRegistration(DefaultBeanContext.java:2990)
	at app//io.micronaut.context.DefaultBeanContext.resolveBeanRegistration(DefaultBeanContext.java:2756)
	at app//io.micronaut.context.DefaultBeanContext.getBean(DefaultBeanContext.java:1745)
	at app//io.micronaut.context.AbstractBeanResolutionContext.getBean(AbstractBeanResolutionContext.java:89)
	at app//io.micronaut.context.AbstractInitializableBeanDefinition.resolveBean(AbstractInitializableBeanDefinition.java:2188)
	at app//io.micronaut.context.AbstractInitializableBeanDefinition.getBeanForConstructorArgument(AbstractInitializableBeanDefinition.java:1350)
	at app//com.example.$Controller$Definition.instantiate(Unknown Source)
	at app//io.micronaut.context.DefaultBeanContext.resolveByBeanFactory(DefaultBeanContext.java:2334)
	at app//io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2304)
	at app//io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2316)
	at app//io.micronaut.context.DefaultBeanContext.createRegistration(DefaultBeanContext.java:3127)
	at app//io.micronaut.context.SingletonScope.getOrCreate(SingletonScope.java:80)
	at app//io.micronaut.context.DefaultBeanContext.findOrCreateSingletonBeanRegistration(DefaultBeanContext.java:3029)
	at app//io.micronaut.context.DefaultBeanContext.initializeEagerBean(DefaultBeanContext.java:2702)
	at app//io.micronaut.context.DefaultBeanContext.initializeContext(DefaultBeanContext.java:1994)
	... 8 more
Caused by: io.micronaut.context.exceptions.BeanContextException: Error processing bean [Definition: io.micronaut.configuration.metrics.binder.web.ClientMetricsFilter] method definition [void onRequest(HttpRequest<Object B> request)]: Bean [class io.micronaut.configuration.metrics.binder.web.ClientMetricsFilter] is disabled since bean property [enabled] value is not equal to [true]
	at app//io.micronaut.context.AnnotationProcessorListener.onCreated(AnnotationProcessorListener.java:98)
	at app//io.micronaut.context.AnnotationProcessorListener.onCreated(AnnotationProcessorListener.java:44)
	at app//io.micronaut.context.DefaultBeanContext.triggerBeanCreatedEventListener(DefaultBeanContext.java:2387)
	at app//io.micronaut.context.DefaultBeanContext.postBeanCreated(DefaultBeanContext.java:2364)
	at app//io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2308)
	at app//io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2316)
	at app//io.micronaut.context.DefaultBeanContext.createRegistration(DefaultBeanContext.java:3127)
	at app//io.micronaut.context.SingletonScope.getOrCreate(SingletonScope.java:80)
	at app//io.micronaut.context.DefaultBeanContext.findOrCreateSingletonBeanRegistration(DefaultBeanContext.java:3029)
	at app//io.micronaut.context.DefaultBeanContext.resolveBeanRegistration(DefaultBeanContext.java:2990)
	at app//io.micronaut.context.DefaultBeanContext.resolveBeanRegistration(DefaultBeanContext.java:2756)
	at app//io.micronaut.context.DefaultBeanContext.getBean(DefaultBeanContext.java:1745)
	at app//io.micronaut.context.AbstractBeanResolutionContext.getBean(AbstractBeanResolutionContext.java:89)
	at app//io.micronaut.context.AbstractInitializableBeanDefinition.resolveBean(AbstractInitializableBeanDefinition.java:2188)
	at app//io.micronaut.context.AbstractInitializableBeanDefinition.getBeanForConstructorArgument(AbstractInitializableBeanDefinition.java:1350)
	at app//io.micronaut.http.client.netty.$DefaultNettyHttpClientRegistry$Definition.instantiate(Unknown Source)
	at app//io.micronaut.context.DefaultBeanContext.resolveByBeanFactory(DefaultBeanContext.java:2334)
	... 56 more
Caused by: io.micronaut.context.exceptions.DisabledBeanException: Bean [class io.micronaut.configuration.metrics.binder.web.ClientMetricsFilter] is disabled since bean property [enabled] value is not equal to [true]
 ```

