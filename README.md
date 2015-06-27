# How to run

## Make .jar file

- By following command, .jar file will be put in ```build/libs``` directory

```
$ ./gradlew build
```

## Start running by specifying .jar file

```
$ java -jar build/libs/fikaflag-<version_name>.jar
```

## Make & launch by bootRun task

```
$ ./gradlew bootRun
```

## Run on docker

- https://spring.io/guides/gs/spring-boot-docker/
- Put .jar & Dockerfile in the same directly

```
$ cd <path to Dockerfile>
$ docker build -t <imageName>:<tagName> .
$ docker images # To get the image Id
$ do docker run -p 8080:8080 -t <imageId>
```

- In case of Mac (using boot2docker)
  - Ip address of docker container is available by ```$ boot2docker ip```

# Flag API

## 確認コマンド

### Post new flag API

```
$ curl -v -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{ "location": "Desk", "message": "Test test test" }' http://localhost:8080/flag
```

# Gradle docker plug-in
- 幾つかある中で、Starが多いgradle-dockerというpluginがある
  - https://github.com/Transmode/gradle-docker
- boot2dockerではdocker buildに失敗してしまう
  - https://github.com/Transmode/gradle-docker/issues/38
  - 2015/6/20時点の最新が1.2で、1.3で修正されるとのこと

## ほかも試したけどダメだった
- https://github.com/bmuschko/gradle-docker-plugin
  - ```$ ./gradlew dockerBuildImage```
  - ```$  ./gradlew dockerBuildImage -DdockerServerUrl=https://192.168.59.105:2356 -DdockerCertPath=~/.boot2docker/certs/boot2docker-vm/
```
```
docker {
    javaApplication {
        baseImage = 'dockerfile/java:openjdk-8-jre'
        maintainer = 'tsuyoyo'
        port = 8080
        tag = 'Test-0.1'
    }

    url = 'https://192.168.59.105:2376'
    certPath = new File(System.properties['user.home'], '.boot2docker/certs/boot2docker-vm')
}
```