# spring-sample-cloud-stream-function

sample repository for below issue
https://stackoverflow.com/questions/55368204/is-there-an-integration-of-spring-cloud-function-webflux-spring-cloud-stream-w

docker-compose up -d starts kafka and zookeeper locally

# Main goal

get data from reactive http end point and pump it to kafka 

since we registered a bean of consumer like below
```
	@Bean
	public Consumer<String> storeSync() {
		return this::store;
	}
```
it is exposed as http endpoint and can be invoked with payload like below

```
curl -X POST \
  http://localhost:8080/storeSync \
  -d 'sample payload'
```

from the spring cloud stream documentation these spring cloud functions can be act as Source, Sink and Processor

* [https://cloud.spring.io/spring-cloud-static/spring-cloud-stream/2.1.2.RELEASE/single/spring-cloud-stream.html#_spring_cloud_function](https://cloud.spring.io/spring-cloud-static/spring-cloud-stream/2.1.2.RELEASE/single/spring-cloud-stream.html#_spring_cloud_function)
