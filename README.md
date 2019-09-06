<div align="center">
  <img src="logo.png"><br><br>
</div>

-----------------

##### Language: English | [中文文档](README-zh.md)

Simple WeChat-miniprogram for online housekeeping services powered by [Spring](https://spring.io/)

The backend is based on my previous Spring site [LLZW](https://github.com/YanzheL/llzw)

This is a Spring demo site designated for beginners, which is also my course project of ***Software Engineering*** at HIT.

**Current project status:**

Frontend: Developing. WeChat mini-program

Backend: Developing.

## Status of Continuous Integration and Automatic Testing

| Branch     | Status                                                    |
| ---------- | --------------------------------------------------------- |
| **master** | ![](https://travis-ci.org/YanzheL/hemeijia.svg?branch=master) |
| **dev**    | ![](https://travis-ci.org/YanzheL/hemeijia.svg?branch=dev)    |


## Prerequisites

- **JRE Runtime:** 9 or later
- **MySQL:** 8 or later, running on [localhost:3306](localhost:3306),  otherwise you should modify related information in [application-dev.yml](api-gate/src/main/resources/application-dev.yml)

## Getting Started

Three options

Backend service will listen on [localhost:8981](localhost:8981) by default

### Use Prebuilt JAR Package

Follow the intructions on [Release Page](https://github.com/YanzheL/hemeijia/releases)

### Use Our Offical Docker Image

You should have docker environment installed before using this option.

Two options

- Run a single backend service

  Pull our official image for api backend

  ```shell
  docker pull leeyanzhe/hemeijia-api-gate
  ```

  Run

  ```shell
  docker run -d -p "8981:8981" leeyanzhe/hemeijia-api-gate
  ```

- Run the production services bundle

  Pull this git repository

  ```shell
  git clone https://github.com/YanzheL/hemeijia.git
  ```

  Enter the project directory and bring up the whole services bundle

  ```shell
  cd hemeijia
  docker-compose up -d --build
  ```

### From Source

To compile the source, you need a valid JDK environment with version 9 or later.

Clone this project

```shell
git clone https://github.com/YanzheL/hemeijia.git
```

Build the package

```shell
./mvnw package -DskipTests
```

Run

```shell
java "-Dspring.profiles.active=dev" -jar target/*.jar
```

You may also run the test version with in-memory database if you do not have an external database.

```shell
java "-Dspring.profiles.active=test" -jar target/*.jar
```

## Features

* RESTful API Backend

* WeChat Mini-Program

* Travis CI Continuous Integration and Automatic Testing

* Docker Integration

  Every commit to `master` and `dev` branch will trigger automatic builds on DockerHub, which produces [Our Latest Docker Image](https://hub.docker.com/r/leeyanzhe/hemeijia-api-gate)

  `latest` image tag targets for `dev` branch

  `stable` image tag targets for `master` branch

## Documentation

- [API Documentation](https://hemeijia.readthedocs.io)

## Contributors:

#### Hemeijia Front:

Located in `miniprogram/` directory

[Xinye Wanyan](https://github.com/WennyXY)

[Zhijie Zhang](https://github.com/zhangzhijie1998)

#### Hemeijia Backend and Misc.

Located in `api-gate/` directory together with other misc files.

[Me](https://github.com/YanzheL)

## License

[Apache License 2.0](LICENSE)
