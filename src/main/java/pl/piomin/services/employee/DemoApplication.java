package pl.piomin.services.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pl.piomin.services.employee.repository.EmployeeRepository;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	EmployeeRepository repository;

	@Bean
	public Docket swaggerPersonApi10() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("pl.piomin.services.employee.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("Employee API").description("Documentation Employee API v1.0").build());
	}

//	@Bean
//	public io.opentracing.Tracer jaegerTracer() {
//		Configuration config = Configuration.fromEnv();
//		config = new Configuration("employee-service");
//		Configuration.SenderConfiguration.Builder builder;
//
//		System.out.println("Config_Host: " + config.getReporter().getSenderConfiguration().getAgentHost());
//		System.out.println("Config_Port: " + config.getReporter().getSenderConfiguration().getAgentPort());
//		System.out.println("Sampler_Host_Port: " + config.getSampler().getManagerHostPort());
//
//		return config.getTracer();
//	}


//	@Bean
//	public io.opentracing.Tracer tracer() {
//		return new Configuration("employee-service", new Configuration.SamplerConfiguration(ProbabilisticSampler.TYPE, 1),
//						new Configuration.ReporterConfiguration())
//						.getTracer();
//	}


//	@Bean
//	public io.opentracing.Tracer jaegerTracer() {
//		return new Configuration("employee-service", new Configuration.SamplerConfiguration(ProbabilisticSampler.TYPE, 1),
//				new Configuration.ReporterConfiguration())
//				.getTracer();
//	}

//	@Bean
//	public io.opentracing.Tracer zipkinTracer() {
//		OkHttpSender okHttpSender = OkHttpSender.builder()
//				.encoding(Encoding.JSON)
//				.endpoint("http://35.240.176.82/api/v1/spans")
//				.build();
//		AsyncReporter<Span> reporter = AsyncReporter.builder(okHttpSender).build();
//		Tracing braveTracer = Tracing.newBuilder()
//				.localServiceName("employee-service")
//				.reporter(reporter)
//				.traceId128Bit(true)
//				.sampler(Sampler.ALWAYS_SAMPLE)
//				.build();
//		return BraveTracer.create(braveTracer);
//	}
}
