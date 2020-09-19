# Laboratorio 6
## Parte I. Integración continua

1.  Cree (si no la tiene aún) una cuenta en el proveedor PAAS Heroku ([www.heroku.com](https://www.heroku.com/)).

A continuación, creamos la cuenta en [Heroku](https://www.heroku.com/).

<img  src="https://github.com/JuanMunozD/CVDS-6/blob/master/Im%C3%A1genes/Parte1.1.PNG">

2.  Acceda a su cuenta en Heroku y cree una nueva aplicación:

<img  src="https://github.com/PDSW-ECI/WebApp-Heroku-CircleCI-CI/blob/master/img/HerokuCreateApp.png">
	
Ahora, para crear una nueva aplicación, realizamos el siguiente procedimiento.
	
<img  src="https://github.com/JuanMunozD/CVDS-6/blob/master/Im%C3%A1genes/Parte1.2.png">

3.  Después de crear su cuenta en Heroku y la respectiva aplicación, genere una llave de API: Opción Manage Account:

<img  src="https://github.com/PDSW-ECI/WebApp-Heroku-CircleCI-CI/blob/master/img/ManageAccount.png">
	
Opción API Key:
	
<img  src="https://github.com/PDSW-ECI/WebApp-Heroku-CircleCI-CI/blob/master/img/GenerateKey.png">
	
Ahora, para generar la **API Key**, realizamos clic en **Reveal**, y ahora vemos nuestra **API Key**.
	
<img  src="https://github.com/JuanMunozD/CVDS-6/blob/master/Im%C3%A1genes/Parte1.3.png">

4.  Ingrese a la plataforma de integración contínua Circle.CI ([www.circleci.com](www.circleci.com)). Para ingresar, basta que se autentique con su usuario de GitHUB.

Para hacer este procedimiento, ingresamos a la plataforma de integración contínua [Circle.CI](https://circleci.com/).

<img  src="https://github.com/JuanMunozD/CVDS-6/blob/master/Im%C3%A1genes/Parte1.4.png">

5.  Seleccione la opción de agregar proyectos. En la organización o usuario de GitHub, seleccione el proyecto al que le va hacer despliegue continuo, y haga clic en "Build Project":

<img  src="https://github.com/PDSW-ECI/WebApp-Heroku-CircleCI-CI/blob/master/img/AppBuild.png">

Una vez se inicie la primera construcción del proyecto, seleccione las opciones del proyecto (project settings)

<img  src="https://github.com/PDSW-ECI/WebApp-Heroku-CircleCI-CI/blob/master/img/ProjectSettings2.png">:

Vaya a CONTINUOUS DEPLOYMENT/Heroku Deployment, y realice los dos pasos indicados: (1) registrar la llave de Heroku, y (2) asociar su usuario para el despliegue:

<img  src="https://github.com/PDSW-ECI/WebApp-Heroku-CircleCI-CI/blob/master/img/SetDeployUser.png">

6.  Si todo queda correctamente configurado, cada vez que hagan un PUSH al repositorio, CircleCI ejecutará la fase de construcción del proyecto. Para que cuando las pruebas pasen automáticamente se despliegue en Heroku, debe definir en el archivo circle.yml (ubicado en la raíz del proyecto):
*   La rama del repositorio de GitHUB que se desplegará en Heroku. o El nombre de la aplicación de Heroku en la que se hará el despliegue.
*   La ejecución de la fase ‘site’ de Maven, para generar la documentación de pruebas, cubrimiento de pruebas y análisis estático (cuando las mismas sean habilitadas).

Ejemplo:
[https://github.com/PDSW-ECI/base-proyectos/blob/master/circle.yml](https://github.com/PDSW-ECI/base-proyectos/blob/master/circle.yml)

7.  Rectifique que en el pom.xml, en la fase de construcción, se tenga el siguiente plugin (es decir, dentro de \<build>\<plugins>):

```xml
<!-- Plugin configuration for Heroku compatibility. -->
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-dependency-plugin</artifactId>
        <version>2.1</version>
        <executions>
            <execution>
                <phase>package</phase>
                <goals>
                    <goal>copy</goal>
                </goals>
                <configuration>
                    <artifactItems>
                        <artifactItem>
                            <groupId>com.github.jsimone</groupId>
                            <artifactId>webapp-runner</artifactId>
                            <version>8.0.30.2</version>
                            <destFileName>webapp-runner.jar</destFileName>
                        </artifactItem>
                    </artifactItems>
                </configuration>
            </execution>

        </executions>
    </plugin>
```           		
	
Nota: Si en el pom.xml ya hay otro plugin con el mismo <groupId> y <artifactId>, reemplácelo por el anteriormente mostrado.

8.  Heroku requiere los siguientes archivos de configuración (con sus respectivos contenidos) en el directorio raíz del proyecto, de manera que sea qué versión de Java utilizar, y cómo iniciar la aplicación, respectivamente:

system.properties

```java.runtime.version=1.8```

Procfile 

```web:    java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war```

9. El ambiente de despliegue contínuo requiere también un archivo de configuración 'circle.yml' en la raíz del proyecto, en el cual se indica (entre otras cosas) en qué aplicación de Heroku se debe desplegar la aplicación que está en GitHUB. Puede basarse en el siguiente archivo, teniendo en cuenta que se debe ajustar el parámetro 'appname': [https://github.com/PDSW-ECI/base-proyectos/blob/master/circle.yml](https://github.com/PDSW-ECI/base-proyectos/blob/master/circle.yml)

10. Haga commit y push de su repositorio local a GitHub. Abra la consola de CircleCI y verifique que el de descarga, compilación, y despliegue. Igualmente, verifique que la aplicación haya sido desplegada en Heroku.

11. Ahora, va a integrar un entorno de Análisis de Calidad de Código a su proyecto, el cual detecte contínuamente defectos asociados al mismo. Autentíquese en [CODACY](https://www.codacy.com ) con su cuenta de GitHUB, y agregue el proyecto antes creado.

12. Cree un archivo README.md para su proyecto, y asocie al mismo dos 'badges', que permitan conocer el estado del proyecto en cualquier momento: uno para [Circle.CI](https://circleci.com/docs/1.0/status-badges/), y otro para [CODACY](https://support.codacy.com/hc/en-us/articles/212799365-Badges). El proyecto usado como referencia, ya incluye dichos 'badges' en su archivo README: [https://github.com/PDSW-ECI/base-proyectos](https://github.com/PDSW-ECI/base-proyectos)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/8bccd05e4cee4cd6ac6f79770f0f013a)](https://www.codacy.com/manual/JuanMunozD/CVDS-6?utm_source=github.com&utm_medium=referral&utm_content=JuanMunozD/CVDS-6&utm_campaign=Badge_Grade)
