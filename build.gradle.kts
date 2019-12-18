import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot") version "2.2.2.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
	kotlin("kapt") version "1.3.61"
}

group = "dev.higtak.sample"
version = "0.1.0"
java.sourceCompatibility = JavaVersion.VERSION_11

val developmentOnly by configurations.creating
val domaGenRuntime by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.seasar.doma.boot:doma-spring-boot-starter:1.2.1")

	kapt("org.seasar.doma:doma:2.25.1")
	kapt("org.springframework.boot:spring-boot-configuration-processor")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}

	domaGenRuntime("org.seasar.doma:doma-gen:2.25.1")
	domaGenRuntime("org.postgresql:postgresql")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.getByName<BootJar>("bootJar") {
	launchScript()
}

// Doma-Gen
// ref: https://doma-gen.readthedocs.io/en/stable/gen/
// ref: https://docs.gradle.org/current/userguide/ant.html
// Javaのソースコードが生成されるのでKotlinの場合はいまいちかも……
tasks.register("gen") {
	group = "doma-gen"
	doLast {
		ant.withGroovyBuilder {
			"taskdef"("resource" to "domagentask.properties",
					"classpath" to configurations.getByName("domaGenRuntime").asPath)
			"gen"("url" to "jdbc:postgresql://localhost:5432/sample",
					"user" to "app",
					"password" to "P@ssw0rd!") {
				"entityConfig"("packageName" to "dev.higtak.sample.springdomakotlin.domain.entity",
						"useAccessor" to false,
						"useListener" to false,
						"overwrite" to true
				)
				"daoConfig"("packageName" to "dev.higtak.sample.springdomakotlin.domain.repository",
						"suffix" to "Repository",
						"overwrite" to true)
				"sqlConfig"()
			}
		}
	}
}
